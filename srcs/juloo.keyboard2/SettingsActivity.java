package juloo.keyboard2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class SettingsActivity extends PreferenceActivity {

    private static final int IMPORT_DICT_REQUEST_CODE = 1;
    private static final int EXPORT_DICT_REQUEST_CODE = 2;
    private static final int IMPORT_CLIP_REQUEST_CODE = 3;
    private static final int EXPORT_CLIP_REQUEST_CODE = 4;

    private static final String CUSTOM_DICTIONARY_FILE = "custom.txt";
    private static final String CLIPBOARD_HISTORY_FILE = "clipboard_history.json";
    private static final String CLIPBOARD_EXPORT_FILENAME = "clipboard_export.json";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Config.migrate(getPreferenceManager().getSharedPreferences());
        } catch (Exception _e) {
            finish(); // Fallback for encrypted storage issues
            return;
        }
        addPreferencesFromResource(R.xml.settings);

        setupPreferenceClickListeners();
    }

    private void setupPreferenceClickListeners() {
        findPreference("import_custom_dictionary").setOnPreferenceClickListener(p -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/plain");
            startActivityForResult(intent, IMPORT_DICT_REQUEST_CODE);
            return true;
        });

        findPreference("export_custom_dictionary").setOnPreferenceClickListener(p -> {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TITLE, CUSTOM_DICTIONARY_FILE);
            startActivityForResult(intent, EXPORT_DICT_REQUEST_CODE);
            return true;
        });

        findPreference("import_clipboard_history").setOnPreferenceClickListener(p -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/json");
            startActivityForResult(intent, IMPORT_CLIP_REQUEST_CODE);
            return true;
        });

        findPreference("export_clipboard_history").setOnPreferenceClickListener(p -> {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/json");
            intent.putExtra(Intent.EXTRA_TITLE, CLIPBOARD_EXPORT_FILENAME);
            startActivityForResult(intent, EXPORT_CLIP_REQUEST_CODE);
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        Uri uri = data.getData();
        if (uri == null) {
            return;
        }

        switch (requestCode) {
            case IMPORT_DICT_REQUEST_CODE:
                importDictionary(uri);
                break;
            case EXPORT_DICT_REQUEST_CODE:
                exportDictionary(uri);
                break;
            case IMPORT_CLIP_REQUEST_CODE:
                importClipboard(uri);
                break;
            case EXPORT_CLIP_REQUEST_CODE:
                exportClipboard(uri);
                break;
        }
    }

    private void importDictionary(Uri uri) {
        Set<String> existingWords = new HashSet<>();
        File customDictFile = new File(getFilesDir(), CUSTOM_DICTIONARY_FILE);

        if (customDictFile.exists()) {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(customDictFile))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    existingWords.add(line.trim().toLowerCase());
                }
            } catch (IOException e) {
                Toast.makeText(this, "Error reading existing dictionary.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        boolean dictionaryChanged = false;
        try (InputStream inputStream = getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             FileOutputStream fos = openFileOutput(CUSTOM_DICTIONARY_FILE, MODE_APPEND)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim().toLowerCase();
                if (!word.isEmpty() && !existingWords.contains(word)) {
                    fos.write((word + "\n").getBytes());
                    existingWords.add(word);
                    dictionaryChanged = true;
                }
            }
            Toast.makeText(this, "Dictionary imported successfully.", Toast.LENGTH_SHORT).show();
            if (dictionaryChanged) {
                sendBroadcast(new Intent(CustomDictionarySettingsActivity.RELOAD_CUSTOM_DICTIONARY_ACTION));
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error importing dictionary.", Toast.LENGTH_SHORT).show();
        }
    }

    private void exportDictionary(Uri uri) {
        File internalFile = new File(getFilesDir(), CUSTOM_DICTIONARY_FILE);
        if (!internalFile.exists()) {
            Toast.makeText(this, "No custom dictionary to export.", Toast.LENGTH_SHORT).show();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(internalFile));
             OutputStream outputStream = getContentResolver().openOutputStream(uri)) {

            String line;
            while ((line = reader.readLine()) != null) {
                outputStream.write((line + "\n").getBytes());
            }
            Toast.makeText(this, "Dictionary exported successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error exporting dictionary.", Toast.LENGTH_SHORT).show();
        }
    }

    private void importClipboard(Uri uri) {
        try {
            ClipboardHistoryService.get_service(this).importFromUri(uri);
            Toast.makeText(this, "Clipboard history imported.", Toast.LENGTH_SHORT).show();
            sendBroadcast(new Intent(ClipboardHistoryService.RELOAD_CLIPBOARD_HISTORY_ACTION));
        } catch (Exception e) {
            Toast.makeText(this, "Failed to import clipboard history.", Toast.LENGTH_SHORT).show();
        }
    }

    private void exportClipboard(Uri uri) {
        try {
            ClipboardHistoryService.get_service(this).exportToUri(uri);
            Toast.makeText(this, "Clipboard history exported.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to export clipboard history.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        DirectBootAwarePreferences.copy_preferences_to_protected_storage(this, getPreferenceManager().getSharedPreferences());
        super.onStop();
    }
}