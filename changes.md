# Changelog

## Version 1.33.0 (Upcoming)

### Automatic Data Backup and Restore

This version introduces a powerful and reliable automatic data backup and restore feature for your custom dictionary and clipboard history.

**How it Works:**

*   **One-Time Permission:** When you first launch the app after updating, you will be prompted to grant "All files access" permission. This is a one-time setup step that is **required** for the automatic import/export feature to work.
*   **Automatic Import:** Once permission is granted, the app will automatically import your `custom.txt` and `clipboard_export.json` files from the `Downloads/ziaistan_keyboard_backup/` directory every time you launch the app.
*   **Automatic Export:** The app will automatically save your custom dictionary and clipboard history to the same directory whenever their content changes. This ensures your backup is always up-to-date.

**To restore your data on a new device:**

1.  Install the app.
2.  Launch the app and grant the "All files access" permission when prompted.
3.  Copy your `custom.txt` and `clipboard_export.json` files into the `Downloads/ziaistan_keyboard_backup/` directory.
4.  Relaunch the app. Your data will be imported automatically.