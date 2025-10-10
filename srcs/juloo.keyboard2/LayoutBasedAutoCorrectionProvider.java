package juloo.keyboard2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

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
     * Gets a list of potential corrections for a given word, generated on-the-fly
     * using multiple typo-detection algorithms running in parallel.
     * @param word The word to check, expected to be in lowercase.
     * @return A sorted list of corrected words, or an empty list if no correction is needed or found.
     */
    public List<String> getCorrections(String word) {
        if (!layoutReady || suggestionProvider == null || word == null || word.isEmpty()) {
            return Collections.emptyList();
        }

        // If the original word is valid, no correction is needed.
        if (suggestionProvider.isValidWord(word)) {
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
        if (!layoutReady || suggestionProvider == null || word == null || word.isEmpty()) {
            return Collections.emptyList();
        }

        Set<CorrectionCandidate> candidates = Collections.newSetFromMap(new ConcurrentHashMap<>());
        CountDownLatch latch = new CountDownLatch(6);

        // Run all correction algorithms in parallel
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            getSubstitutionCandidates(word, candidates);
            latch.countDown();
        });
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            getDeletionCandidates(word, candidates);
            latch.countDown();
        });
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            getInsertionCandidates(word, candidates);
            latch.countDown();
        });
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            getTranspositionCandidates(word, candidates);
            latch.countDown();
        });
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            getReversalCandidates(word, candidates);
            latch.countDown();
        });
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            getDoublingSinglingCandidates(word, candidates);
            latch.countDown();
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        }

        List<CorrectionCandidate> sortedCandidates = new ArrayList<>(candidates);
        Collections.sort(sortedCandidates, Comparator.comparingInt(c -> c.source.ordinal()));

        List<String> result = new ArrayList<>();
        for (CorrectionCandidate candidate : sortedCandidates) {
            result.add(candidate.word);
        }

        return result;
    }

    private void getSubstitutionCandidates(String word, Set<CorrectionCandidate> candidates) {
        if (adjacencyMap == null) return;
        for (int i = 0; i < word.length(); i++) {
            List<Character> neighbors = adjacencyMap.get(word.charAt(i));
            if (neighbors != null) {
                StringBuilder builder = new StringBuilder(word);
                for (char neighbor : neighbors) {
                    builder.setCharAt(i, neighbor);
                    String candidateWord = builder.toString();
                    if (suggestionProvider.isValidWord(candidateWord)) {
                        candidates.add(new CorrectionCandidate(candidateWord, suggestionProvider.getWordSource(candidateWord)));
                    }
                }
            }
        }
    }

    private void getDeletionCandidates(String word, Set<CorrectionCandidate> candidates) {
        if (word.length() <= 1) return;
        for (int i = 0; i < word.length(); i++) {
            String candidateWord = word.substring(0, i) + word.substring(i + 1);
            if (suggestionProvider.isValidWord(candidateWord)) {
                candidates.add(new CorrectionCandidate(candidateWord, suggestionProvider.getWordSource(candidateWord)));
            }
        }
    }

    private void getInsertionCandidates(String word, Set<CorrectionCandidate> candidates) {
        if (adjacencyMap == null || adjacencyMap.isEmpty()) return;

        for (int i = 0; i <= word.length(); i++) {
            Set<Character> charsToInsert = new HashSet<>();

            // Get neighbors of the character to the left of the insertion point
            if (i > 0) {
                List<Character> leftNeighbors = adjacencyMap.get(word.charAt(i - 1));
                if (leftNeighbors != null) {
                    charsToInsert.addAll(leftNeighbors);
                }
            }

            // Get neighbors of the character to the right of the insertion point
            if (i < word.length()) {
                List<Character> rightNeighbors = adjacencyMap.get(word.charAt(i));
                if (rightNeighbors != null) {
                    charsToInsert.addAll(rightNeighbors);
                }
            }

            for (char c : charsToInsert) {
                String candidateWord = new StringBuilder(word).insert(i, c).toString();
                if (suggestionProvider.isValidWord(candidateWord)) {
                    candidates.add(new CorrectionCandidate(candidateWord, suggestionProvider.getWordSource(candidateWord)));
                }
            }
        }
    }

    private void getTranspositionCandidates(String word, Set<CorrectionCandidate> candidates) {
        if (word.length() <= 4) return; // Only apply to words with more than 4 characters.
        for (int i = 0; i < word.length() - 1; i++) {
            char[] chars = word.toCharArray();
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
            String candidateWord = new String(chars);
            if (suggestionProvider.isValidWord(candidateWord)) {
                candidates.add(new CorrectionCandidate(candidateWord, suggestionProvider.getWordSource(candidateWord)));
            }
        }
    }

    private void getReversalCandidates(String word, Set<CorrectionCandidate> candidates) {
        if (word.length() < 2) return;
        String reversedWord = new StringBuilder(word).reverse().toString();
        if (suggestionProvider.isValidWord(reversedWord)) {
            candidates.add(new CorrectionCandidate(reversedWord, suggestionProvider.getWordSource(reversedWord)));
        }
    }

    private void getDoublingSinglingCandidates(String word, Set<CorrectionCandidate> candidates) {
        // Singling (e.g., "helllo" -> "hello")
        if (word.matches(".*(.)\\1.*")) {
            StringBuilder reducedBuilder = new StringBuilder();
            if (word.length() > 0) {
                reducedBuilder.append(word.charAt(0));
                for (int i = 1; i < word.length(); i++) {
                    if (word.charAt(i) != word.charAt(i - 1)) {
                        reducedBuilder.append(word.charAt(i));
                    }
                }
                String reducedWord = reducedBuilder.toString();
                if (suggestionProvider.isValidWord(reducedWord)) {
                    candidates.add(new CorrectionCandidate(reducedWord, suggestionProvider.getWordSource(reducedWord)));
                }
            }
        }

        // Doubling (e.g., "helo" -> "hello")
        for (int i = 0; i < word.length(); i++) {
            String doubledWord = word.substring(0, i + 1) + word.charAt(i) + word.substring(i + 1);
            if (suggestionProvider.isValidWord(doubledWord)) {
                candidates.add(new CorrectionCandidate(doubledWord, suggestionProvider.getWordSource(doubledWord)));
            }
        }
    }
}