package juloo.keyboard2;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

public class CustomDictionarySettingsActivity extends PreferenceActivity {

    private static final int IMPORT_REQUEST_CODE = 1;
    private static final int EXPORT_REQUEST_CODE = 2;
    private static final String CUSTOM_DICTIONARY_FILE = "custom.txt";
    public static final String RELOAD_CUSTOM_DICTIONARY_ACTION = "juloo.keyboard2.RELOAD_CUSTOM_DICTIONARY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.custom_dictionary_settings);

        Preference importPref = findPreference("import_custom_dictionary");
        importPref.setOnPreferenceClickListener(p -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/plain");
            startActivityForResult(intent, IMPORT_REQUEST_CODE);
            return true;
        });

        Preference exportPref = findPreference("export_custom_dictionary");
        exportPref.setOnPreferenceClickListener(p -> {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TITLE, CUSTOM_DICTIONARY_FILE);
            startActivityForResult(intent, EXPORT_REQUEST_CODE);
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

        if (requestCode == IMPORT_REQUEST_CODE) {
            importDictionary(uri);
        } else if (requestCode == EXPORT_REQUEST_CODE) {
            exportDictionary(uri);
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
                e.printStackTrace();
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
                sendBroadcast(new Intent(RELOAD_CUSTOM_DICTIONARY_ACTION));
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error importing dictionary.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
}