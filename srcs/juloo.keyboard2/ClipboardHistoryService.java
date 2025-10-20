package juloo.keyboard2;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ClipboardHistoryService {
    private static final String TAG = "ClipboardHistoryService";
    private static final String PERSIST_FILE_NAME = "clipboard_history.json";
    public static final String RELOAD_CLIPBOARD_HISTORY_ACTION = "juloo.keyboard2.RELOAD_CLIPBOARD_HISTORY";

    private static ClipboardHistoryService _service = null;
    private static ClipboardPasteCallback _paste_callback = null;

    private final Context context;
    private final ClipboardManager clipboardManager;
    private final List<ClipboardItem> items;
    private OnClipboardHistoryChange listener = null;

    public static void on_startup(Context ctx, ClipboardPasteCallback cb) {
        get_service(ctx);
        _paste_callback = cb;
    }

    public static ClipboardHistoryService get_service(Context ctx) {
        if (_service == null) {
            _service = new ClipboardHistoryService(ctx.getApplicationContext());
        }
        return _service;
    }

    public static void set_history_enabled(boolean e) {
        Config.globalConfig().set_clipboard_history_enabled(e);
        if (_service == null) return;
        if (e) {
            _service.addCurrentClip();
        } else {
            _service.clearHistory();
        }
    }

    public static void paste(String clip) {
        if (_paste_callback != null) {
            _paste_callback.paste_from_clipboard_pane(clip);
        }
    }

    private ClipboardHistoryService(Context ctx) {
        this.context = ctx;
        this.items = new ArrayList<>();
        this.clipboardManager = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
        this.clipboardManager.addPrimaryClipChangedListener(new SystemListener());
        loadItems();

        IntentFilter filter = new IntentFilter(RELOAD_CLIPBOARD_HISTORY_ACTION);
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                loadItems();
                notifyHistoryChange();
            }
        }, filter);
    }

    public List<ClipboardItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addClip(String clip) {
        if (!Config.globalConfig().clipboard_history_enabled || clip == null || clip.trim().isEmpty()) {
            return;
        }

        long currentTime = System.currentTimeMillis();

        ClipboardItem newItem = new ClipboardItem(clip, currentTime, false);

        // Remove existing item to update its timestamp and move it to the top
        items.remove(newItem);
        items.add(newItem);

        // Trim unpinned items if necessary
        trimHistory();
        sortItems();
        persistItems();
        notifyHistoryChange();
    }

    public void removeItem(ClipboardItem item) {
        if (items.remove(item)) {
            // If the removed item was the most recent one, clear the system clipboard
            if (isSystemClipboard(item.getText())) {
                if (VERSION.SDK_INT >= 28) {
                    clipboardManager.clearPrimaryClip();
                } else {
                    clipboardManager.setPrimaryClip(ClipData.newPlainText("", ""));
                }
            }
            persistItems();
            notifyHistoryChange();
        }
    }

    public void togglePin(ClipboardItem item) {
        int index = items.indexOf(item);
        if (index != -1) {
            ClipboardItem currentItem = items.get(index);
            currentItem.setPinned(!currentItem.isPinned());
            currentItem.setTimestamp(System.currentTimeMillis()); // Update timestamp for sorting
            sortItems();
            persistItems();
            notifyHistoryChange();
        }
    }

    public void clearHistory() {
        items.clear();
        persistItems();
        notifyHistoryChange();
    }

    public void setOnClipboardHistoryChange(OnClipboardHistoryChange l) {
        listener = l;
    }

    private void sortItems() {
        Collections.sort(items, new Comparator<ClipboardItem>() {
            @Override
            public int compare(ClipboardItem o1, ClipboardItem o2) {
                if (o1.isPinned() == o2.isPinned()) {
                    // Both pinned or both unpinned, sort by timestamp
                    // Unpinned: newest first (desc)
                    // Pinned: by pinned time (asc)
                    return o1.isPinned() ? Long.compare(o1.getTimestamp(), o2.getTimestamp())
                                         : Long.compare(o2.getTimestamp(), o1.getTimestamp());
                }
                // Pinned items go to the bottom
                return o1.isPinned() ? 1 : -1;
            }
        });
    }

    private void trimHistory() {
        // No-op to keep all items
    }

    private void notifyHistoryChange() {
        if (listener != null) {
            listener.on_clipboard_history_change();
        }
    }

    private void addCurrentClip() {
        ClipData clip = clipboardManager.getPrimaryClip();
        if (clip == null) return;
        int count = clip.getItemCount();
        for (int i = 0; i < count; i++) {
            CharSequence text = clip.getItemAt(i).getText();
            if (text != null) {
                addClip(text.toString());
            }
        }
    }

    private boolean isSystemClipboard(String text) {
        ClipData clip = clipboardManager.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            CharSequence clipText = clip.getItemAt(0).getText();
            return clipText != null && clipText.toString().equals(text);
        }
        return false;
    }

    private void loadItems() {
        File file = new File(context.getFilesDir(), PERSIST_FILE_NAME);
        if (!file.exists()) {
            migrateFromPrefs();
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            JSONArray jsonArray = new JSONArray(sb.toString());
            items.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                items.add(ClipboardItem.fromJSON(jsonArray.getJSONObject(i)));
            }
            sortItems();
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Failed to load clipboard history", e);
        }
    }

    private void persistItems() {
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            JSONArray jsonArray = new JSONArray();
            for (ClipboardItem item : items) {
                try {
                    jsonArray.put(item.toJSON());
                } catch (JSONException e) {
                    Log.e(TAG, "Failed to convert item to JSON", e);
                }
            }

            String jsonPayload = jsonArray.toString();

            // Persist to internal storage
            File file = new File(context.getFilesDir(), PERSIST_FILE_NAME);
            try (FileOutputStream fos = new FileOutputStream(file);
                 OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
                writer.write(jsonPayload);
            } catch (IOException e) {
                Log.e(TAG, "Failed to persist clipboard history", e);
            }

            // Export directly to external storage
            new DataSyncService(context).exportClipboard(jsonPayload);
        });
    }

    private void migrateFromPrefs() {
        SharedPreferences store = context.getSharedPreferences("pinned_clipboards", Context.MODE_PRIVATE);
        String arr_s = store.getString("pinned", null);
        if (arr_s == null) return;

        try {
            JSONArray arr = new JSONArray(arr_s);
            long currentTime = System.currentTimeMillis();
            for (int i = 0; i < arr.length(); i++) {
                String text = arr.getString(i);
                // Assign a slightly different timestamp to maintain order
                items.add(new ClipboardItem(text, currentTime + i, true));
            }
            sortItems();
            persistItems();
            // Clear the old prefs
            store.edit().clear().apply();
        } catch (JSONException e) {
            Log.e(TAG, "Failed to migrate pinned clips", e);
        }
    }

    public void importFromUri(Uri uri) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = context.getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to read import file", e);
            return;
        }

        try {
            JSONArray jsonArray = new JSONArray(sb.toString());
            Set<ClipboardItem> existingItems = new HashSet<>(items);
            int importedCount = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                ClipboardItem newItem = ClipboardItem.fromJSON(jsonArray.getJSONObject(i));
                if (!existingItems.contains(newItem)) {
                    items.add(newItem);
                    importedCount++;
                }
            }
            if (importedCount > 0) {
                sortItems();
                persistItems();
                notifyHistoryChange();
            }
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse import file", e);
        }
    }

    public void exportToUri(Uri uri) {
        JSONArray jsonArray = new JSONArray();
        for (ClipboardItem item : items) {
            try {
                jsonArray.put(item.toJSON());
            } catch (JSONException e) {
                Log.e(TAG, "Failed to convert item to JSON for export", e);
            }
        }

        try (OutputStream os = context.getContentResolver().openOutputStream(uri);
             OutputStreamWriter writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            writer.write(jsonArray.toString(2)); // Indent for readability
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Failed to export clipboard history", e);
        }
    }

    public interface OnClipboardHistoryChange {
        void on_clipboard_history_change();
    }

    public interface ClipboardPasteCallback {
        void paste_from_clipboard_pane(String content);
    }

    private final class SystemListener implements ClipboardManager.OnPrimaryClipChangedListener {
        @Override
        public void onPrimaryClipChanged() {
            addCurrentClip();
        }
    }
}