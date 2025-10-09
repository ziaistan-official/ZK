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
     * The typo-detection algorithms (transposition, substitution, etc.) are script-agnostic
     * and will work for any language, including Urdu, as long as a dictionary is provided.
     * @param word The word to check, expected to be in lowercase.
     * @return A sorted list of corrected words, or an empty list if no correction is needed or found.
     */
    public List<String> getCorrections(String word) {
        if (!layoutReady || suggestionProvider == null) {
            return Collections.emptyList();
        }
        // If the original word is already valid, no correction is needed.
        if (suggestionProvider.getWordSource(word) != SuggestionProvider.WordSource.NONE) {
            return Collections.emptyList();
        }
        return getSimilarWords(word);
    }

    /**
     * Gets a list of words similar to the given word, sorted by dictionary priority.
     * This is used for generating suggestions for a word that may already be valid.
     * @param word The word to check, expected to be in lowercase.
     * @return A sorted list of similar words.
     */
    public List<String> getSimilarWords(String word) {
        if (!layoutReady || adjacencyMap == null || suggestionProvider == null || word == null || word.length() < 2) {
            return Collections.emptyList();
        }

        Set<CorrectionCandidate> candidates = new HashSet<>();

        // Generate candidates from transpositions
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

        // Generate candidates from substitutions
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

        // Generate candidate from full reversal
        String reversedWord = new StringBuilder(word).reverse().toString();
        SuggestionProvider.WordSource reversedSource = suggestionProvider.getWordSource(reversedWord);
        if (reversedSource != SuggestionProvider.WordSource.NONE) {
            candidates.add(new CorrectionCandidate(reversedWord, reversedSource));
        }

        // Generate candidate from repeated letter reduction
        if (word.matches(".*(.)\\1.*")) {
            StringBuilder reducedBuilder = new StringBuilder();
            if(word.length() > 0) {
                reducedBuilder.append(word.charAt(0));
                for (int i = 1; i < word.length(); i++) {
                    if (word.charAt(i) != word.charAt(i-1)) {
                        reducedBuilder.append(word.charAt(i));
                    }
                }
                String reducedWord = reducedBuilder.toString();
                SuggestionProvider.WordSource reducedSource = suggestionProvider.getWordSource(reducedWord);
                if (reducedSource != SuggestionProvider.WordSource.NONE) {
                    candidates.add(new CorrectionCandidate(reducedWord, reducedSource));
                }
            }
        }

        // Generate candidates by deleting a single character
        for (int i = 0; i < word.length(); i++) {
            String deletedWord = word.substring(0, i) + word.substring(i + 1);
            SuggestionProvider.WordSource deletedSource = suggestionProvider.getWordSource(deletedWord);
            if (deletedSource != SuggestionProvider.WordSource.NONE) {
                candidates.add(new CorrectionCandidate(deletedWord, deletedSource));
            }
        }

        List<CorrectionCandidate> sortedCandidates = new ArrayList<>(candidates);
        Collections.sort(sortedCandidates, Comparator.comparingInt(c -> c.source.ordinal()));

        List<String> result = new ArrayList<>();
        for(CorrectionCandidate candidate : sortedCandidates) {
            result.add(candidate.word);
        }

        return result;
    }
}