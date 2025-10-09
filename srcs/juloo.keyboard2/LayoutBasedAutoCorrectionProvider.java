package juloo.keyboard2;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LayoutBasedAutoCorrectionProvider {

    private final Map<String, String> corrections = new HashMap<>();
    private final Context context;
    private volatile boolean loaded = false;

    public LayoutBasedAutoCorrectionProvider(Context context) {
        this.context = context;
    }

    public void updateLayout(KeyboardData keyboardData) {
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            // Reset state for the new layout
            loaded = false;
            corrections.clear();
            // Generate new corrections
            generateCorrections(keyboardData);
            loaded = true;
        });
    }

    private void generateCorrections(KeyboardData keyboardData) {
        if (keyboardData == null) {
            return;
        }

        // 1. Get the adjacency map from our analyzer
        Map<Character, List<Character>> adjacencyMap = KeyboardLayoutAnalyzer.getAdjacencyMap(keyboardData);
        if (adjacencyMap.isEmpty()) {
            return;
        }

        // 2. Load the base dictionary of correct words
        Set<String> dictionary = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(context.getResources().openRawResource(R.raw.common)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return; // Cannot proceed without a dictionary
        }

        // 3. For each correct word, generate likely misspellings
        for (String word : dictionary) {
            if (word.length() < 2) continue;

            // A. Generate substitutions based on key adjacency (fat-finger errors)
            for (int i = 0; i < word.length(); i++) {
                char originalChar = word.charAt(i);
                if (adjacencyMap.containsKey(originalChar)) {
                    for (char adjacentChar : adjacencyMap.get(originalChar)) {
                        String misspelled = word.substring(0, i) + adjacentChar + word.substring(i + 1);
                        // Do not overwrite a correction for a different word
                        if (!corrections.containsKey(misspelled)) {
                           corrections.put(misspelled, word);
                        }
                    }
                }
            }

            // B. Generate transpositions (swapped letters)
            for (int i = 0; i < word.length() - 1; i++) {
                char[] chars = word.toCharArray();
                char temp = chars[i];
                chars[i] = chars[i + 1];
                chars[i + 1] = temp;
                String misspelled = new String(chars);
                 if (!corrections.containsKey(misspelled)) {
                    corrections.put(misspelled, word);
                }
            }
        }
    }

    /**
     * Gets the correction for a given word.
     * @param word The word to check, expected to be in lowercase.
     * @return The corrected word, or null if no correction is found.
     */
    public String getCorrection(String word) {
        if (!loaded) {
            return null;
        }
        return corrections.get(word);
    }
}