# Ziaistan Keyboard

Lightweight and privacy-conscious virtual keyboard for Android.

The main feature is that you can type more characters by swiping the keys towards the corners.

This application was originally designed for programmers using Termux.
Now perfect for everyday use.

This application contains no ads, doesn't make any network requests and is open source.

Usage: to apply the symbols located in the corners of each key, slide your finger in the direction of the symbols. For example, the Settings are opened by sliding in the left down corner.

| <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/1.png" alt="Screenshot-1" /> | <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/2.png" alt="Screenshot-2"/> | <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/3.png" alt="Screenshot-3"/> |
| --- | --- | --- |
| <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/4.png" alt="Screenshot-4" /> | <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/5.png" alt="Screenshot-5" /> | <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/6.png" alt="Screenshot-6" /> |

## Similar apps

* [Calculator++](https://git.bubu1.eu/Bubu/android-calculatorpp) - Calculator with a similar UX, swipe to corners for advanced math symbols and operators.

## Contributing

For instructions on building the application, see
[Contributing](CONTRIBUTING.md).


# Changelog

This document outlines the new features and enhancements added to the keyboard, based on recent requests.

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


## On-the-Fly Auto-Correction

This keyboard now includes a powerful, on-the-fly auto-correction system designed to fix typos as you type without slowing you down.

### How It Works

*   **Triggered on Spacebar:** Auto-correction is automatically triggered when you press the spacebar after typing a word. If the system detects a potential typo, it will instantly replace the word with the most likely correction.
*   **Keyboard-Aware Corrections:** The correction engine is "keyboard-aware," meaning it understands the layout of the keyboard. It prioritizes corrections based on keys that are physically adjacent to the ones you typed, which is a common source of typos. For example, it knows that 'o' is next to 'i' and 'p' on a QWERTY keyboard and will use this information to make more accurate suggestions.
*   **Advanced Correction Algorithms:** The system uses a suite of sophisticated algorithms running in parallel to find the best correction for your typos. These include:
    *   **Substitution:** Corrects typos where the wrong key was pressed (e.g., "tets" -> "test").
    *   **Deletion:** Corrects typos where an extra character was added (e.g., "heello" -> "hello").
    *   **Insertion:** Corrects typos where a character was missed (e.g., "hallo" -> "hello").
    *   **Transposition:** Corrects typos where adjacent characters were swapped (e.g., "teh" -> "the").
    *   **Reversal:** Corrects words that were typed completely backward.
    *   **Doubling/Singling:** Corrects typos involving doubled or singled letters.
*   **High Performance:** All correction algorithms run on a background thread pool to ensure that the keyboard's UI remains fast and responsive at all times.
*   **Easy Revert:** If a correction was made by mistake, simply press the backspace key once to instantly revert to the original word you typed.

## Next-Word Suggestions

This keyboard now includes an intelligent next-word suggestion feature that learns from your typing habits to predict the next word you are likely to type.

### How It Works

*   **Learns from Your Typing:** The system learns your common word pairs as you type. It learns when you:
    *   Type a word and press the spacebar.
    *   Select a word from the suggestion strip.
    *   Paste text from the clipboard.
*   **Shows Suggestions:** After you type a word and press space, or select a suggestion, the keyboard will display a list of likely next words in the suggestion strip.
*   **Handles Special Characters:** The learning model is designed to handle complex text. It will:
    *   Convert all words to lowercase to avoid duplicates.
    *   Support multilingual words (including Urdu).
    *   Filter out all special characters and punctuation to learn only "pure" words.
    *   Correctly split compound words joined by special characters (e.g., "medium_length" will be learned as "medium" and "length").
*   **Capped Frequency:** To ensure a balanced learning model, the frequency of any given next word is capped at a maximum of 10.
*   **Manual Learning:** You can manually train the suggestion model from a block of text. To do this, select the text in any app and long-press the 'd' key on the keyboard.
*   **Tutorial:** A tutorial for this feature will appear in the suggestion strip when you double-press the spacebar.

## Theme Switching Key

A new theme-switching key has been added to the bottom row of the keyboard to allow for quick and easy theme changes.

*   **Icon**: The key is represented by a 👕 icon.
*   **Tap to Cycle**: A short tap on the key cycles through a predefined list of six themes: Galactic, Golden Pearl, Neon Punk, Everforest Light, Cobalt, and ePaper.
*   **Swipe to Select**: A swipe to the sides on the key changing the themes right side lighter themes left side darker themes, allowing you to select one directly.
*   **Instant Refresh**: The selected theme is applied to the keyboard immediately, without requiring a restart.

## Enhanced Suggestion Strip Tutorials

To help users discover the keyboard's rich feature set, the suggestion strip now includes a dynamic tutorial system.

### Feature Overview

*   **Context-Aware Display**: When there are no word suggestions to show, the suggestion strip will automatically display helpful tips and tricks.
*   **Dynamic Content**: The feature includes over 80 detailed tutorials covering everything from basic clipboard actions to advanced circle gestures.
*   **Automatic Scrolling**: To ensure users see a variety of tips, the tutorials automatically transition to a new, random tip every 5 seconds.
*   **Manual Navigation**: Users can also manually swipe left or right on the tutorial text to navigate to the previous or next tip. Manual interaction will temporarily pause the automatic scrolling.

This system provides a seamless way for users to learn about the keyboard's capabilities directly within the UI.

### How to Add New Tutorials

Adding new tutorials is simple and can be done by editing a single resource file.

1.  **Locate the File**: Open the `res/values/arrays.xml` file in the project.
2.  **Find the Array**: Inside this file, locate the `<string-array>` with the name `tutorials`.
3.  **Add a New Item**: To add a new tip, simply add a new `<item>` tag within the `tutorials` array. For example:

    ```xml
    <string-array name="tutorials">
        <!-- Existing tips -->
        <item>Your new tutorial tip goes here.</item>
    </string-array>
    ```

4.  **Important Note on Special Characters**: If your tutorial string contains special characters like an apostrophe (`'`), you **must** escape it with a backslash (`\'`) to prevent the build from failing. For example: `Tip: This is a user\'s guide.`

The keyboard will automatically pick up the new tutorial the next time the application is built.

## Clipboard Refactor

The clipboard feature has been completely rebuilt from the ground up to provide a modern, intuitive, and powerful experience.

### Modern, Theme-Aware UI
- The old list has been replaced with a fluid, grid-based layout using `RecyclerView`.
- Each clipboard item is displayed in a `CardView` with rounded, theme-aware borders, visually separating them.
- The entire clipboard pane, including items and buttons, is now fully **theme-aware**, matching the background, key, and text colors of your active keyboard theme.

### Gestures and Actions
- **Tap to Paste**: Simply tap any item to instantly paste its content.
- **Long-Press to Pin/Unpin**: Long-press any item to toggle its pinned status.
- **Swipe to Delete**: Swipe any item left or right to delete it from your history.
- **Scrolling**: Vertical scrolling is now smooth and uninterrupted, as the conflicting vertical swipe gestures have been removed.

### Pinning Logic
- When an item is pinned, it moves to the bottom of the grid.
- Pinned items remain at the bottom, sorted by the time they were pinned.
- Unpinned items appear at the top, sorted with the most recent items first.

### Import and Export
- **Export**: Save your entire clipboard history (pinned and unpinned items) to a JSON file.
- **Import**: Load clipboard items from a JSON file. The import function intelligently avoids adding duplicate entries.

### Navigation
- A **Back** button has been added to the bottom action row to easily return to the main keyboard.
- The system's navigation bar back button will also now correctly close the clipboard view.

### Settings
- Clipboard history is now **enabled by default** for a better out-of-the-box experience.
- You can toggle the feature on or off in **Settings > Clipboard**.

This refactoring provides a much more robust and user-friendly clipboard that is seamlessly integrated with the keyboard's look and feel.

## Custom Dictionary

This feature allows you to create and manage a personal dictionary for word suggestions. Words from your custom dictionary will be prioritized over the standard suggestions.

### Managing Your Dictionary

You can manage your dictionary from the settings screen:

1.  Go to **Settings** > **Behavior** > **Custom Dictionary**.
2.  From here, you can:
    *   **Import**: Import a `custom.txt` file to add words to your dictionary. The import function will append new words and ignore any duplicates.
    *   **Export**: Export your current custom dictionary to a `custom.txt` file.

### Adding Words While Typing

You can quickly add words or short phrases (up to 5 words) to your dictionary directly from the keyboard:

1.  Select the text you want to add.
2.  Do one of the following:
    *   Press the **'d'** key.
    *   Press the new **"add to dictionary"** button (+).

The word(s) will be converted to lowercase and added to your custom dictionary. The keyboard will notify you if the word already exists.

### Adding the "Add to Dictionary" Button to Your Layout

You can add a dedicated "add to dictionary" button to any keyboard layout. To do this, edit the desired layout XML file (e.g., `res/xml/latn_qwerty_us.xml`) and add the following key definition:

```xml
<key key0="add_to_dictionary"/>
```

You can adjust the `width` attribute as needed to fit your layout.

## Text Manipulation on Selection

When a portion of text is selected, you can now use the following single-key commands to manipulate it:

### Formatting
*   **Bold**: Type `b` to make the selected text **bold**.
*   **Italic**: Type `i` to make the selected text *italic*.
*   **Font Size**: Type a two-digit number (e.g., `24`) to change the font size of the selected text. The keyboard will wait for two digits before applying the change.

### Case Conversion
*   **Uppercase**: Type `u` to convert the selected text to UPPERCASE.
*   **Lowercase**: Type `l` to convert the selected text to lowercase.
*   **Sentence Case**: Type `s` to convert the selected text to Sentence case.

### Encapsulation
*   Typing any of the following symbols will encapsulate the selected text: `{}`, `[]`, `()`, `<>`, `"`, `'`, `/`, `\`. For example, selecting "hello" and typing `[` will result in `[hello]`.

## Application Integrations

Quickly send selected text to other applications:

*   **Google Translate**: Type `t` to send the selected text to the Google Translate app.
*   **Google Keep Notes**: Type `k` to create a new note in Google Keep with the selected text.
*   **Obsidian**: Type `o` to create a new note in Obsidian with the selected text.
*   **Google Chrome**: Type `c` to search for the selected text in Google Chrome.

## Keyboard Layout and Behavior

### Word Jumping with the Number Row

The number row now includes a powerful new feature for rapid cursor navigation. You can now jump the cursor by one or more words at a time using simple swipe gestures on the number keys.

**How to Use:**

*   **Swipe Up (Backward):** Swipe up on any of the number keys from **1 to 5** to move the cursor backward by the corresponding number of words.
    *   Example: Swiping up on the '3' key will move the cursor three words to the left.
*   **Swipe Down (Forward):** Swipe down on any of the number keys from **1 to 5** to move the cursor forward by the corresponding number of words.
    *   Example: Swiping down on the '3' key will move the cursor three words to the right.

This feature is designed to make text editing faster and more efficient, allowing you to navigate through your document without having to repeatedly tap the arrow keys.



## Enhanced Circle Gestures Documentation

### Overview

This update introduces a powerful new feature that expands the functionality of circle gestures on the keyboard. Previously, a simple circle gesture would trigger a single action. Now, you can perform eight distinct gestures by starting your circle from any of the four corners of a key, in either a clockwise (CW) or anti-clockwise (ACW) direction.

This allows for a much richer and faster typing experience, giving you quick access to vowels, modified characters, and other useful actions without lifting your finger.

### How to Use

To perform a gesture, simply start your swipe from one of the four corners of a key and draw a circle.

*   **Starting Corner:** The gesture is determined by where you begin the circle (South-West, North-East, South-East, or North-West).
*   **Direction:** The gesture is also determined by whether you draw the circle clockwise or anti-clockwise.

### Implemented Gestures

Here is a complete list of the new gestures and their functions. These gestures apply to standard letter keys (e.g., 'a', 'b', 'c').

| Starting Corner | Direction | Gesture Name | Action | Example (on 'k') |
| :--- | :--- | :--- | :--- | :--- |
| **South-West** | Clockwise | `CircleSW` | Appends 'a' | `ka` |
| **South-West** | Anti-Clockwise| `AnticircleSW`| Appends 'u' | `ku` |
| **North-East** | Clockwise | `CircleNE` | Appends 'e' | `ke` |
| **North-East** | Anti-Clockwise| `AnticircleNE`| `Shift + Character` | `K` |
| **South-East** | Clockwise | `CircleSE` | Appends 'i' | `ki` |
| **South-East** | Anti-Clockwise| `AnticircleSE`| `Ctrl + Character` | `Ctrl+k` |
| **North-West** | Clockwise | `CircleNW` | Appends 'o' | `ko` |
| **North-West** | Anti-Clockwise| `AnticircleNW`| `Ctrl+Shift+Character`| `Ctrl+Shift+k` |


View the animated grid in: [grid.html](grid.html) download it amd view in web browser



### Technical Implementation Details

For developers or those interested in the underlying changes, here is a summary of how this feature was implemented:

1.  **`Gesture.java`:**
    *   An enum `Corner` (`NE`, `SE`, `SW`, `NW`) was added to detect the starting corner of the gesture based on its initial direction.
    *   The `Name` enum was expanded to include eight new gesture names (e.g., `CircleNE`, `AnticircleSW`) to uniquely identify each corner- and direction-specific gesture.
    *   The `get_gesture()` method was updated to return these new specific gesture names.

2.  **`Pointers.java`:**
    *   The `apply_gesture()` method was significantly updated to handle all the new gesture names.
    *   For the vowel-appending gestures, it now creates a new `KeyValue` with the combined string (e.g., character + 'a').
    *   For the modifier-based gestures (`shift`, `ctrl`), it now uses a new, specialized `KeyValue` type to ensure the modifiers are applied correctly for a single key event.

3.  **`KeyValue.java` & `KeyEventHandler.java` (The Fix):**
    *   To fix the issue where `ctrl` and `shift` modifiers were not working, a new `Kind` of `KeyValue` was created called `ModifiedChar`.
    *   This new type encapsulates both a character and its intended temporary modifier state (`metaState`).
    *   A new factory method, `makeModifiedCharKey(char, int)`, was added to create these objects.
    *   `KeyEventHandler.java` was updated to handle `ModifiedChar`. When it receives this key type, it extracts the character and the `metaState` and sends the correct `KeyEvent` to the system, correctly simulating a modified key press (e.g., `Ctrl+C`). This approach avoids interfering with the keyboard's persistent modifier state.
*   **Circle Gestures**: The circle gestures have been enhanced to support different actions on the four corners of a key. For alphabetic keys, this inserts the character followed by a vowel (`a`, `e`, `i`, `o`, `u`) or applies modifier keys (`Shift`, `Ctrl`).


## Auto-Completion Feature Documentation

### Overview

This keyboard application includes a robust auto-completion feature designed to speed up your typing by suggesting words as you type. This feature is powered by a comprehensive word list that includes over 600,000 words in both English and Urdu.

### How It Works

The auto-completion system uses a dictionary file named `wordlist.txt` to provide suggestions. As you type, the keyboard searches this list for words that match your input and displays them as suggestions above the keyboard.

### The `wordlist.txt` File

*   **Location:** The word list is located at `res/raw/wordlist.txt`.
*   **Format:** The file is a simple plain text file with one word or phrase per line.
*   **Content:** The list contains a mix of English and Urdu words, as well as common phrases and email addresses, making it a versatile tool for everyday typing.

### Customization

While the default `wordlist.txt` is extensive, it can be customized to better suit your needs. You can add or remove words from this file to fine-tune the auto-completion suggestions. To do this, simply edit the `res/raw/wordlist.txt` file and add or remove words, with each word on a new line. After modifying the file, you will need to rebuild the application for the changes to take effect.

### Suggestion Strip Position

The suggestion strip can be positioned at either the top or the bottom of the keyboard to suit your preference.

*   **Using the Handle:** A handle with up and down arrows is located on the right side of the suggestion strip. Clicking this handle will instantly move the strip to the opposite position (from top to bottom, or from bottom to top).

*   **Using Settings:** You can also set the default position of the suggestion strip in the keyboard's settings:
    1.  Go to `Settings`.
    2.  Navigate to the `Behavior` section.
    3.  Toggle the `Suggestion strip on top` option to enable or disable showing the strip at the top of the keyboard. This option is only available when suggestions are enabled.
*   **Suggestion Strip**:
    *   Suggestions from `common.txt` are now prioritized and appear before suggestions from `wordlist.txt`.
    *   Arrow buttons have been added to the suggestion strip to move it to the top or bottom of the screen.
*   **Default Settings**:
    *   Default keyboard height set to 20% in both portrait and landscape modes.
    *   Default bottom margin set to 50dp.
    *   Default horizontal margin set to 15dp in portrait mode.
    *   Default theme set to "Galactic".
*   **New Themes**: Added 7 new themes (4 light, 3 darker).



## Keyboard Layouts Documentation

This document provides an overview of the modifications made to the English QWERTY and Urdu Phonetic keyboard layouts. These changes are designed to enhance the typing experience by providing quick access to a wide range of functions, symbols, and diacritics.

### English QWERTY Layout (`latn_qwerty_us.xml`)

The English QWERTY layout has been significantly enhanced to provide a more powerful and efficient typing experience.

#### Key Modifications:

*   **Custom Function Rows:** Two dedicated function rows have been added at the top of the keyboard, providing one-tap access to common actions such as:
    *   `home`, `end`, `page_up`, `page_down`
    *   `cut`, `copy`, `paste`, `selectAll`
    *   `undo`, `redo`, `delete`, `esc`
*   **Enhanced Modifier Keys:** The `shift` key now functions as a multi-modifier key, giving you access to `ctrl`, `alt`, and `meta` via corner swipes. It also includes integrated cursor controls.
*   **Comprehensive Fn Layer:** A powerful `Fn` layer has been added, providing quick access to a wide range of symbols, including:
    *   Punctuation: `۔`, `،`, `؟`, `!`, `(`, `)`, `"`, `'`
    *   Brackets: `[]`, `{}`
    *   Mathematical & Currency Symbols: `√`, `π`, `€`, `£`, `¥`
*   **Easy Symbol Access:** The main typing area now includes quick access to numbers and common symbols (e.g., `!`, `@`, `#`, `$`) through a long press or swipe up on the letter keys.

### Urdu Phonetic Layout (`urdu_phonetic_ur.xml`)

The Urdu Phonetic layout has been customized to provide a more intuitive and efficient typing experience for Urdu users.

#### Key Modifications:

*   **Diacritics on Every Key:** The most significant enhancement is the addition of diacritics (`zabbar`, `zer`, `pesh`, etc.) to every letter key via corner swipes. This allows for quick and easy access to correct Urdu spellings without needing a separate diacritics menu.
*   **Customized Fn Layer for Urdu:** The `Fn` layer has been tailored for Urdu users, providing quick access to:
    *   Urdu numerals: `۱`, `۲`, `۳`, etc.
    *   Urdu punctuation: `۔`, `،`, `؟`
    *   Common symbols and brackets.
*   **Consistent User Experience:** Like the English layout, the Urdu layout also includes two dedicated function rows with the same set of actions, ensuring a consistent and predictable experience when switching between languages.
*   **Enhanced Modifier Keys:** The `shift` key has the same multi-modifier and cursor control functionality as the English layout.


## Implemented Feature: Key-press Pop-up Enhancements

### Key Features:
- **Correct Character Display:** Shows the final character for taps, swipes, gestures, and modifier combos (e.g., Ctrl+Shift).
- **Theme-Aware Text Color:** Dynamically adjusts text color based on key's label and theme state.
- **Consistent Size and Position:** Pop-up is centered and visible above the keyboard with fixed size (4x key width).
- **Reliable Display for Rapid Typing:** Managed by a Handler with a consistent 500ms display duration.
- **No-Animation Display:** Instant appearance/disappearance for quick feedback.


These modifications are designed to make the keyboard more powerful, versatile, and user-friendly for both English and Urdu typists.

## New Settings and Defaults

This update introduces several new settings to provide more control over the keyboard's behavior and sets more convenient defaults for a better out-of-the-box experience.

### New Toggleable Features

The following features can now be enabled or disabled from the settings menu:

*   **Popup on keypress**: Show a popup with the selected character when a key is pressed.
*   **Circle Gestures**: Enable or disable the corner-based circle gestures for quick access to vowels and other functions.
*   **Application Integrations**: Enable or disable sending selected text to other applications (Google Translate, Keep, Obsidian, Chrome).
*   **Encapsulation**: Enable or disable automatically wrapping selected text with symbols like `()`, `{}`, `[]`, etc.
*   **Case Conversion and Formatting**: Enable or disable text manipulation features for selected text, such as changing case or applying bold/italic styles.

### Updated Default Settings

The following settings have been updated to new defaults:

*   **Default Theme**: The default theme is now **Galactic**.
*   **Default Keyboard Layouts**: The default layouts for new installations are now **Urdu Phonetic** and **English QWERTY**.
*   **Suggestion Strip on Top**: The suggestion strip is now shown at the top of the keyboard by default.
*   **Double Tap for Caps Lock**: Double-tapping the shift key to enable caps lock is now on by default.
*   **Number Row**: The number row is now shown with symbols by default.
*   **Spacebar Slider Sensitivity**: The sensitivity of the spacebar slider for cursor movement is now set to **fast** by default.



## Clipboard Limit increased

Clipboard copied items limit increased and also history timeout increased until app stopped and automatic removal of copied content now it will be only removed by swiping left right



### Batch Add to Dictionary

The "batch add to dictionary" feature has been added. You can now swipe top left to the "++" button on the keyboard to add all the words in a selected block of text to your custom dictionary.
*   **Advanced Sanitization:** The feature includes advanced text processing to ensure only valid words are added. It will:
    *   Split words based on any non-letter character (spaces, symbols, numbers, etc.).
    *   Convert all words to lowercase.
    *   Ignore single-letter words.
    *   Ignore words that are already in your dictionary.
