package juloo.keyboard2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private static class CorrectionCandidate {
        final String word;
        final SuggestionProvider.WordSource source;

        CorrectionCandidate(String word, SuggestionProvider.WordSource source) {
            this.word = word;
            this.source = source;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            CorrectionCandidate that = (CorrectionCandidate) obj;
            return word.equals(that.word);
        }

        @Override
        public int hashCode() {
            return word.hashCode();
        }
    }

    /**
     * Gets a list of potential corrections for a given word, sorted by dictionary priority.
     * @param word The word to check, expected to be in lowercase.
     * @return A sorted list of corrected words, or an empty list if no correction is needed or found.
     */
    public List<String> getCorrections(String word) {
        if (!layoutReady || adjacencyMap == null || suggestionProvider == null || word == null || word.length() < 2) {
            return Collections.emptyList();
        }

        // 1. Check if the original word is already valid. If so, no correction needed.
        if (suggestionProvider.getWordSource(word) != SuggestionProvider.WordSource.NONE) {
            return Collections.emptyList();
        }

        Set<CorrectionCandidate> candidates = new HashSet<>();

        // 2. Generate candidates from transpositions
        for (int i = 0; i < word.length() - 1; i++) {
            char[] chars = word.toCharArray();
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
            String candidateWord = new String(chars);
            SuggestionProvider.WordSource source = suggestionProvider.getWordSource(candidateWord);
            if (source != SuggestionProvider.WordSource.NONE) {
                candidates.add(new CorrectionCandidate(candidateWord, source));
            }
        }

        // 3. Generate candidates from substitutions
        for (int i = 0; i < word.length(); i++) {
            List<Character> neighbors = adjacencyMap.get(word.charAt(i));
            if (neighbors != null) {
                StringBuilder builder = new StringBuilder(word);
                for (char neighbor : neighbors) {
                    builder.setCharAt(i, neighbor);
                    String candidateWord = builder.toString();
                    SuggestionProvider.WordSource source = suggestionProvider.getWordSource(candidateWord);
                    if (source != SuggestionProvider.WordSource.NONE) {
                        candidates.add(new CorrectionCandidate(candidateWord, source));
                    }
                }
            }
        }

        // 4. Sort the candidates based on the source priority
        List<CorrectionCandidate> sortedCandidates = new ArrayList<>(candidates);
        Collections.sort(sortedCandidates, Comparator.comparingInt(c -> c.source.ordinal()));

        // 5. Extract the sorted words
        List<String> result = new ArrayList<>();
        for(CorrectionCandidate candidate : sortedCandidates) {
            result.add(candidate.word);
        }

        return result;
    }
}