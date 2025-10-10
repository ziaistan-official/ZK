package juloo.keyboard2;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class SettingsActivity extends PreferenceActivity
{
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    // The preferences can't be read when in direct-boot mode. Avoid crashing
    // and don't allow changing the settings.
    // Run the config migration on this prefs as it might be different from the
    // one used by the keyboard, which have been migrated.
    try
    {
      Config.migrate(getPreferenceManager().getSharedPreferences());
    }
    catch (Exception _e) { fallbackEncrypted(); return; }
    addPreferencesFromResource(R.xml.settings);

    boolean foldableDevice = FoldStateTracker.isFoldableDevice(this);
    findPreference("margin_bottom_portrait_unfolded").setEnabled(foldableDevice);
    findPreference("margin_bottom_landscape_unfolded").setEnabled(foldableDevice);
    findPreference("horizontal_margin_portrait_unfolded").setEnabled(foldableDevice);
    findPreference("horizontal_margin_landscape_unfolded").setEnabled(foldableDevice);
    findPreference("keyboard_height_unfolded").setEnabled(foldableDevice);
    findPreference("keyboard_height_landscape_unfolded").setEnabled(foldableDevice);

    Preference importDataPref = findPreference("import_data");
    importDataPref.setOnPreferenceClickListener(p -> {
      new DataSyncService(this).importData();
      Toast.makeText(this, "Data imported successfully.", Toast.LENGTH_SHORT).show();
      // Reload dictionaries and clipboard view if needed
      sendBroadcast(new Intent(CustomDictionarySettingsActivity.RELOAD_CUSTOM_DICTIONARY_ACTION));
      sendBroadcast(new Intent(ClipboardHistoryService.RELOAD_CLIPBOARD_HISTORY_ACTION));
      return true;
    });

    Preference exportDataPref = findPreference("export_data");
    exportDataPref.setOnPreferenceClickListener(p -> {
      new DataSyncService(this).exportData();
      Toast.makeText(this, "Data exported successfully.", Toast.LENGTH_SHORT).show();
      return true;
    });
  }

  void fallbackEncrypted()
  {
    // Can't communicate with the user here.
    finish();
  }

  protected void onStop()
  {
    DirectBootAwarePreferences
      .copy_preferences_to_protected_storage(this,
          getPreferenceManager().getSharedPreferences());
    super.onStop();
  }
}
