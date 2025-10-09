package juloo.keyboard2;

import java.util.List;
import java.util.Map;

public class LayoutBasedAutoCorrectionProvider {

    private final SuggestionProvider suggestionProvider;
    private Map<Character, List<Character>> adjacencyMap;
    private volatile boolean layoutReady = false;

    public LayoutBasedAutoCorrectionProvider(SuggestionProvider suggestionProvider) {
        this.suggestionProvider = suggestionProvider;
    }

    public void updateLayout(KeyboardData keyboardData) {
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            layoutReady = false;
            if (keyboardData != null) {
                adjacencyMap = KeyboardLayoutAnalyzer.getAdjacencyMap(keyboardData);
            }
            layoutReady = true;
        });
    }

    /**
     * Gets a correction for a given word by generating candidates and checking them against the dictionary.
     * This is a lightweight, on-the-fly correction mechanism.
     * @param word The word to check, expected to be in lowercase.
     * @return The corrected word, or null if no correction is found.
     */
    public String getCorrection(String word) {
        if (!layoutReady || adjacencyMap == null || suggestionProvider == null || word == null || word.length() < 2) {
            return null;
        }

        // Type 1: Transposition errors (e.g., "hte" -> "the")
        for (int i = 0; i < word.length() - 1; i++) {
            char[] chars = word.toCharArray();
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
            String candidate = new String(chars);
            if (suggestionProvider.isValidWord(candidate)) {
                return candidate;
            }
        }

        // Type 2: Substitution errors (e.g., "yhe" -> "the")
        for (int i = 0; i < word.length(); i++) {
            List<Character> neighbors = adjacencyMap.get(word.charAt(i));
            if (neighbors != null) {
                StringBuilder builder = new StringBuilder(word);
                for (char neighbor : neighbors) {
                    builder.setCharAt(i, neighbor);
                    String candidate = builder.toString();
                    if (!candidate.equals(word) && suggestionProvider.isValidWord(candidate)) {
                        return candidate;
                    }
                }
            }
        }

        return null;
    }
}