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