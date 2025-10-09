package juloo.keyboard2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyboardLayoutAnalyzer {

    private static class KeyInfo {
        final float centerX;
        final float centerY;
        final char character;

        KeyInfo(float centerX, float centerY, char character) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.character = character;
        }
    }

    /**
     * Analyzes a keyboard layout to determine which keys are adjacent to each other.
     * @param keyboardData The keyboard layout to analyze.
     * @return A map where each key is a character and the value is a list of adjacent characters.
     */
    public static Map<Character, List<Character>> getAdjacencyMap(KeyboardData keyboardData) {
        Map<Character, List<Character>> adjacencyMap = new HashMap<>();
        List<KeyInfo> keyInfos = new ArrayList<>();

        float totalWidth = 0;
        int keyCount = 0;
        float currentY = 0;

        // First, iterate to calculate positions and gather key info
        for (KeyboardData.Row row : keyboardData.rows) {
            currentY += row.shift;
            float currentX = 0;
            for (KeyboardData.Key key : row.keys) {
                currentX += key.shift;
                // Use the primary key value (index 0)
                KeyValue kv = key.getKeyValue(0);

                if (kv != null && kv.getKind() == KeyValue.Kind.Char && key.width > 0) {
                    char character = Character.toLowerCase(kv.getChar());
                    if (Character.isLetter(character)) {
                        keyInfos.add(new KeyInfo(currentX + key.width / 2f, currentY + row.height / 2f, character));
                        totalWidth += key.width;
                        keyCount++;
                    }
                }
                currentX += key.width;
            }
            currentY += row.height;
        }

        if (keyCount == 0) {
            return adjacencyMap; // No keys to analyze
        }

        float avgWidth = totalWidth / keyCount;
        // A threshold of 1.5 times the average width should capture horizontal, vertical, and diagonal neighbors
        float threshold = avgWidth * 1.5f;

        // Build the adjacency map by calculating distances
        for (KeyInfo info1 : keyInfos) {
            List<Character> neighbors = new ArrayList<>();
            for (KeyInfo info2 : keyInfos) {
                if (info1 == info2) {
                    continue;
                }

                float distance = (float) Math.sqrt(Math.pow(info1.centerX - info2.centerX, 2) + Math.pow(info1.centerY - info2.centerY, 2));

                if (distance < threshold) {
                    if (info1.character != info2.character && !neighbors.contains(info2.character)) {
                         neighbors.add(info2.character);
                    }
                }
            }

            // Merge with existing entries if a character appears multiple times
            if (adjacencyMap.containsKey(info1.character)) {
                List<Character> existingNeighbors = adjacencyMap.get(info1.character);
                for (char neighbor : neighbors) {
                    if (!existingNeighbors.contains(neighbor)) {
                        existingNeighbors.add(neighbor);
                    }
                }
            } else {
                adjacencyMap.put(info1.character, neighbors);
            }
        }

        return adjacencyMap;
    }
}