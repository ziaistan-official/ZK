package juloo.keyboard2;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataSyncService {

    private static final String TAG = "DataSyncService";
    private static final String EXTERNAL_DIR_NAME = "ziaistan_keyboard_backup";
    private static final String CUSTOM_DICT_FILENAME = "custom.txt";
    private static final String CLIPBOARD_EXPORT_FILENAME = "clipboard_export.json";
    private static final String CLIPBOARD_INTERNAL_FILENAME = "clipboard_history.json";

    private final Context context;

    public DataSyncService(Context context) {
        this.context = context;
    }

    public void importData() {
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            importDictionaryInternal();
            importClipboardInternal();
        });
    }

    public void importDictionary() {
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(this::importDictionaryInternal);
    }

    private void importDictionaryInternal() {
        importFile(CUSTOM_DICT_FILENAME, CUSTOM_DICT_FILENAME);
    }

    public void importClipboard() {
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(this::importClipboardInternal);
    }

    private void importClipboardInternal() {
        File externalDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_DIR_NAME);
        File sourceFile = new File(externalDir, CLIPBOARD_EXPORT_FILENAME);
        File destFile = new File(context.getFilesDir(), CLIPBOARD_INTERNAL_FILENAME);

        if (!sourceFile.exists()) {
            Log.e(TAG, "Import failed: Source file does not exist: " + sourceFile.getAbsolutePath());
            return;
        }

        Set<ClipboardItem> existingItems = readClipboardFile(destFile);
        Set<ClipboardItem> newItems = readClipboardFile(sourceFile);

        existingItems.addAll(newItems);
        writeClipboardFile(destFile, existingItems);
        Log.d(TAG, "Successfully merged clipboard data.");
    }

    private Set<ClipboardItem> readClipboardFile(File file) {
        Set<ClipboardItem> items = new HashSet<>();
        if (!file.exists()) {
            return items;
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            String json = new String(data);
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                items.add(new ClipboardItem(obj));
            }
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Failed to read clipboard file: " + file.getAbsolutePath(), e);
        }
        return items;
    }

    private void writeClipboardFile(File file, Set<ClipboardItem> items) {
        JSONArray jsonArray = new JSONArray();
        for (ClipboardItem item : items) {
            jsonArray.put(item.toJson());
        }

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(jsonArray.toString().getBytes());
        } catch (IOException e) {
            Log.e(TAG, "Failed to write clipboard file: " + file.getAbsolutePath(), e);
        }
    }

    public void exportDictionary() {
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> exportFile(CUSTOM_DICT_FILENAME, CUSTOM_DICT_FILENAME));
    }

    public void exportClipboard(String jsonPayload) {
        KeyboardExecutors.HIGH_PRIORITY_EXECUTOR.execute(() -> {
            File externalDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_DIR_NAME);
            if (!externalDir.exists()) {
                if (!externalDir.mkdirs()) {
                    Log.e(TAG, "Export failed: Could not create external directory: " + externalDir.getAbsolutePath());
                    return;
                }
            }

            File destFile = new File(externalDir, CLIPBOARD_EXPORT_FILENAME);
            Log.d(TAG, "Attempting to export clipboard to: " + destFile.getAbsolutePath());

            try (FileOutputStream fos = new FileOutputStream(destFile)) {
                fos.write(jsonPayload.getBytes());
                Log.d(TAG, "Successfully exported " + CLIPBOARD_EXPORT_FILENAME);
            } catch (IOException e) {
                Log.e(TAG, "Failed to export " + CLIPBOARD_EXPORT_FILENAME, e);
            }
        });
    }

    private void importFile(String sourceFileName, String destFileName) {
        File externalDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_DIR_NAME);
        File sourceFile = new File(externalDir, sourceFileName);
        File destFile = new File(context.getFilesDir(), destFileName);

        if (!sourceFile.exists()) {
            Log.e(TAG, "Import failed: Source file does not exist: " + sourceFile.getAbsolutePath());
            return;
        }

        try (FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
             FileChannel destChannel = new FileOutputStream(destFile).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            Log.d(TAG, "Successfully imported " + sourceFileName);
        } catch (IOException e) {
            Log.e(TAG, "Failed to import " + sourceFileName, e);
        }
    }

    private void exportFile(String destFileName, String sourceFileName) {
        File internalFile = new File(context.getFilesDir(), sourceFileName);
        if (!internalFile.exists()) {
            return; // Don't log error if file doesn't exist, it might be the first time
        }

        File externalDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_DIR_NAME);
        if (!externalDir.exists()) {
            if (!externalDir.mkdirs()) {
                Log.e(TAG, "Export failed: Could not create external directory: " + externalDir.getAbsolutePath());
                return;
            }
        }

        File destFile = new File(externalDir, destFileName);
        try (FileChannel sourceChannel = new FileInputStream(internalFile).getChannel();
             FileChannel destChannel = new FileOutputStream(destFile).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            Log.d(TAG, "Successfully exported " + destFileName);
        } catch (IOException e) {
            Log.e(TAG, "Failed to export " + destFileName, e);
        }
    }
}