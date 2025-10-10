package juloo.keyboard2;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

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
        importDictionary();
        importClipboard();
    }


    public void importDictionary() {
        importFile(CUSTOM_DICT_FILENAME, CUSTOM_DICT_FILENAME);
    }

    public void importClipboard() {
        importFile(CLIPBOARD_EXPORT_FILENAME, CLIPBOARD_INTERNAL_FILENAME);
    }

    public void exportDictionary() {
        exportFile(CUSTOM_DICT_FILENAME, CUSTOM_DICT_FILENAME);
    }

    public void exportClipboard(String jsonPayload) {
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