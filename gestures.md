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