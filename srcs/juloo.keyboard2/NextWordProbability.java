package juloo.keyboard2;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NextWordProbability {

    private static final String PROBABILITY_FILE = "next_word_prob.txt";
    private static final String EXTERNAL_DIR_NAME = "ziaistan_keyboard_backup";
    private final File probabilityFile;

    private final Map<String, Integer> wordToId = new HashMap<>();
    private final Map<Integer, String> idToWord = new HashMap<>();
    private final Map<Integer, Map<Integer, Integer>> nextWordCounts = new HashMap<>();
    private int nextId = 0;
    private final Handler saveHandler = new Handler(Looper.getMainLooper());
    private final Runnable saveRunnable = this::saveProbabilitiesInternal;

    public NextWordProbability(Context context) {
        File backupDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_DIR_NAME);
        if (!backupDir.exists()) {
            backupDir.mkdirs();
        }
        this.probabilityFile = new File(backupDir, PROBABILITY_FILE);
        loadProbabilities();
    }

    private void loadProbabilities() {
        if (!probabilityFile.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(probabilityFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length < 2) continue;
                String word = parts[0];
                int id = getOrCreateId(word);
                for (int i = 1; i < parts.length; i++) {
                    String[] nextWordParts = parts[i].split(":");
                    if (nextWordParts.length != 2) continue;
                    String nextWord = nextWordParts[0];
                    int nextId = getOrCreateId(nextWord);
                    int count = Integer.parseInt(nextWordParts[1]);
                    nextWordCounts.computeIfAbsent(id, k -> new HashMap<>()).put(nextId, count);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveProbabilitiesInternal() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(probabilityFile))) {
            for (Map.Entry<Integer, Map<Integer, Integer>> entry : nextWordCounts.entrySet()) {
                String word = idToWord.get(entry.getKey());
                if (word == null) continue;
                writer.write(word);
                for (Map.Entry<Integer, Integer> nextEntry : entry.getValue().entrySet()) {
                    String nextWord = idToWord.get(nextEntry.getKey());
                    if (nextWord == null) continue;
                    writer.write(" " + nextWord + ":" + nextEntry.getValue());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getOrCreateId(String word) {
        if (wordToId.containsKey(word)) {
            return wordToId.get(word);
        }
        wordToId.put(word, nextId);
        idToWord.put(nextId, word);
        return nextId++;
    }

    public void trackWordSequence(String previousWord, String currentWord) {
        if (previousWord == null || previousWord.isEmpty() || currentWord == null || currentWord.isEmpty()) {
            return;
        }
        previousWord = previousWord.replaceAll("[^a-zA-Z]", "");
        currentWord = currentWord.replaceAll("[^a-zA-Z]", "");
        if (previousWord.isEmpty() || currentWord.isEmpty()) {
            return;
        }
        int prevId = getOrCreateId(previousWord);
        int currentId = getOrCreateId(currentWord);
        Map<Integer, Integer> counts = nextWordCounts.computeIfAbsent(prevId, k -> new HashMap<>());
        int currentCount = counts.getOrDefault(currentId, 0);
        if (currentCount < 10) {
            counts.put(currentId, currentCount + 1);
        }
        saveProbabilities();
    }

    public void saveProbabilities() {
        saveHandler.removeCallbacks(saveRunnable);
        saveHandler.postDelayed(saveRunnable, 2000);
    }

    public List<String> getNextWordSuggestions(String word) {
        if (word == null || !wordToId.containsKey(word)) {
            return Collections.emptyList();
        }
        int id = wordToId.get(word);
        Map<Integer, Integer> counts = nextWordCounts.get(id);
        if (counts == null || counts.isEmpty()) {
            return Collections.emptyList();
        }

        List<Map.Entry<Integer, Integer>> sortedCounts = new ArrayList<>(counts.entrySet());
        Collections.sort(sortedCounts, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        List<String> suggestions = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sortedCounts) {
            suggestions.add(idToWord.get(entry.getKey()));
        }
        return suggestions;
    }
}