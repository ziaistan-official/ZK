package juloo.keyboard2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyboardLayoutAnalyzer {

    public static Map<Character, List<Character>> getAdjacencyMap(KeyboardData keyboardData) {
        Map<Character, List<Character>> adjacencyMap = new HashMap<>();
        if (keyboardData == null || keyboardData.rows == null) {
            return adjacencyMap;
        }

        List<KeyboardData.Key> allKeys = new ArrayList<>();
        for (KeyboardData.Row row : keyboardData.rows) {
            if (row.keys != null) {
                for (KeyboardData.Key key : row.keys) {
                    allKeys.add(key);
                }
            }
        }

        for (KeyboardData.Key key : allKeys) {
            char keyChar = getKeyChar(key);
            if (keyChar == 0) continue;

            List<Character> neighbors = new ArrayList<>();
            for (KeyboardData.Key otherKey : allKeys) {
                if (key == otherKey) continue;

                if (isAdjacent(key, otherKey, keyboardData.rowHeight)) {
                    char otherKeyChar = getKeyChar(otherKey);
                    if (otherKeyChar != 0) {
                        neighbors.add(otherKeyChar);
                    }
                }
            }
            adjacencyMap.put(keyChar, neighbors);
        }

        return adjacencyMap;
    }

    private static char getKeyChar(KeyboardData.Key key) {
        if (key.kvs != null && key.kvs.length > 0) {
            KeyValue kv = key.kvs[0];
            if (kv != null && kv.getKind() == KeyValue.Kind.Char) {
                return Character.toLowerCase(kv.getChar());
            }
        }
        return 0; // Not a character key
    }

    private static boolean isAdjacent(KeyboardData.Key key1, KeyboardData.Key key2, float rowHeight) {
        float key1CenterX = key1.x + key1.width / 2;
        float key1CenterY = key1.y + rowHeight / 2;
        float key2CenterX = key2.x + key2.width / 2;
        float key2CenterY = key2.y + rowHeight / 2;

        float dx = Math.abs(key1CenterX - key2CenterX);
        float dy = Math.abs(key1CenterY - key2CenterY);

        // A simple adjacency check: if the distance between centers is less than 1.5 times the key width/height
        float maxDistanceX = (key1.width + key2.width) / 2 * 1.5f;
        float maxDistanceY = rowHeight * 1.5f;

        return dx < maxDistanceX && dy < maxDistanceY;
    }
}
