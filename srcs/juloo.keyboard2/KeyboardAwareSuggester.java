package juloo.keyboard2;

import android.content.Context;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KeyboardAwareSuggester {

    private static final int MAX_SUGGESTIONS = 100;
    private static final int MAX_ALTERNATES_PER_POSITION = 4;
    private static final int MAX_TOTAL_TRIE_NODES = 50000;
    private static final int TIMEOUT_MS = 80;

    private final Context context;
    private final Map<Character, List<Character>> surroundings;
    private final TrieNode root;

    public KeyboardAwareSuggester(Context context) {
        this.context = context;
        this.surroundings = parseSurroundings();
        this.root = new TrieNode();
        loadDictionaries();
    }

    private Map<Character, List<Character>> parseSurroundings() {
        Map<Character, List<Character>> surroundingsMap = new HashMap<>();
        try {
            XmlPullParser parser = context.getResources().getXml(R.xml.surroundings);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && "char".equals(parser.getName())) {
                    char value = parser.getAttributeValue(null, "value").charAt(0);
                    String neighborsStr = parser.getAttributeValue(null, "neighbors");
                    List<Character> neighbors = new ArrayList<>();
                    neighbors.add(value); // a character is its own neighbor
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

    private void loadDictionaries() {
        loadDictionary("custom.txt", WordSource.CUSTOM);
        loadDictionary(R.raw.common, WordSource.COMMON);
        loadDictionary(R.raw.wordlist, WordSource.WORDLIST);
    }

    private void loadDictionary(String filename, WordSource source) {
        File file = new File(context.getFilesDir(), filename);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                insert(line.trim(), source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDictionary(int resourceId, WordSource source) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resourceId)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                insert(line.trim(), source);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insert(String word, WordSource source) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        current.isEndOfWord = true;
        current.source = source;
    }

    public List<Suggestion> suggest(String token) {
        if (token == null || token.isEmpty()) {
            return Collections.emptyList();
        }
        String normalizedToken = token.toLowerCase();
        List<Suggestion> suggestions = new ArrayList<>();
        Set<String> foundWords = new HashSet<>();
        long startTime = System.currentTimeMillis();
        int visitedNodes = 0;

        List<List<Character>> allowedChars = new ArrayList<>();
        for (char c : normalizedToken.toCharArray()) {
            List<Character> neighbors = surroundings.get(c);
            if (neighbors == null) {
                neighbors = Collections.singletonList(c);
            }
            allowedChars.add(neighbors.subList(0, Math.min(neighbors.size(), MAX_ALTERNATES_PER_POSITION)));
        }

        search(root, "", 0, normalizedToken, allowedChars, suggestions, foundWords, startTime, visitedNodes);

        Collections.sort(suggestions, (s1, s2) -> Double.compare(s2.score, s1.score));

        return suggestions.subList(0, Math.min(suggestions.size(), MAX_SUGGESTIONS));
    }

    private void search(TrieNode node, String currentWord, int index, String token, List<List<Character>> allowedChars,
                        List<Suggestion> suggestions, Set<String> foundWords, long startTime, int visitedNodes) {
        if (System.currentTimeMillis() - startTime > TIMEOUT_MS || visitedNodes > MAX_TOTAL_TRIE_NODES) {
            return;
        }

        if (index == token.length()) {
            if (node.isEndOfWord && !foundWords.contains(currentWord)) {
                double score = calculateScore(currentWord, token, node.source, allowedChars);
                suggestions.add(new Suggestion(currentWord, score, node.source));
                foundWords.add(currentWord);
            }
            return;
        }

        for (char c : allowedChars.get(index)) {
            TrieNode child = node.children.get(c);
            if (child != null) {
                search(child, currentWord + c, index + 1, token, allowedChars, suggestions, foundWords, startTime, visitedNodes + 1);
            }
        }
    }

    private double calculateScore(String word, String token, WordSource source, List<List<Character>> allowedChars) {
        int substitutions = 0;
        double keyboardDistance = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != token.charAt(i)) {
                substitutions++;
                List<Character> neighbors = allowedChars.get(i);
                int distance = neighbors.indexOf(word.charAt(i));
                if (distance != -1) {
                    keyboardDistance += distance;
                }
            }
        }
        int sourcePriority = 0;
        if (source == WordSource.CUSTOM) {
            sourcePriority = 3;
        } else if (source == WordSource.COMMON) {
            sourcePriority = 2;
        } else if (source == WordSource.WORDLIST) {
            sourcePriority = 1;
        }
        return sourcePriority * 1000 - substitutions * 20 - (keyboardDistance / word.length()) * 10;
    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord;
        WordSource source;
    }

    public enum WordSource {
        CUSTOM, COMMON, WORDLIST
    }

    public static class Suggestion {
        public final String word;
        public final double score;
        public final WordSource source;

        public Suggestion(String word, double score, WordSource source) {
            this.word = word;
            this.score = score;
            this.source = source;
        }
    }
}
