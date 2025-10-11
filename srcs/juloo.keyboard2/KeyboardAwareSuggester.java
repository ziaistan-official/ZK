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

    private static final int MAX_CANDIDATES = 1000;

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

        List<List<Character>> alternates = new ArrayList<>();
        for (char c : normalizedToken.toCharArray()) {
            List<Character> neighbors = surroundings.get(c);
            if (neighbors == null) {
                neighbors = Collections.singletonList(c);
            }
            alternates.add(neighbors);
        }

        if (alternates.isEmpty()) {
            return Collections.emptyList();
        }

        List<Character> firstCharAlternates = alternates.get(0);
        final CountDownLatch latch = new CountDownLatch(firstCharAlternates.size());

        for (final char firstChar : firstCharAlternates) {
            KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(firstChar);
                    generateCandidates(normalizedToken, alternates, 1, sb, customSuggestions, commonSuggestions, wordlistSuggestions);
                } finally {
                    latch.countDown();
                }
            });
        }

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

    private void generateCandidates(String token, List<List<Character>> alternates, int position,
                                    StringBuilder currentCandidate, List<String> customSuggestions,
                                    List<String> commonSuggestions, List<String> wordlistSuggestions) {
        if (customSuggestions.size() + commonSuggestions.size() + wordlistSuggestions.size() >= MAX_CANDIDATES) {
            return;
        }

        if (position == token.length()) {
            String candidate = currentCandidate.toString();
            SuggestionProvider.WordSource source = suggestionProvider.getWordSource(candidate);
            switch (source) {
                case CUSTOM:
                    customSuggestions.add(candidate);
                    break;
                case COMMON:
                    commonSuggestions.add(candidate);
                    break;
                case WORDLIST:
                    wordlistSuggestions.add(candidate);
                    break;
                default:
                    break;
            }
            return;
        }

        for (char alternate : alternates.get(position)) {
            currentCandidate.append(alternate);
            generateCandidates(token, alternates, position + 1, currentCandidate, customSuggestions, commonSuggestions, wordlistSuggestions);
            currentCandidate.deleteCharAt(currentCandidate.length() - 1);
        }
    }
}
