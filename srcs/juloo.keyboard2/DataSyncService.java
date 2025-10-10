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

    public void exportClipboard() {
        exportFile(CLIPBOARD_EXPORT_FILENAME, CLIPBOARD_INTERNAL_FILENAME);
    }

    private void importFile(String sourceFileName, String destFileName) {
        File externalDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_DIR_NAME);
        File sourceFile = new File(externalDir, sourceFileName);
        File destFile = new File(context.getFilesDir(), destFileName);

        if (!sourceFile.exists()) {
            Log.d(TAG, "Source file not found for import: " + sourceFile.getAbsolutePath());
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
            Log.d(TAG, "Internal file not found for export: " + internalFile.getAbsolutePath());
            return;
        }

        File externalDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_DIR_NAME);
        if (!externalDir.exists() && !externalDir.mkdirs()) {
            Log.e(TAG, "Failed to create external directory: " + externalDir.getAbsolutePath());
            return;
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