package juloo.keyboard2;

import android.content.Context;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.CountDownLatch;

public class KeyboardAwareSuggester {

    private final Map<Character, List<Character>> surroundings;
    private final SuggestionProvider suggestionProvider;

    public KeyboardAwareSuggester(Context context, SuggestionProvider suggestionProvider) {
        this.surroundings = parseSurroundings(context);
        this.suggestionProvider = suggestionProvider;
    }

    private Map<Character, List<Character>> parseSurroundings(Context context) {
        Map<Character, List<Character>> surroundingsMap = new HashMap<>();
        try {
            XmlPullParser parser = context.getResources().getXml(R.xml.surroundings);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && "char".equals(parser.getName())) {
                    char value = parser.getAttributeValue(null, "value").charAt(0);
                    String neighborsStr = parser.getAttributeValue(null, "neighbors");
                    List<Character> neighbors = new ArrayList<>();
                    neighbors.add(value); // A character is its own neighbor
                    for (char c : neighborsStr.toCharArray()) {
                        neighbors.add(c);
                    }
                    surroundingsMap.put(value, neighbors);
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return surroundingsMap;
    }

    public List<String> suggest(String token) {
        if (token == null || token.isEmpty()) {
            return Collections.emptyList();
        }

        String normalizedToken = token.toLowerCase();
        final List<String> customSuggestions = Collections.synchronizedList(new ArrayList<>());
        final List<String> commonSuggestions = Collections.synchronizedList(new ArrayList<>());
        final List<String> wordlistSuggestions = Collections.synchronizedList(new ArrayList<>());

        final CountDownLatch latch = new CountDownLatch(3);

        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            try {
                unifiedTrieSearch(new StringBuilder(), suggestionProvider.customRoot, normalizedToken, 0, 1, customSuggestions);
            } finally {
                latch.countDown();
            }
        });

        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            try {
                if (suggestionProvider.commonLoaded) {
                    unifiedTrieSearch(new StringBuilder(), suggestionProvider.commonRoot, normalizedToken, 0, 1, commonSuggestions);
                }
            } finally {
                latch.countDown();
            }
        });

        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            try {
                unifiedTrieSearch(new StringBuilder(), suggestionProvider.wordlistRoot, normalizedToken, 0, 1, wordlistSuggestions);
            } finally {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        }

        Set<String> finalSuggestions = new LinkedHashSet<>();
        finalSuggestions.addAll(customSuggestions);
        finalSuggestions.addAll(commonSuggestions);
        finalSuggestions.addAll(wordlistSuggestions);

        return new ArrayList<>(finalSuggestions);
    }

    private void unifiedTrieSearch(StringBuilder currentWord, SuggestionProvider.TrieNode node,
                                   String token, int tokenIndex, int edits, List<String> suggestions) {
        if (node.isEndOfWord && tokenIndex == token.length()) {
            if (!suggestions.contains(currentWord.toString())) {
                suggestions.add(currentWord.toString());
            }
        }

        if (tokenIndex > token.length()) {
            return;
        }

        for (Map.Entry<Character, SuggestionProvider.TrieNode> entry : node.children.entrySet()) {
            char c = entry.getKey();
            SuggestionProvider.TrieNode child = entry.getValue();
            currentWord.append(c);

            // Match
            if (tokenIndex < token.length() && c == token.charAt(tokenIndex)) {
                unifiedTrieSearch(currentWord, child, token, tokenIndex + 1, edits, suggestions);
            } else if (edits > 0) {
                // Substitution
                List<Character> neighbors = surroundings.get(token.charAt(tokenIndex));
                if (neighbors != null && neighbors.contains(c)) {
                    unifiedTrieSearch(currentWord, child, token, tokenIndex + 1, edits - 1, suggestions);
                }
                // Deletion
                unifiedTrieSearch(currentWord, child, token, tokenIndex, edits - 1, suggestions);
                // Insertion
                unifiedTrieSearch(currentWord, node, token, tokenIndex + 1, edits - 1, suggestions);
                // Transposition
                if (tokenIndex < token.length() - 1 && c == token.charAt(tokenIndex + 1) && entry.getValue().children.containsKey(token.charAt(tokenIndex))) {
                    unifiedTrieSearch(currentWord, child.children.get(token.charAt(tokenIndex)), token, tokenIndex + 2, edits - 1, suggestions);
                }
            }
            currentWord.deleteCharAt(currentWord.length() - 1);
        }
    }
}
