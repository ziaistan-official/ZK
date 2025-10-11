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

        final CountDownLatch latch = new CountDownLatch(4);

        // 1. Substitutions
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            try {
                performSubstitutionSearch(normalizedToken, customSuggestions, commonSuggestions, wordlistSuggestions);
            } finally {
                latch.countDown();
            }
        });

        // 2. Deletions
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            try {
                performDeletionSearch(normalizedToken, customSuggestions, commonSuggestions, wordlistSuggestions);
            } finally {
                latch.countDown();
            }
        });

        // 3. Insertions
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            try {
                performInsertionSearch(normalizedToken, customSuggestions, commonSuggestions, wordlistSuggestions);
            } finally {
                latch.countDown();
            }
        });

        // 4. Transpositions
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            try {
                performTranspositionSearch(normalizedToken, customSuggestions, commonSuggestions, wordlistSuggestions);
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

    private void performSubstitutionSearch(String token, List<String> custom, List<String> common, List<String> wordlist) {
        List<List<Character>> alternates = new ArrayList<>();
        for (char c : token.toCharArray()) {
            List<Character> neighbors = surroundings.get(c);
            if (neighbors == null) {
                neighbors = Collections.singletonList(c);
            }
            alternates.add(neighbors);
        }

        trieGuidedSearch(new StringBuilder(), suggestionProvider.customRoot, alternates, 0, custom);
        if (suggestionProvider.commonLoaded) {
            trieGuidedSearch(new StringBuilder(), suggestionProvider.commonRoot, alternates, 0, common);
        }
        trieGuidedSearch(new StringBuilder(), suggestionProvider.wordlistRoot, alternates, 0, wordlist);
    }

    private void performDeletionSearch(String token, List<String> custom, List<String> common, List<String> wordlist) {
        for (int i = 0; i < token.length(); i++) {
            String candidate = token.substring(0, i) + token.substring(i + 1);
            addCandidate(candidate, custom, common, wordlist);
        }
    }

    private void performInsertionSearch(String token, List<String> custom, List<String> common, List<String> wordlist) {
        for (int i = 0; i <= token.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String candidate = token.substring(0, i) + c + token.substring(i);
                addCandidate(candidate, custom, common, wordlist);
            }
        }
    }

    private void performTranspositionSearch(String token, List<String> custom, List<String> common, List<String> wordlist) {
        if (token.length() < 2) return;
        char[] chars = token.toCharArray();
        for (int i = 0; i < token.length() - 1; i++) {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
            addCandidate(new String(chars), custom, common, wordlist);
            chars[i + 1] = chars[i];
            chars[i] = temp;
        }
    }

    private void addCandidate(String candidate, List<String> custom, List<String> common, List<String> wordlist) {
        SuggestionProvider.WordSource source = suggestionProvider.getWordSource(candidate);
        switch (source) {
            case CUSTOM: custom.add(candidate); break;
            case COMMON: common.add(candidate); break;
            case WORDLIST: wordlist.add(candidate); break;
            default: break;
        }
    }

    private void trieGuidedSearch(StringBuilder currentWord, SuggestionProvider.TrieNode node,
                                  List<List<Character>> alternates, int position, List<String> suggestions) {
        if (position == alternates.size()) {
            if (node.isEndOfWord) {
                suggestions.add(currentWord.toString());
            }
            return;
        }

        for (char c : alternates.get(position)) {
            SuggestionProvider.TrieNode child = node.children.get(c);
            if (child != null) {
                currentWord.append(c);
                trieGuidedSearch(currentWord, child, alternates, position + 1, suggestions);
                currentWord.deleteCharAt(currentWord.length() - 1);
            }
        }
    }
}
