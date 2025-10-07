package juloo.keyboard2;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuggestionProvider {

    private static final int MAX_SUGGESTIONS = 20;

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord;
    }

    private final TrieNode commonRoot;
    private final TrieNode wordlistRoot;
    private final Context context;

    public SuggestionProvider(Context context) {
        this.context = context;
        commonRoot = new TrieNode();
        wordlistRoot = new TrieNode();
        loadDictionary(R.raw.common, commonRoot);
        loadDictionary(R.raw.wordlist, wordlistRoot);
    }

    private void loadDictionary(int resourceId, TrieNode root) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getResources().openRawResource(resourceId)));
            String line;
            while ((line = reader.readLine()) != null) {
                insert(line.trim(), root);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insert(String word, TrieNode root) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        current.isEndOfWord = true;
    }

    public List<String> getSuggestions(String prefix) {
        List<String> suggestions = new ArrayList<>();
        if (prefix == null || prefix.isEmpty()) {
            return suggestions;
        }

        // Find suggestions from common words first
        TrieNode commonPrefixNode = findPrefixNode(prefix, commonRoot);
        if (commonPrefixNode != null) {
            findAllWords(commonPrefixNode, prefix, suggestions);
        }

        // Then, find suggestions from the wordlist
        if (suggestions.size() < MAX_SUGGESTIONS) {
            TrieNode wordlistPrefixNode = findPrefixNode(prefix, wordlistRoot);
            if (wordlistPrefixNode != null) {
                findAllWords(wordlistPrefixNode, prefix, suggestions);
            }
        }

        return suggestions;
    }

    private TrieNode findPrefixNode(String prefix, TrieNode root) {
        TrieNode current = root;
        for (char ch : prefix.toLowerCase().toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return null;
            }
            current = node;
        }
        return current;
    }

    private void findAllWords(TrieNode node, String prefix, List<String> suggestions) {
        if (suggestions.size() >= MAX_SUGGESTIONS) {
            return;
        }
        if (node.isEndOfWord) {
            if (!suggestions.contains(prefix)) {
                suggestions.add(prefix);
            }
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            findAllWords(entry.getValue(), prefix + entry.getKey(), suggestions);
            if (suggestions.size() >= MAX_SUGGESTIONS) {
                return;
            }
        }
    }
}