package juloo.keyboard2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyboardLayoutAnalyzer {

    /**
     * Analyzes a keyboard layout to determine which keys are adjacent to each other.
     * @param keyboardData The keyboard layout to analyze.
     * @return A map where each key is a character and the value is a list of adjacent characters.
     */
    public static Map<Character, List<Character>> getAdjacencyMap(KeyboardData keyboardData) {
        Map<Character, List<Character>> adjacencyMap = new HashMap<>();
        List<KeyboardData.Key> allKeys = new ArrayList<>();

        // Flatten the list of keys and calculate average key width
        float totalWidth = 0;
        int keyCount = 0;
        for (KeyboardData.Row row : keyboardData.rows) {
            for (KeyboardData.Key key : row.keys) {
                // Only consider keys that are actual, single characters
                if (key.kv != null && key.kv.getKind() == KeyValue.Kind.Char && key.width > 0) {
                    allKeys.add(key);
                    totalWidth += key.width;
                    keyCount++;
                }
            }
        }

        if (keyCount == 0) {
            return adjacencyMap; // No keys to analyze
        }

        float avgWidth = totalWidth / keyCount;
        // A threshold of 1.5 times the average width should capture horizontal, vertical, and diagonal neighbors
        float threshold = avgWidth * 1.5f;

        // Build the adjacency map
        for (KeyboardData.Key key1 : allKeys) {
            char char1 = Character.toLowerCase(key1.kv.getChar());
            if (!Character.isLetter(char1)) {
                continue;
            }

            List<Character> neighbors = new ArrayList<>();
            float centerX1 = key1.x + key1.width / 2f;
            float centerY1 = key1.y + key1.height / 2f;

            for (KeyboardData.Key key2 : allKeys) {
                if (key1 == key2) {
                    continue;
                }
                 if (key2.kv == null || key2.kv.getKind() != KeyValue.Kind.Char) {
                    continue;
                }

                float centerX2 = key2.x + key2.width / 2f;
                float centerY2 = key2.y + key2.height / 2f;

                float distance = (float) Math.sqrt(Math.pow(centerX1 - centerX2, 2) + Math.pow(centerY1 - centerY2, 2));

                if (distance < threshold) {
                    char char2 = Character.toLowerCase(key2.kv.getChar());
                    if (Character.isLetter(char2) && char1 != char2) {
                        if (!neighbors.contains(char2)) {
                            neighbors.add(char2);
                        }
                    }
                }
            }
            // Merge with existing entries if a character appears multiple times (e.g., in pop-up keys)
            if (adjacencyMap.containsKey(char1)) {
                List<Character> existingNeighbors = adjacencyMap.get(char1);
                for(char neighbor : neighbors) {
                    if (!existingNeighbors.contains(neighbor)) {
                        existingNeighbors.add(neighbor);
                    }
                }
            } else {
                adjacencyMap.put(char1, neighbors);
            }
        }

        return adjacencyMap;
    }
}