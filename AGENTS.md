# BLUEPRINT OF CURRENT PROJECT FOR AGENT TO UNDERSTAND THE CURRENT CODEBASE CORRECTLY AND MAKE ONLY THE NECESSARY CHANGES ASKED BY THE USER TO AVOID MESSING THE PROJECT.

## 1. Files and Defined Symbols

**`check_layout.py`**

- **Functions:** `check_layout, key_list_str, missing_required, missing_some_of, parse_known_keys, parse_layout, parse_row, parse_row_from_et, unexpected_keys, warn`

**`funding.json`**

- **Config keys:** `address, amount, channels, currency, description, email, entity, frequency, funding, guid, history, licenses, name, plans, projects, repositoryUrl, role, status, tags, type, url, version, webpageUrl`

**`gen_emoji.py`**

- **Functions:** `getEmojiTestContents, rawEmojiFromCodes`

**`gen_layouts.py`**

- **Functions:** `generate_arrays, mk_array, read_layout, read_layouts, sort_layouts`

**`gen_sinhala_phonetic_layout.py`**

- **Classs:** `LayoutBuilder, LayoutGenError, Placement`
- **Functions:** `__init__, _add_extra_chars_to_ref_map, _apply_transitions, _make_extra_modmap, _move_untransited_to_new_map, _parse_reference_layout, _post_escape, _process_key, _resolve_placement, build, get_args, get_xml, is_in_escape_list, keys_map_to_str, xml_elem_to_str, xml_encode_char`

**`gradle.properties`**

- **Config keys:** `android.nonTransitiveRClass, android.useAndroidX, org.gradle.configuration-cache, org.gradle.jvmargs`

**`gradle/wrapper/gradle-wrapper.properties`**

- **Config keys:** `distributionBase, distributionPath, distributionUrl, networkTimeout, validateDistributionUrl, zipStoreBase, zipStorePath`

**`res/layout/clipboard_action_row.xml`**

- **Resource ids:** `@+id/clipboard_back_button, @+id/clipboard_export_button, @+id/clipboard_import_button`

**`res/layout/clipboard_grid_item.xml`**

- **Resource ids:** `@+id/clipboard_item_pin_icon, @+id/clipboard_item_text`

**`res/layout/clipboard_pane.xml`**

- **Resource ids:** `@+id/clipboard_bottom_row_view, @+id/clipboard_recycler_view`

**`res/layout/dialog_edit_text.xml`**

- **Resource ids:** `@+id/text`

**`res/layout/emoji_pane.xml`**

- **Resource ids:** `@+id/emoji_grid`

**`res/layout/keyboard.xml`**

- **Resource ids:** `@+id/keyboard_container, @+id/keyboard_view, @+id/suggestion_strip_container_bottom, @+id/suggestion_strip_container_top`

**`res/layout/launcher_activity.xml`**

- **Resource ids:** `@+id/launcher_anim_circle, @+id/launcher_anim_round_trip, @+id/launcher_anim_swipe, @+id/launcher_tryhere_area, @+id/launcher_tryhere_text`

**`res/layout/pref_listgroup_item_widget.xml`**

- **Resource ids:** `@+id/pref_listgroup_remove_btn`

**`res/layout/suggestion_strip.xml`**

- **Resource ids:** `@+id/suggestion_strip_handle, @+id/suggestions_grid, @+id/suggestions_strip, @+id/suggestions_strip_scroll, @+id/tutorial_flipper, @+id/ziaistan_official_text`

**`res/menu/launcher_menu.xml`**

- **Resource ids:** `@+id/btnLaunchSettingsActivity`

**`srcs/compose/accent_aigu.json`**

- **Config keys:** `a, c, e, g, i, j, k, l, m, n, o, p, r, s, u, w, y, z, â, å, æ, ç, ê, ï, ô, õ, ø, ü, ă, ą, ē, ō, ũ, ơ, ư, α, ε, η, ι, ο, υ, а, г, е, и, к, о, у, ы, э, ю, я, ṡ`

**`srcs/compose/accent_arrows.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9`

**`srcs/compose/accent_bar.json`**

- **Config keys:** `2, b, c, d, f, g, h, i, j, k, l, o, p, q, r, t, u, y, z, ȷ, о, х, ь, ү, ӧ`

**`srcs/compose/accent_box.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9`

**`srcs/compose/accent_caron.json`**

- **Config keys:** `a, c, d, e, g, h, i, j, k, l, n, o, r, s, t, u, z, ü, ʒ, в, г, д, з, р, т, х, ғ, ṡ`

**`srcs/compose/accent_cedille.json`**

- **Config keys:** `c, d, e, g, h, k, l, n, r, s, t, ć, ĕ`

**`srcs/compose/accent_circonflexe.json`**

- **Config keys:** `a, c, e, g, h, i, j, o, u, w, x, y, z, à, á, ã, è, é, ò, ó, õ, ŝ, ơ, а, е, и, о, у, ạ, ả, ẹ, ẻ, ẽ, ọ`

**`srcs/compose/accent_dot_above.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, ā, ō, ś, š, ſ, ṣ`

**`srcs/compose/accent_dot_below.json`**

- **Config keys:** `a, b, d, e, h, i, k, l, m, n, o, r, s, t, u, v, w, y, z, â, ê, ô, ă, ơ, ư, ṡ`

**`srcs/compose/accent_double_aigu.json`**

- **Config keys:** `a, e, i, m, o, u, y, у`

**`srcs/compose/accent_double_grave.json`**

- **Config keys:** `a, e, i, o, r, u, а, е, и, о, р, у, ѵ`

**`srcs/compose/accent_grave.json`**

- **Config keys:** `a, e, i, n, o, s, u, w, y, z, â, ê, ô, ü, ă, ē, ō, ơ, ư, ɔ, ʌ, α, ε, η, ι, ο, υ, ω, е, и`

**`srcs/compose/accent_hook_above.json`**

- **Config keys:** `a, e, i, o, u, y, â, ê, ô, ă, ơ, ư`

**`srcs/compose/accent_horn.json`**

- **Config keys:** `o, u, ò, ó, õ, ù, ú, ũ, ọ, ỏ, ụ, ủ`

**`srcs/compose/accent_macron.json`**

- **Config keys:** `a, e, g, i, l, o, r, u, y, ä, æ, è, é, ò, ó, õ, ö, ü, ǫ, ȧ, ȯ, α, ι, υ, и, у, ḷ, ṛ`

**`srcs/compose/accent_ogonek.json`**

- **Config keys:** `a, e, i, o, u, ō`

**`srcs/compose/accent_ordinal.json`**

- **Config keys:** `1, 2, 3, 4, 5, 6, 7, 8, 9, a, o`

**`srcs/compose/accent_ring.json`**

- **Config keys:** `a, u, w, y, á`

**`srcs/compose/accent_slash.json`**

- **Config keys:** `a, b, c, e, g, k, l, n, o, r, s, t, u, v, ó, ɔ`

**`srcs/compose/accent_subscript.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9, a, e, h, i, j, k, l, m, n, o, p, r, s, t, u, v, x, ə, β, γ, ρ, φ, χ`

**`srcs/compose/accent_superscript.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, ŋ, œ, ɐ, ɒ, ɔ, ɕ, ə, ɛ, ɜ, ɟ, ɣ, ɥ, ɦ, ɨ, ɯ, ɰ, ɱ, ɵ, ɹ, ɻ, ʁ, ʂ, ʃ, ʉ, ʊ, ʌ, ʍ, ʒ, β, δ, θ, ι, φ, χ, ө, ᴂ, ᴈ, ᴉ, ᴝ`

**`srcs/compose/accent_tilde.json`**

- **Config keys:** `a, e, i, n, o, u, v, y, â, ê, ó, ö, ú, ă, ō, ơ, ư`

**`srcs/compose/accent_trema.json`**

- **Config keys:** `a, c, e, h, i, j, k, l, m, n, o, s, t, u, w, x, y, í, õ, ù, ú, ā, ō, ū, ǔ, ι, υ, ύ, ϒ, а, ж, з, и, о, у, ч, ы, э, ә, ө, ὺ, ῦ`

**`srcs/compose/compile.py`**

- **Functions:** `add_leaf, add_node, add_seq_to_trie, add_sequences_to_trie, add_tree, batched, char_repr, check_for_warnings, existing_sequence_to_str, gen_array, gen_entry_state, gen_java, get, make_automata, parse_keysymdef_h, parse_seq_char, parse_seq_chars, parse_seq_line, parse_seq_result, parse_sequences_dir, parse_sequences_file, parse_sequences_file_json, parse_sequences_file_xkb, print_automata, seq_to_str, strip_cstyle_comments, strip_line, tree_to_seqs, warn`

**`srcs/compose/compose/arabic.json`**

- **Config keys:** `ا, ت, ث, ج, ح, د, ر, ز, س, ش, ع, غ, ف, ق, ك, ل, ن, ه, و, ي, ٧, ٨, ک, ی, ۷, ۸`

**`srcs/compose/compose/cyrillic.json`**

- **Config keys:** `І, Ј, А, Г, Д, Е, Ж, З, И, Й, К, Л, М, Н, О, П, С, Т, У, Ф, Х, Ч, Ш, Ы, Ь, Э, Ю, а, б, г, д, е, ж, з, и, й, к, л, м, н, о, п, с, т, у, ф, х, ч, ш, ъ, ы, ь, э, ю, і, ј, Ѡ, ѡ, Ѣ, ѣ, Ѥ, ѥ, Ѧ, ѧ, Ꙋ, ꙋ, Ꙑ, ꙑ`

**`srcs/compose/compose/extra.json`**

- **Config keys:** `A, D, E, I, N, O, Q, R, S, T, V, Z, a, c, e, g, h, j, n, q, r, s, t, u, z`

**`srcs/compose/fn.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9, _, a, b, c, e, h, l, o, p, r, u, y, z, Θ, Π, Σ, β, γ, ε, θ, κ, π, ρ, σ, υ, φ, а, г, д, е, з, и, й, л, м, н, о, с, у, ф, х, ч, ш, ъ, ы, ь, э, я, ђ, і, ј, ћ, џ, ѡ, ҁ, қ, ҷ, ӈ, ԓ, ב, ד, ו, ז, ח, ם, ס, ף, פ, צ, ק, ר, ש, إ, ئ, ا, ب, ة, ت, ث, ح, د, ر, ز, س, ش, ص, ط, ع, غ, ؽ, ف, ق, ك, ل, م, ن, ه, و, ي, ٠, ١, ٢, ٣, ٤, ٥, ٦, ٧, ٨, ٩, ژ, ڡ, ک, ھ, ۆ, ۉ, ی, ێ, ۰, ۱, ۲, ۳, ۴, ۵, ۶, ۷, ۸, ۹, अ, आ, इ, ई, उ, ऊ, ऋ, ऌ, ए, ऐ, ओ, औ, क, ख, ग, घ, च, छ, ज, झ, ट, ड, ढ, ण, थ, द, न, प, फ, ब, य, र, ळ, व, श, ष, ऽ, ஒ, ச, ய, ள, வ, ꙉ, ꙑ, ꙗ`

**`srcs/compose/numpad_bengali.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9`

**`srcs/compose/numpad_devanagari.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9`

**`srcs/compose/numpad_gujarati.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9`

**`srcs/compose/numpad_hindu.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9`

**`srcs/compose/numpad_kannada.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9`

**`srcs/compose/numpad_persian.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9`

**`srcs/compose/numpad_tamil.json`**

- **Config keys:** `0, 1, 2, 3, 4, 5, 6, 7, 8, 9`

**`srcs/compose/shift.json`**

- **Config keys:** `ß, ı, ǰ, ȷ, ʰ, ʲ, ʳ, ʷ, ˡ, अ, इ, उ, ऋ, ऌ, ए, ओ, क, ग, च, ज, ट, ड, त, द, न, ब, म, ल, स, ह, અ, ઇ, ઉ, એ, ઓ, ક, ગ, ચ, જ, ટ, ડ, ત, દ, ન, પ, બ, મ, લ, સ, હ, ᵃ, ᵇ, ᵈ, ᵉ, ᵍ, ᵏ, ᵐ, ᵒ, ᵖ, ᵗ, ᵘ, ᵛ, ᵠ, ᶜ, ᶠ, ᶾ, ẗ, ẘ, ẙ, ⁱ, ⁿ`

**`srcs/juloo.keyboard2/Autocapitalisation.java`**

- **Classs:** `Autocapitalisation, Callback, Runnable`
- **Functions:** `callback, callback_now, event_sent, is_trigger_character, pause, run, selection_updated, started, started_should_update_state, stop, type_one_char, typed, unpause`

**`srcs/juloo.keyboard2/ClipboardHistoryCheckBox.java`**

- **Classs:** `ClipboardHistoryCheckBox`
- **Functions:** `onCheckedChanged`

**`srcs/juloo.keyboard2/ClipboardHistoryService.java`**

- **Classs:** `ClipboardHistoryService, ClipboardPasteCallback, OnClipboardHistoryChange, SystemListener`
- **Functions:** `addClip, addCurrentClip, clearHistory, compare, exportToUri, getItems, get_service, importFromUri, isSystemClipboard, loadItems, migrateFromPrefs, notifyHistoryChange, onPrimaryClipChanged, on_startup, paste, persistItems, removeItem, setOnClipboardHistoryChange, set_history_enabled, sortItems, togglePin, trimHistory`

**`srcs/juloo.keyboard2/ClipboardItem.java`**

- **Classs:** `ClipboardItem`
- **Functions:** `equals, getText, getTimestamp, hashCode, isPinned, setPinned, setTimestamp`

**`srcs/juloo.keyboard2/ClipboardView.java`**

- **Classs:** `ClipboardAdapter, ClipboardView, GestureCallback, ViewHolder`
- **Functions:** `getItem, getItemCount, if, isLongPressDragEnabled, onAttachedToWindow, onBindViewHolder, onCreateViewHolder, onDetachedFromWindow, onFinishInflate, onMove, onSwiped, on_clipboard_history_change, setItems, updateData`

**`srcs/juloo.keyboard2/ComposeKey.java`**

- **Classs:** `ComposeKey`
- **Functions:** `apply`

**`srcs/juloo.keyboard2/ComposeKeyData.java`**

- **Classs:** `ComposeKeyData`

**`srcs/juloo.keyboard2/Config.java`**

- **Classs:** `Config, IKeyEventHandler`
- **Functions:** `getThemeId, get_current_layout, get_dip_pref, get_dip_pref_oriented, globalConfig, globalPrefs, initGlobalConfig, migrate, migrate_layout, refresh, set_clipboard_history_enabled, set_current_layout`

**`srcs/juloo.keyboard2/CustomDictionarySettingsActivity.java`**

- **Classs:** `CustomDictionarySettingsActivity`
- **Functions:** `exportDictionary, if, importDictionary, onActivityResult, onCreate`

**`srcs/juloo.keyboard2/CustomLayoutEditDialog.java`**

- **Classs:** `Callback, CustomLayoutEditDialog, LayoutEntryEditText, OnChangeListener, Runnable`
- **Functions:** `onClick, onDraw, onTextChanged, on_change, run, set_on_text_change, show`

**`srcs/juloo.keyboard2/DirectBootAwarePreferences.java`**

- **Classs:** `DirectBootAwarePreferences`
- **Functions:** `check_need_migration, copy_preferences_to_protected_storage, copy_shared_preferences, get_protected_prefs, get_shared_preferences`

**`srcs/juloo.keyboard2/Emoji.java`**

- **Classs:** `Emoji`
- **Functions:** `getEmojiByString, getEmojisByGroup, getNumGroups, init, kv`

**`srcs/juloo.keyboard2/EmojiGridView.java`**

- **Classs:** `EmojiGridView, EmojiView, EmojiViewAdpater`
- **Functions:** `compare, emojiSharedPreferences, getCount, getItem, getItemId, getLastEmojis, getView, loadLastUsed, migrateOldPrefs, onItemClick, saveLastUsed, setEmoji, setEmojiGroup`

**`srcs/juloo.keyboard2/EmojiGroupButtonsBar.java`**

- **Classs:** `EmojiGroupButton, EmojiGroupButtonsBar`
- **Functions:** `add_group, get_emoji_grid, onTouch`

**`srcs/juloo.keyboard2/ExportClipboardActivity.java`**

- **Classs:** `ExportClipboardActivity`
- **Functions:** `onActivityResult, onCreate`

**`srcs/juloo.keyboard2/ExtraKeys.java`**

- **Classs:** `ExtraKey, ExtraKeys, Query`
- **Functions:** `compute, merge, merge_with, one_or_none, parse`

**`srcs/juloo.keyboard2/FoldStateTracker.java`**

- **Classs:** `FoldStateTracker, LayoutStateChangeCallback`
- **Functions:** `accept, close, isFoldableDevice, isUnfolded, setChangedCallback`

**`srcs/juloo.keyboard2/Gesture.java`**

- **Classs:** `Corner, Gesture, Name, State`
- **Functions:** `changed_direction, current_direction, dir_diff, get_corner, get_gesture, is_in_progress, moved_to_center, pointer_up`

**`srcs/juloo.keyboard2/ImportClipboardActivity.java`**

- **Classs:** `ImportClipboardActivity`
- **Functions:** `onActivityResult, onCreate`

**`srcs/juloo.keyboard2/KeyEventHandler.java`**

- **Classs:** `Autocapitalisation_callback, IReceiver, KeyEventHandler, Runnable`
- **Functions:** `addSelectedTextToDictionary, can_set_selection, cancel_selection, evaluate_macro, evaluate_macro_loop, get_cursor_pos, handleAutoCorrectionOnSpace, handle_editing_key, handle_slider, if, isWordInDictionary, is_selection_not_empty, key_down, key_up, mods_changed, move_cursor, move_cursor_fallback, move_cursor_sel, move_cursor_vertical, paste_from_clipboard_pane, replaceCurrentWord, revertAutoCorrection, run, selection_updated, sendMetaKey, sendMetaKeyForModifier, sendTextVerbatim, send_context_menu_action, send_key_down_up, send_key_down_up_repeat, send_keyevent, send_text, should_move_cursor_force_fallback, started, updateSuggestionsFromPrefix, update_meta_state, update_shift_state, wait_after_macro_key`

**`srcs/juloo.keyboard2/KeyModifier.java`**

- **Classs:** `KeyModifier`
- **Functions:** `apply_combining_char, apply_compose, apply_compose_or_dead_char, apply_compose_pending, apply_ctrl, apply_dead_char, apply_fn, apply_fn_editing, apply_fn_event, apply_fn_keyevent, apply_fn_placeholder, apply_gesture, apply_selection_mode, apply_shift, combine_hangul_initial, combine_hangul_medial, modify, modify_long_press, modify_numpad_script, set_modmap, turn_into_keyevent`

**`srcs/juloo.keyboard2/KeyValue.java`**

- **Classs:** `Editing, Event, KeyValue, Kind, Macro, Modifier, Placeholder, Slider`
- **Functions:** `charKey, check, compareTo, diacritic, editingKey, equals, eventKey, getChar, getEditing, getEvent, getFlags, getHangulPrecomposed, getKeyByName, getKeyevent, getKind, getMacro, getMetaState, getModifier, getPendingCompose, getPlaceholder, getSlider, getSliderRepeat, getSpecialKeyByName, getString, hasFlagsAny, hashCode, keyeventKey, makeActionKey, makeCharKey, makeComposePending, makeHangulFinal, makeHangulInitial, makeHangulMedial, makeInternalModifier, makeMacro, makeModifiedCharKey, makeStringKey, modifierKey, placeholderKey, sameKey, sliderKey, toString, withChar, withFlags, withKeyevent, withSymbol`

**`srcs/juloo.keyboard2/KeyValueParser.java`**

- **Classs:** `KeyValueParser, ParseError, Starting_with_colon`
- **Functions:** `init, key_by_name_or_str, match, remove_escaping, while`

**`srcs/juloo.keyboard2/Keyboard2.java`**

- **Classs:** `BroadcastReceiver, Keyboard2, Receiver, Runnable`
- **Functions:** `actionLabel_of_imeAction, current_layout, current_layout_unmodified, defaultSubtypes, extra_keys_of_subtype, getConnectionToken, getContext, getCurrentInputConnection, getEnabledSubtypes, getHandler, get_imm, handle_event_key, if, incrTextLayout, inflate_view, loadLayout, loadNumpad, loadPinentry, onCreate, onCurrentInputMethodSubtypeChanged, onDestroy, onEvaluateFullscreenMode, onFinishInputView, onKeyDown, onReceive, onSharedPreferenceChanged, onStartInputView, onUpdateSelection, populateSuggestions, refreshAccentsOption, refreshSubtypeImm, refresh_action_label, refresh_config, refresh_special_layout, reloadCustomDictionary, run, selection_state_changed, setInputView, setSpecialLayout, setTextLayout, setTheme, set_compose_pending, set_shift_state, setupSuggestionStrip, showSuggestions, updateFullscreenMode, updateLayoutGravityOf, updateLayoutHeightOf, updateSoftInputWindowLayoutParams, updateSuggestionStripPosition, updateSuggestionsFromPrefix`

**`srcs/juloo.keyboard2/Keyboard2View.java`**

- **Classs:** `Keyboard2View, Vertical`
- **Functions:** `drawBorder, drawIndication, drawKeyFrame, drawLabel, drawSubLabel, getKeyAtPosition, getKeyPopupText, getParentWindow, getRowAtPosition, labelColor, modifyKey, onApplyWindowInsets, onDetachedFromWindow, onDraw, onLayout, onMeasure, onPointerDown, onPointerFlagsChanged, onPointerHold, onPointerUp, onShowPopup, onTouch, refresh_navigation_bar, reset, scaleTextSize, setKeyboard, set_compose_pending, set_fake_ptr_latched, set_selection_state, set_shift_state, showPopup, updateFlags, vibrate`

**`srcs/juloo.keyboard2/KeyboardData.java`**

- **Classs:** `Key, KeyPos, KeyboardData, MapKey, MapKeyValues, PreferredPos, Row`
- **Functions:** `addExtraKeys, addNumPad, add_key_to_pos, add_key_to_preferred_pos, apply, attribute_bool, attribute_float, compute_max_width, copy, error, findKeyWithValue, getKeyValue, getKeys, get_key_at_pos, hasValue, if, insert_row, keyHasFlag, load, load_string, mapKeys, scaleWidth, stripPrefix, updateWidth, withKeyValue, withShift, with_dir`

**`srcs/juloo.keyboard2/KeyboardExecutors.java`**

- **Classs:** `KeyboardExecutors`

**`srcs/juloo.keyboard2/KeyboardLayoutAnalyzer.java`**

- **Classs:** `KeyInfo, KeyboardLayoutAnalyzer`
- **Functions:** `for, getAdjacencyMap`

**`srcs/juloo.keyboard2/LauncherActivity.java`**

- **Classs:** `LauncherActivity, Tryhere_OnUnhandledKeyEventListener`
- **Functions:** `find_anim, handleMessage, launch_imepicker, launch_imesettings, onCreate, onCreateOptionsMenu, onOptionsItemSelected, onStart, onUnhandledKeyEvent`

**`srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java`**

- **Classs:** `CorrectionCandidate, LayoutBasedAutoCorrectionProvider`
- **Functions:** `equals, getCorrections, getDeletionCandidates, getDoublingSinglingCandidates, getInsertionCandidates, getReversalCandidates, getSimilarWords, getSubstitutionCandidates, getTranspositionCandidates, hashCode, if, updateLayout`

**`srcs/juloo.keyboard2/LayoutModifier.java`**

- **Classs:** `LayoutModifier`
- **Functions:** `apply, init, inverse_numpad_char, modify_key, modify_layout, modify_number_row, modify_numpad, modify_pinentry, numpad_script_map`

**`srcs/juloo.keyboard2/Logs.java`**

- **Classs:** `Logs`
- **Functions:** `debug, debug_config_migration, debug_startup_input_view, exn, set_debug_logs, trace`

**`srcs/juloo.keyboard2/Modmap.java`**

- **Classs:** `M, Modmap`
- **Functions:** `add, get`

**`srcs/juloo.keyboard2/NumberLayout.java`**

- **Classs:** `NumberLayout`
- **Functions:** `of_string`

**`srcs/juloo.keyboard2/Pointers.java`**

- **Classs:** `IPointerEventHandler, Modifiers, ModifiersDiffIterator, Pointer, Pointers, Sliding`
- **Functions:** `add_fake_pointer, advance, apply_gesture, clear, clearLatched, diff, equals, get, getKeyAtDirection, getKeyFlags, getLatched, getModifiers, getNearestKeyAtDirection, getPtr, handleLongPress, handleMessage, has, hasFlagsAny, hasNext, hashCode, if, isKeyDown, isOtherPointerDown, isSliding, lockPointer, make_pointer, modify_key_with_extra_modifier, next, ofArray, onTouchCancel, onTouchDown, onTouchMove, onTouchUp, pointer_flags_of_kv, removePtr, restartLongPress, set_fake_pointer_state, size, startLongPress, startSliding, stopLongPress, update_speed, with_extra_mod`

**`srcs/juloo.keyboard2/SettingsActivity.java`**

- **Classs:** `SettingsActivity`
- **Functions:** `fallbackEncrypted, onCreate, onStop`

**`srcs/juloo.keyboard2/SuggestionProvider.java`**

- **Classs:** `SuggestionProvider, TrieNode, WordSource`
- **Functions:** `findAllWords, findPrefixNode, getSuggestions, getWordSource, if, insert, isValidWord, loadCustomDictionary, loadDictionary, reloadCustomDictionary`

**`srcs/juloo.keyboard2/Theme.java`**

- **Classs:** `Computed, Key, Theme`
- **Functions:** `adjustLight, getKeyFont, initIndicationPaint, init_border_paint, init_label_paint, label_paint, sublabel_paint`

**`srcs/juloo.keyboard2/Utils.java`**

- **Classs:** `Utils`
- **Functions:** `capitalize_string, show_dialog_on_ime`

**`srcs/juloo.keyboard2/VibratorCompat.java`**

- **Classs:** `VibratorCompat`
- **Functions:** `get_vibrator, vibrate, vibrator_vibrate`

**`srcs/juloo.keyboard2/VoiceImeSwitcher.java`**

- **Classs:** `IME, VoiceImeSwitcher`
- **Functions:** `choose_voice_ime, choose_voice_ime_and_update_prefs, get_display_name, get_id, get_ime_by_id, get_ime_display_names, get_voice_ime_list, onClick, serialize_ime_ids, switch_input_method, switch_to_voice_ime`

**`srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java`**

- **Classs:** `CustomExtraKeysPreference`
- **Functions:** `get, get_serializer, label_of_value, onClick, select`

**`srcs/juloo.keyboard2/prefs/ExtraKeysPreference.java`**

- **Classs:** `ExtraKeyCheckBoxPreference, ExtraKeysPreference`
- **Functions:** `default_checked, format_key_combination, format_key_combination_gesture, get_extra_keys, if, implements, key_description, key_preferred_pos, key_title, mk_preferred_pos, onAttachedToActivity, onBindView, pref_key_of_key_name`

**`srcs/juloo.keyboard2/prefs/IntSlideBarPreference.java`**

- **Classs:** `IntSlideBarPreference`
- **Functions:** `onCreateDialogView, onDialogClosed, onGetDefaultValue, onProgressChanged, onSetInitialValue, onStartTrackingTouch, onStopTrackingTouch, updateText`

**`srcs/juloo.keyboard2/prefs/LayoutsPreference.java`**

- **Classs:** `CustomLayout, Layout, LayoutsAddButton, LayoutsPreference, NamedLayout, Serializer, SystemLayout`
- **Functions:** `get_layout_names, get_serializer, if, label_of_layout, label_of_value, layout_id_of_name, layout_of_string, load_from_preferences, onClick, onSetInitialValue, on_attach_add_button, parse, read_initial_custom_layout, save_to_preferences, select, select_custom, select_dialog, should_allow_remove_item, validate`

**`srcs/juloo.keyboard2/prefs/ListGroupPreference.java`**

- **Classs:** `AddButton, Item, ListGroupPreference, SelectionCallback, Serializer, StringSerializer`
- **Functions:** `add_item, allow_remove, change_item, is, load_from_preferences, load_from_string, load_item, onAttachedToActivity, onClick, onCreateView, onSetInitialValue, on_attach_add_button, reattach, remove_item, save_item, save_to_preferences, save_to_string, select, set_values, should_allow_remove_item`

**`srcs/juloo.keyboard2/prefs/SlideBarPreference.java`**

- **Classs:** `SlideBarPreference`
- **Functions:** `float_of_string, onCreateDialogView, onDialogClosed, onGetDefaultValue, onProgressChanged, onSetInitialValue, onStartTrackingTouch, onStopTrackingTouch, updateText`

**`srcs/res/SvgToVector.java`**

- **Classs:** `SvgToVector`
- **Functions:** `main`

**`sync_translations.py`**

- **Functions:** `key, parse_strings_file, print_status, sync, sync_meta_file, sync_metadata, write_updated_strings`

**`test/juloo.keyboard2/ComposeKeyTest.java`**

- **Classs:** `ComposeKeyTest`
- **Functions:** `apply`

**`test/juloo.keyboard2/KeyValueParserTest.java`**

- **Classs:** `KeyValueParserTest, Utils`
- **Functions:** `expect_error`

**`test/juloo.keyboard2/KeyValueTest.java`**

- **Classs:** `KeyValueTest`
- **Functions:** `apply_numpad_script, equals, numpad_script`

**`test/juloo.keyboard2/ModmapTest.java`**

- **Classs:** `ModmapTest, Utils`
- **Functions:** `apply, keyevent_mappings, test`

## 2. Connections and References

### File: `check_layout.py`

- **Function `check_layout`** connects **internally** to code: `key_list_str, missing_required, missing_some_of, parse_row, unexpected_keys, warn` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `root.get, warn`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `root.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `root.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `root.get`).
- **Function `missing_required`** connects **internally** to code: `key_list_str, warn` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `warn`).
- **Function `missing_some_of`** connects **internally** to code: `key_list_str, warn` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `warn`).
- **Function `parse_layout`** connects **internally** to code: `parse_row_from_et` and connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `ET.parse`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `ET.parse`).
- **Function `parse_row`** connects **internally** to code: `parse_row_from_et` and connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `ET.parse`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `ET.parse`).
- **Function `parse_row_from_et`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `key.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `dup.add, key.get, keys.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `key.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `key.get`).
- **Function `unexpected_keys`** connects **internally** to code: `key_list_str, warn` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `warn`).

---

### File: `gen_emoji.py`


---

### File: `gen_layouts.py`

- **Function `generate_arrays`** connects **internally** to code: `mk_array`.
- **Function `read_layout`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `root.get`); file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `XML.parse`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `root.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `root.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `root.get`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `XML.parse`).
- **Function `read_layouts`** connects **internally** to code: `read_layout`.

---

### File: `gen_sinhala_phonetic_layout.py`

- **Class `LayoutBuilder`** connects **internally** to code: `LayoutGenError, Placement, cls._add_extra_chars_to_ref_map, cls._move_untransited_to_new_map, is_in_escape_list, self._apply_transitions, self._make_extra_modmap, self._parse_reference_layout, self._post_escape, self._process_key, self._resolve_placement, xml_elem_to_str, xml_encode_char` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `KEYS_MAP.get, coord_map.get, key.get, new_key.attrib.get, new_key.get, to_key.get`); file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `ElementTree.parse`); file `srcs/juloo.keyboard2/Logs.java` (calling: `LOGGER.debug`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `KEYS_MAP.get, coord_map.get, key.get, new_key.attrib.get, new_key.get, to_key.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `KEYS_MAP.get, coord_map.get, key.get, new_key.attrib.get, new_key.get, to_key.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `KEYS_MAP.get, coord_map.get, key.get, new_key.attrib.get, new_key.get, to_key.get`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `ElementTree.parse`).
- **Function `_add_extra_chars_to_ref_map`** connects **internally** to code: `LayoutGenError` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `coord_map.get, key.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `coord_map.get, key.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `coord_map.get, key.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `coord_map.get, key.get`).
- **Function `_apply_transitions`** connects **internally** to code: `LayoutGenError, cls._add_extra_chars_to_ref_map, cls._move_untransited_to_new_map` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `coord_map.get, key.get, to_key.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `coord_map.get, key.get, to_key.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `coord_map.get, key.get, to_key.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `coord_map.get, key.get, to_key.get`).
- **Function `_move_untransited_to_new_map`** connects **internally** to code: `LayoutGenError` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `new_key.attrib.get, new_key.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `new_key.attrib.get, new_key.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `new_key.attrib.get, new_key.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `new_key.attrib.get, new_key.get`).
- **Function `_parse_reference_layout`** connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `ElementTree.parse`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `ElementTree.parse`).
- **Function `_post_escape`** connects **internally** to code: `is_in_escape_list, xml_encode_char`.
- **Function `_process_key`** connects **internally** to code: `LayoutGenError, Placement, self._resolve_placement` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `KEYS_MAP.get, key.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `KEYS_MAP.get, key.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `KEYS_MAP.get, key.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `KEYS_MAP.get, key.get`).
- **Function `_resolve_placement`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `key.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `key.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `key.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `key.get`).
- **Function `build`** connects **internally** to code: `self._apply_transitions, self._make_extra_modmap, self._parse_reference_layout, self._process_key, xml_elem_to_str` and connects **externally** to code in file `srcs/juloo.keyboard2/Logs.java` (calling: `LOGGER.debug`).
- **Function `get_xml`** connects **internally** to code: `self._post_escape, xml_elem_to_str`.

---

### File: `srcs/compose/compile.py`

- **Function `add_node`** connects **internally** to code: `add_leaf, add_tree` and **reads configuration** from file `funding.json` (reading: `type`).
- **Function `add_sequences_to_trie`** connects **internally** to code: `add_seq_to_trie, existing_sequence_to_str, warn` and connects **externally** to code in file `check_layout.py` (calling: `warn`).
- **Function `add_tree`** connects **internally** to code: `add_node`.
- **Function `char_repr`** **reads configuration** from file `funding.json` (reading: `type`).
- **Function `check_for_warnings`** connects **internally** to code: `get, seq_to_str, warn` and connects **externally** to code in file `check_layout.py` (calling: `warn`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `get`) and **reads configuration** from file `funding.json` (reading: `type`).
- **Function `gen_array`** connects **internally** to code: `batched`.
- **Function `gen_java`** connects **internally** to code: `batched, gen_array` and **reads configuration** from file `funding.json` (reading: `type`).
- **Function `get`** **reads configuration** from file `funding.json` (reading: `type`).
- **Function `make_automata`** connects **internally** to code: `add_leaf, add_node, add_tree` and **reads configuration** from file `funding.json` (reading: `type`).
- **Function `parse_keysymdef_h`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValueParser.java` (calling: `re.match`).
- **Function `parse_seq_line`** connects **internally** to code: `parse_seq_chars, parse_seq_result` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValueParser.java` (calling: `re.match`).
- **Function `parse_sequences_dir`** connects **internally** to code: `parse_keysymdef_h, parse_sequences_file`.
- **Function `parse_sequences_file`** connects **internally** to code: `parse_sequences_file_json, parse_sequences_file_xkb`.
- **Function `parse_sequences_file_json`** connects **internally** to code: `strip_cstyle_comments, tree_to_seqs, warn` and connects **externally** to code in file `check_layout.py` (calling: `warn`).
- **Function `parse_sequences_file_xkb`** connects **internally** to code: `parse_seq_chars, parse_seq_line, parse_seq_result` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValueParser.java` (calling: `re.match`).
- **Function `tree_to_seqs`** connects **internally** to code: `tree_to_seqs`.
- **Function `warn`** connects **internally** to code: `seq_to_str`.

---

### File: `srcs/juloo.keyboard2/Autocapitalisation.java`

- **Class `Runnable`** connects **internally** to code: `Runnable` and connects **externally** to code in file `srcs/juloo.keyboard2/CustomLayoutEditDialog.java` (calling: `Runnable`); file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `Runnable`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `Runnable`).
- **Function `callback_now`** connects **internally** to code: `delayed_callback.run` and connects **externally** to code in file `srcs/juloo.keyboard2/CustomLayoutEditDialog.java` (calling: `delayed_callback.run`); file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `delayed_callback.run`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `delayed_callback.run`).
- **Function `run`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `_callback.update_shift_state`).
- **Function `selection_updated`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `t.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `t.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `t.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `t.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `t.equals`).
- **Function `started`** connects **externally** to code in file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalConfig`).

---

### File: `srcs/juloo.keyboard2/ClipboardHistoryCheckBox.java`

- **Class `ClipboardHistoryCheckBox`** connects **externally** to code in file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalConfig`).
- **Function `onCheckedChanged`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardHistoryService.java` (calling: `ClipboardHistoryService.set_history_enabled`).

---

### File: `srcs/juloo.keyboard2/ClipboardHistoryService.java`

- **Class `ClipboardHistoryService`** connects **internally** to code: `SystemListener`.
- **Function `addClip`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `ClipboardItem`); file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalConfig`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `items.add`).
- **Function `addCurrentClip`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardView.java` (calling: `clip.getItemCount`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `text.toString`).
- **Function `clearHistory`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `items.clear`).
- **Function `compare`** connects **internally** to code: `Long.compare` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `o1.getTimestamp, o1.isPinned, o2.getTimestamp, o2.isPinned`); file `srcs/juloo.keyboard2/EmojiGridView.java` (calling: `Long.compare`).
- **Function `exportToUri`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `jsonArray.toString`) and **reads configuration** from file `srcs/compose/accent_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_caron.json` (reading: `Log.e`); file `srcs/compose/accent_cedille.json` (reading: `Log.e`); file `srcs/compose/accent_circonflexe.json` (reading: `Log.e`); file `srcs/compose/accent_dot_above.json` (reading: `Log.e`); file `srcs/compose/accent_dot_below.json` (reading: `Log.e`); file `srcs/compose/accent_double_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_double_grave.json` (reading: `Log.e`); file `srcs/compose/accent_grave.json` (reading: `Log.e`); file `srcs/compose/accent_hook_above.json` (reading: `Log.e`); file `srcs/compose/accent_macron.json` (reading: `Log.e`); file `srcs/compose/accent_ogonek.json` (reading: `Log.e`); file `srcs/compose/accent_slash.json` (reading: `Log.e`); file `srcs/compose/accent_subscript.json` (reading: `Log.e`); file `srcs/compose/accent_superscript.json` (reading: `Log.e`); file `srcs/compose/accent_tilde.json` (reading: `Log.e`); file `srcs/compose/accent_trema.json` (reading: `Log.e`); file `srcs/compose/compose/extra.json` (reading: `Log.e`); file `srcs/compose/fn.json` (reading: `Log.e`).
- **Function `get_service`** connects **internally** to code: `ClipboardHistoryService`.
- **Function `importFromUri`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `sb.toString`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `items.add`) and **reads configuration** from file `srcs/compose/accent_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_caron.json` (reading: `Log.e`); file `srcs/compose/accent_cedille.json` (reading: `Log.e`); file `srcs/compose/accent_circonflexe.json` (reading: `Log.e`); file `srcs/compose/accent_dot_above.json` (reading: `Log.e`); file `srcs/compose/accent_dot_below.json` (reading: `Log.e`); file `srcs/compose/accent_double_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_double_grave.json` (reading: `Log.e`); file `srcs/compose/accent_grave.json` (reading: `Log.e`); file `srcs/compose/accent_hook_above.json` (reading: `Log.e`); file `srcs/compose/accent_macron.json` (reading: `Log.e`); file `srcs/compose/accent_ogonek.json` (reading: `Log.e`); file `srcs/compose/accent_slash.json` (reading: `Log.e`); file `srcs/compose/accent_subscript.json` (reading: `Log.e`); file `srcs/compose/accent_superscript.json` (reading: `Log.e`); file `srcs/compose/accent_tilde.json` (reading: `Log.e`); file `srcs/compose/accent_trema.json` (reading: `Log.e`); file `srcs/compose/compose/extra.json` (reading: `Log.e`); file `srcs/compose/fn.json` (reading: `Log.e`).
- **Function `isSystemClipboard`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardView.java` (calling: `clip.getItemCount`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `clipText.toString`).
- **Function `loadItems`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `sb.toString`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `items.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `items.clear`) and **reads configuration** from file `srcs/compose/accent_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_caron.json` (reading: `Log.e`); file `srcs/compose/accent_cedille.json` (reading: `Log.e`); file `srcs/compose/accent_circonflexe.json` (reading: `Log.e`); file `srcs/compose/accent_dot_above.json` (reading: `Log.e`); file `srcs/compose/accent_dot_below.json` (reading: `Log.e`); file `srcs/compose/accent_double_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_double_grave.json` (reading: `Log.e`); file `srcs/compose/accent_grave.json` (reading: `Log.e`); file `srcs/compose/accent_hook_above.json` (reading: `Log.e`); file `srcs/compose/accent_macron.json` (reading: `Log.e`); file `srcs/compose/accent_ogonek.json` (reading: `Log.e`); file `srcs/compose/accent_slash.json` (reading: `Log.e`); file `srcs/compose/accent_subscript.json` (reading: `Log.e`); file `srcs/compose/accent_superscript.json` (reading: `Log.e`); file `srcs/compose/accent_tilde.json` (reading: `Log.e`); file `srcs/compose/accent_trema.json` (reading: `Log.e`); file `srcs/compose/compose/extra.json` (reading: `Log.e`); file `srcs/compose/fn.json` (reading: `Log.e`).
- **Function `migrateFromPrefs`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `ClipboardItem`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `arr.getString, store.getString`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `items.add`) and **reads configuration** from file `srcs/compose/accent_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_caron.json` (reading: `Log.e`); file `srcs/compose/accent_cedille.json` (reading: `Log.e`); file `srcs/compose/accent_circonflexe.json` (reading: `Log.e`); file `srcs/compose/accent_dot_above.json` (reading: `Log.e`); file `srcs/compose/accent_dot_below.json` (reading: `Log.e`); file `srcs/compose/accent_double_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_double_grave.json` (reading: `Log.e`); file `srcs/compose/accent_grave.json` (reading: `Log.e`); file `srcs/compose/accent_hook_above.json` (reading: `Log.e`); file `srcs/compose/accent_macron.json` (reading: `Log.e`); file `srcs/compose/accent_ogonek.json` (reading: `Log.e`); file `srcs/compose/accent_slash.json` (reading: `Log.e`); file `srcs/compose/accent_subscript.json` (reading: `Log.e`); file `srcs/compose/accent_superscript.json` (reading: `Log.e`); file `srcs/compose/accent_tilde.json` (reading: `Log.e`); file `srcs/compose/accent_trema.json` (reading: `Log.e`); file `srcs/compose/compose/extra.json` (reading: `Log.e`); file `srcs/compose/fn.json` (reading: `Log.e`).
- **Function `notifyHistoryChange`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardView.java` (calling: `listener.on_clipboard_history_change`).
- **Function `paste`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `_paste_callback.paste_from_clipboard_pane`).
- **Function `persistItems`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `jsonArray.toString`) and **reads configuration** from file `srcs/compose/accent_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_caron.json` (reading: `Log.e`); file `srcs/compose/accent_cedille.json` (reading: `Log.e`); file `srcs/compose/accent_circonflexe.json` (reading: `Log.e`); file `srcs/compose/accent_dot_above.json` (reading: `Log.e`); file `srcs/compose/accent_dot_below.json` (reading: `Log.e`); file `srcs/compose/accent_double_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_double_grave.json` (reading: `Log.e`); file `srcs/compose/accent_grave.json` (reading: `Log.e`); file `srcs/compose/accent_hook_above.json` (reading: `Log.e`); file `srcs/compose/accent_macron.json` (reading: `Log.e`); file `srcs/compose/accent_ogonek.json` (reading: `Log.e`); file `srcs/compose/accent_slash.json` (reading: `Log.e`); file `srcs/compose/accent_subscript.json` (reading: `Log.e`); file `srcs/compose/accent_superscript.json` (reading: `Log.e`); file `srcs/compose/accent_tilde.json` (reading: `Log.e`); file `srcs/compose/accent_trema.json` (reading: `Log.e`); file `srcs/compose/compose/extra.json` (reading: `Log.e`); file `srcs/compose/fn.json` (reading: `Log.e`).
- **Function `removeItem`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `item.getText`).
- **Function `set_history_enabled`** connects **internally** to code: `_service.addCurrentClip, _service.clearHistory` and connects **externally** to code in file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalConfig`).
- **Function `togglePin`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `items.get`); file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `currentItem.isPinned, currentItem.setPinned, currentItem.setTimestamp`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `items.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `items.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `items.get`).
- **Function `trimHistory`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `unpinnedItems.get`); file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `item.isPinned`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `unpinnedItems.add, unpinnedItems.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `unpinnedItems.get, unpinnedItems.size`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `unpinnedItems.get`).

---

### File: `srcs/juloo.keyboard2/ClipboardItem.java`

- **Function `equals`** connects **internally** to code: `text.equals` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `text.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `text.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `text.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `text.equals`).
- **Function `hashCode`** connects **internally** to code: `ClipboardItem, text.hashCode` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `json.getString, text.hashCode`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `text.hashCode`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `text.hashCode`).

---

### File: `srcs/juloo.keyboard2/ClipboardView.java`

- **Class `ClipboardAdapter`** connects **internally** to code: `ClipboardAdapter.ViewHolder`.
- **Class `ClipboardView`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardHistoryService.java` (calling: `ClipboardHistoryService.get_service`).
- **Class `ViewHolder`** connects **internally** to code: `RecyclerView.ViewHolder`.
- **Function `getItem`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `items.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `items.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `items.get, items.size`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `items.get`).
- **Function `getItemCount`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `items.size`).
- **Function `if`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardHistoryService.java` (calling: `service.removeItem`).
- **Function `onAttachedToWindow`** connects **internally** to code: `super.onAttachedToWindow` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardHistoryService.java` (calling: `service.setOnClipboardHistoryChange`).
- **Function `onBindViewHolder`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `items.get`); file `srcs/juloo.keyboard2/ClipboardHistoryService.java` (calling: `ClipboardHistoryService.paste, service.togglePin`); file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `item.getText, item.isPinned`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `items.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `items.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `items.get`).
- **Function `onCreateViewHolder`** connects **internally** to code: `ViewHolder` and connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `parent.getContext`).
- **Function `onDetachedFromWindow`** connects **internally** to code: `super.onDetachedFromWindow` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardHistoryService.java` (calling: `service.setOnClipboardHistoryChange`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `super.onDetachedFromWindow`).
- **Function `onFinishInflate`** connects **internally** to code: `ClipboardAdapter, GestureCallback, super.onFinishInflate` and connects **externally** to code in file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalConfig`); file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `handler.key_up`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getSpecialKeyByName`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `bottomRowView.setKeyboard`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.load`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers`).
- **Function `onMove`** connects **internally** to code: `RecyclerView.ViewHolder`.
- **Function `onSwiped`** connects **internally** to code: `RecyclerView.ViewHolder, adapter.getItem` and connects **externally** to code in file `srcs/juloo.keyboard2/EmojiGridView.java` (calling: `adapter.getItem`).
- **Function `updateData`** connects **internally** to code: `adapter.setItems` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardHistoryService.java` (calling: `service.getItems`).

---

### File: `srcs/juloo.keyboard2/ComposeKey.java`

- **Function `apply`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Kind, k.getKind, k.getPendingCompose`).

---

### File: `srcs/juloo.keyboard2/Config.java`

- **Class `IKeyEventHandler`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers`).
- **Function `initGlobalConfig`** connects **internally** to code: `Config` and connects **externally** to code in file `srcs/juloo.keyboard2/Emoji.java` (calling: `LayoutModifier.init`); file `srcs/juloo.keyboard2/KeyValueParser.java` (calling: `LayoutModifier.init`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `LayoutModifier.init`).
- **Function `migrate`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `custom_layout.equals, snd_layout.equals`); file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `e.apply`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `custom_layout.equals, prefs.getString, snd_layout.equals`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `e.apply`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `custom_layout.equals, snd_layout.equals`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `e.apply`); file `srcs/juloo.keyboard2/Logs.java` (calling: `Logs.debug_config_migration`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `l.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `custom_layout.equals, snd_layout.equals`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `LayoutsPreference.CustomLayout, LayoutsPreference.Layout, LayoutsPreference.save_to_preferences`); file `srcs/juloo.keyboard2/prefs/ListGroupPreference.java` (calling: `LayoutsPreference.save_to_preferences`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `e.apply`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `custom_layout.equals, snd_layout.equals`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `e.apply`).
- **Function `migrate_layout`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `name.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `name.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `name.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `name.equals`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `LayoutsPreference.Layout, LayoutsPreference.NamedLayout, LayoutsPreference.SystemLayout`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `name.equals`).
- **Function `refresh`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `CustomExtraKeysPreference.get`); file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `number_row.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `_prefs.getString, number_row.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `number_row.equals`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `CustomExtraKeysPreference.get`); file `srcs/juloo.keyboard2/NumberLayout.java` (calling: `NumberLayout.of_string`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `CustomExtraKeysPreference.get, number_row.equals`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `CustomExtraKeysPreference.get`); file `srcs/juloo.keyboard2/prefs/ExtraKeysPreference.java` (calling: `ExtraKeysPreference.get_extra_keys`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `LayoutsPreference.load_from_preferences`); file `srcs/juloo.keyboard2/prefs/ListGroupPreference.java` (calling: `LayoutsPreference.load_from_preferences`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `number_row.equals`).
- **Function `set_current_layout`** connects **externally** to code in file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `e.apply`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `e.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `e.apply`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `e.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `e.apply`).

---

### File: `srcs/juloo.keyboard2/CustomDictionarySettingsActivity.java`

- **Function `importDictionary`** connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `existingWords.add`).
- **Function `onActivityResult`** connects **internally** to code: `super.onActivityResult` and connects **externally** to code in file `srcs/juloo.keyboard2/ExportClipboardActivity.java` (calling: `super.onActivityResult`); file `srcs/juloo.keyboard2/ImportClipboardActivity.java` (calling: `super.onActivityResult`).
- **Function `onCreate`** connects **internally** to code: `super.onCreate` and connects **externally** to code in file `srcs/juloo.keyboard2/ExportClipboardActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/ImportClipboardActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/LauncherActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/SettingsActivity.java` (calling: `super.onCreate`).

---

### File: `srcs/juloo.keyboard2/CustomLayoutEditDialog.java`

- **Class `Runnable`** connects **internally** to code: `Runnable` and connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `Runnable`); file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `Runnable`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `Runnable`).
- **Function `onClick`** connects **internally** to code: `LayoutEntryEditText.OnChangeListener, input.set_on_text_change` and connects **externally** to code in file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `callback.select`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `callback.select`); file `srcs/juloo.keyboard2/prefs/ListGroupPreference.java` (calling: `callback.select`).
- **Function `onDraw`** connects **internally** to code: `super.onDraw` and connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `super.onDraw`).
- **Function `on_change`** connects **internally** to code: `dialog.show` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `input.getText`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `callback.validate`).
- **Function `run`** connects **internally** to code: `l.on_change`.
- **Function `show`** connects **internally** to code: `LayoutEntryEditText`.

---

### File: `srcs/juloo.keyboard2/DirectBootAwarePreferences.java`

- **Function `copy_shared_preferences`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `entries.get`); file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `e.apply`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `e.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `e.apply`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `entries.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `entries.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `entries.get`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `e.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `e.apply`).

---

### File: `srcs/juloo.keyboard2/Emoji.java`

- **Class `Emoji`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue, KeyValue.Kind`).
- **Function `getEmojiByString`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_stringMap.get`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `sb.toString`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_stringMap.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_stringMap.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_stringMap.get`).
- **Function `getEmojisByGroup`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_groups.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_groups.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_groups.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_groups.get`).
- **Function `getNumGroups`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `_groups.size`).
- **Function `init`** connects **internally** to code: `Emoji, Emoji.init` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValueParser.java` (calling: `Emoji.init`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `Emoji.init`); file `srcs/juloo.keyboard2/Logs.java` (calling: `Logs.exn`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_all.add, _groups.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_all.size`).

---

### File: `srcs/juloo.keyboard2/EmojiGridView.java`

- **Class `EmojiGridView`** connects **externally** to code in file `srcs/juloo.keyboard2/Emoji.java` (calling: `Emoji.init`); file `srcs/juloo.keyboard2/KeyValueParser.java` (calling: `Emoji.init`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `Emoji.init`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_lastUsed.size`).
- **Function `compare`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_lastUsed.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_lastUsed.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_lastUsed.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_lastUsed.get`).
- **Function `getCount`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `_emojiArray.size`).
- **Function `getItem`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_emojiArray.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_emojiArray.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_emojiArray.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_emojiArray.get`).
- **Function `getView`** connects **internally** to code: `EmojiView, view.setEmoji` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `_emojiArray.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_emojiArray.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_emojiArray.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_emojiArray.get`).
- **Function `loadLastUsed`** connects **externally** to code in file `srcs/juloo.keyboard2/Emoji.java` (calling: `Emoji.getEmojiByString`).
- **Function `migrateOldPrefs`** connects **externally** to code in file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `edit.apply`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `edit.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `edit.apply`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `lastUsedNew.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `edit.clear`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `edit.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `edit.apply`).
- **Function `onItemClick`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_emojiArray.get, _lastUsed.get`); file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalConfig`); file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `config.handler.key_up`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_emojiArray.get, _lastUsed.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers, _emojiArray.get, _lastUsed.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_emojiArray.get, _lastUsed.get`).
- **Function `saveLastUsed`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_lastUsed.get`); file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `edit.apply`); file `srcs/juloo.keyboard2/Emoji.java` (calling: `emoji.kv`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `edit.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `edit.apply`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_lastUsed.get, set.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_lastUsed.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_lastUsed.get`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `edit.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `edit.apply`).
- **Function `setEmoji`** connects **externally** to code in file `srcs/juloo.keyboard2/Emoji.java` (calling: `emoji.kv`).
- **Function `setEmojiGroup`** connects **internally** to code: `EmojiViewAdpater` and connects **externally** to code in file `srcs/juloo.keyboard2/Emoji.java` (calling: `Emoji.getEmojisByGroup`).

---

### File: `srcs/juloo.keyboard2/EmojiGroupButtonsBar.java`

- **Class `EmojiGroupButtonsBar`** connects **externally** to code in file `srcs/juloo.keyboard2/Emoji.java` (calling: `Emoji.getEmojisByGroup, Emoji.getNumGroups, Emoji.init, first.kv`); file `srcs/juloo.keyboard2/KeyValueParser.java` (calling: `Emoji.init`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `Emoji.init`).
- **Function `add_group`** connects **internally** to code: `EmojiGroupButton`.

---

### File: `srcs/juloo.keyboard2/ExportClipboardActivity.java`

- **Function `onActivityResult`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardHistoryService.java` (calling: `ClipboardHistoryService.get_service`).
- **Function `onCreate`** connects **internally** to code: `super.onCreate` and connects **externally** to code in file `srcs/juloo.keyboard2/CustomDictionarySettingsActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/ImportClipboardActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/LauncherActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/SettingsActivity.java` (calling: `super.onCreate`).

---

### File: `srcs/juloo.keyboard2/ExtraKeys.java`

- **Function `compute`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `alternatives.get`); file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `q.script.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `q.script.equals`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.PreferredPos`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `q.script.equals`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `alternatives.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `alternatives.get, alternatives.size, q.script.equals`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `alternatives.get`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `q.script.equals`).
- **Function `merge`** connects **internally** to code: `ExtraKeys, k.merge_with` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `merged_keys.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `merged_keys.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `merged_keys.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `merged_keys.get`).
- **Function `merge_with`** connects **internally** to code: `ExtraKey`.
- **Function `one_or_none`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `a.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `a.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `a.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `a.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `a.equals`).
- **Function `parse`** connects **internally** to code: `ExtraKey` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue, KeyValue.getKeyByName`).

---

### File: `srcs/juloo.keyboard2/FoldStateTracker.java`

- **Class `FoldStateTracker`** connects **internally** to code: `LayoutStateChangeCallback`.
- **Function `accept`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_changedCallback.run`); file `srcs/juloo.keyboard2/CustomLayoutEditDialog.java` (calling: `_changedCallback.run`); file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `_changedCallback.run`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_changedCallback.run`).

---

### File: `srcs/juloo.keyboard2/Gesture.java`

- **Function `changed_direction`** connects **externally** to code in file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalConfig`).

---

### File: `srcs/juloo.keyboard2/ImportClipboardActivity.java`

- **Function `onActivityResult`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardHistoryService.java` (calling: `ClipboardHistoryService.get_service`).
- **Function `onCreate`** connects **internally** to code: `super.onCreate` and connects **externally** to code in file `srcs/juloo.keyboard2/CustomDictionarySettingsActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/ExportClipboardActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/LauncherActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/SettingsActivity.java` (calling: `super.onCreate`).

---

### File: `srcs/juloo.keyboard2/KeyEventHandler.java`

- **Class `Autocapitalisation_callback`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `Autocapitalisation.Callback`); file `srcs/juloo.keyboard2/CustomLayoutEditDialog.java` (calling: `Autocapitalisation.Callback`).
- **Class `IReceiver`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Event`).
- **Class `KeyEventHandler`** connects **internally** to code: `Autocapitalisation_callback` and connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `Autocapitalisation`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `recv.getHandler`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers`).
- **Function `addSelectedTextToDictionary`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `selectedText.toString`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getContext, _recv.getCurrentInputConnection, _recv.reloadCustomDictionary`); file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `_recv.reloadCustomDictionary`).
- **Function `cancel_selection`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection, _recv.selection_state_changed`).
- **Function `evaluate_macro`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_autocap.pause`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers`).
- **Function `evaluate_macro_loop`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_autocap.unpause`); file `srcs/juloo.keyboard2/KeyModifier.java` (calling: `KeyModifier.modify`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `kv.hasFlagsAny`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers, kv.hasFlagsAny, mods.with_extra_mod`).
- **Function `handleAutoCorrectionOnSpace`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `corrections.get`); file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_autocap.typed`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection, _recv.showSuggestions`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `_autoCorrectionProvider.getCorrections`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `corrections.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `corrections.get, revertedWords.clear`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `corrections.get`).
- **Function `handle_editing_key`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Editing`).
- **Function `handle_slider`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Slider`).
- **Function `if`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers`).
- **Function `isWordInDictionary`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getContext`).
- **Function `is_selection_not_empty`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection`).
- **Function `key_down`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_autocap.stop`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `key.getKind, key.getModifier, key.getSlider, key.getSliderRepeat`).
- **Function `key_up`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Kind, key.getChar, key.getEditing, key.getEvent, key.getKeyevent, key.getKind, key.getMacro, key.getMetaState, key.getSlider, key.getSliderRepeat, key.getString`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.handle_event_key, _recv.set_compose_pending`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_recv.set_compose_pending`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyCharacterMap.load`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers`).
- **Function `mods_changed`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers`).
- **Function `move_cursor`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection`).
- **Function `move_cursor_sel`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection`).
- **Function `replaceCurrentWord`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_autocap.typed`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection, _recv.showSuggestions`).
- **Function `revertAutoCorrection`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `revertedWords.add`).
- **Function `run`** connects **internally** to code: `Runnable` and connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `Runnable`); file `srcs/juloo.keyboard2/CustomLayoutEditDialog.java` (calling: `Runnable`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `Runnable, _recv.getHandler`).
- **Function `selection_updated`** connects **internally** to code: `_autocap.selection_updated` and connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_autocap.selection_updated`).
- **Function `sendMetaKeyForModifier`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `kv.getKind, kv.getModifier`).
- **Function `sendTextVerbatim`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_autocap.typed`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection, _recv.showSuggestions`).
- **Function `send_context_menu_action`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection`).
- **Function `send_keyevent`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_autocap.event_sent`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection`).
- **Function `send_text`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_autocap.typed`); file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalConfig`); file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `Uri.parse`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `selectedText.toString, text.toString`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getContext, _recv.getCurrentInputConnection`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `Uri.parse`).
- **Function `started`** connects **internally** to code: `_autocap.started` and connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_autocap.started`); file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalConfig`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection, _recv.showSuggestions`).
- **Function `updateSuggestionsFromPrefix`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.getCurrentInputConnection, _recv.showSuggestions`); file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `_suggestionProvider.getSuggestions`).
- **Function `update_meta_state`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers, _mods.diff, it.hasNext, it.next, mods.diff`).
- **Function `update_shift_state`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `_recv.set_shift_state`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_recv.set_shift_state`).
- **Function `wait_after_macro_key`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `kv.getKind`).

---

### File: `srcs/juloo.keyboard2/KeyModifier.java`

- **Function `apply_combining_char`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.makeStringKey, k.getChar, k.getFlags, k.getKind`).
- **Function `apply_compose`** connects **externally** to code in file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `ComposeKey.apply`).
- **Function `apply_compose_or_dead_char`** connects **externally** to code in file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `ComposeKey.apply`).
- **Function `apply_compose_pending`** connects **externally** to code in file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName, kv.getFlags, kv.getKind, kv.withFlags`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `ComposeKey.apply`).
- **Function `apply_ctrl`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_modmap.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `Modmap.M, _modmap.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_modmap.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_modmap.get`).
- **Function `apply_dead_char`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.makeStringKey, k.getChar, k.getKind`).
- **Function `apply_fn`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_modmap.get`); file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName, k.getEditing, k.getEvent, k.getKeyevent, k.getKind, k.getPlaceholder`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `Modmap.M, _modmap.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_modmap.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_modmap.get`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `ComposeKey.apply`).
- **Function `apply_fn_editing`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Editing`).
- **Function `apply_fn_event`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Event`).
- **Function `apply_fn_placeholder`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Placeholder`).
- **Function `apply_gesture`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_modmap.get`); file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `modified.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName, k.getKeyevent, k.getKind, k.getModifier, modified.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `modified.equals`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `Modmap.M, _modmap.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_modmap.get, modified.equals`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_modmap.get`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `modified.equals`).
- **Function `apply_selection_mode`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName, k.getChar, k.getKeyevent, k.getKind, k.getSlider`).
- **Function `apply_shift`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_modmap.get`); file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `s.equals`); file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.makeStringKey, k.getChar, k.getFlags, k.getKind, k.getString, k.withChar, s.equals`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `s.equals`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `Modmap.M, _modmap.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_modmap.get, s.equals`); file `srcs/juloo.keyboard2/Utils.java` (calling: `Utils.capitalize_string`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_modmap.get`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `s.equals`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `ComposeKey.apply`).
- **Function `combine_hangul_initial`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.makeHangulMedial, kv.getFlags, kv.withFlags`).
- **Function `combine_hangul_medial`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.makeHangulFinal, kv.getFlags, kv.withFlags`).
- **Function `modify`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Modifier`).
- **Function `modify_long_press`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName, k.getEvent, k.getKind`).
- **Function `turn_into_keyevent`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Kind, k.getChar, k.getKind, k.withKeyevent`).

---

### File: `srcs/juloo.keyboard2/KeyValue.java`

- **Class `Kind`** connects **internally** to code: `KeyValue.Macro, KeyValue.Slider, _payload.toString`.
- **Function `compareTo`** connects **internally** to code: `_symbol.compareTo`.
- **Function `diacritic`** connects **internally** to code: `KeyValue, Kind.Modifier`.
- **Function `getEditing`** connects **internally** to code: `Kind.Editing`.
- **Function `getEvent`** connects **internally** to code: `Kind.Event`.
- **Function `getKeyByName`** connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `KeyValueParser.parse`); file `srcs/juloo.keyboard2/KeyValueParser.java` (calling: `KeyValueParser.ParseError`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `KeyValueParser.parse`).
- **Function `getMacro`** connects **internally** to code: `Kind.Macro`.
- **Function `getModifier`** connects **internally** to code: `Kind.Modifier`.
- **Function `getPlaceholder`** connects **internally** to code: `Kind.Placeholder`.
- **Function `getSlider`** connects **internally** to code: `Kind.Slider`.
- **Function `getSliderRepeat`** connects **internally** to code: `Kind.Slider`.
- **Function `getString`** connects **internally** to code: `_payload.toString`.
- **Function `hashCode`** connects **internally** to code: `_payload.hashCode` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `_payload.hashCode`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `_payload.hashCode`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_payload.hashCode`).
- **Function `makeHangulFinal`** connects **internally** to code: `KeyValue.makeCharKey`.
- **Function `makeHangulInitial`** connects **internally** to code: `KeyValue`.
- **Function `makeHangulMedial`** connects **internally** to code: `KeyValue`.
- **Function `makeInternalModifier`** connects **internally** to code: `KeyValue, Kind.Modifier`.
- **Function `makeMacro`** connects **internally** to code: `KeyValue, Kind.Macro, Macro`.
- **Function `makeModifiedCharKey`** connects **internally** to code: `KeyValue`.
- **Function `makeStringKey`** connects **internally** to code: `KeyValue`.
- **Function `placeholderKey`** connects **internally** to code: `KeyValue, Kind.Placeholder`.
- **Function `sameKey`** connects **internally** to code: `_payload.compareTo`.
- **Function `sliderKey`** connects **internally** to code: `KeyValue, Kind.Slider`.
- **Function `withChar`** connects **internally** to code: `KeyValue`.
- **Function `withFlags`** connects **internally** to code: `KeyValue`.
- **Function `withKeyevent`** connects **internally** to code: `KeyValue`.
- **Function `withSymbol`** connects **internally** to code: `KeyValue`.

---

### File: `srcs/juloo.keyboard2/KeyValueParser.java`

- **Class `KeyValueParser`** connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `Starting_with_colon.parse`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue, KeyValue.makeMacro, KeyValue.makeStringKey, first_key.withSymbol`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `keydefs.add`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `Starting_with_colon.parse`).
- **Function `key_by_name_or_str`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `token.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getSpecialKeyByName, KeyValue.keyeventKey, KeyValue.makeStringKey, token.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `token.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `token.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `token.equals`).
- **Function `match`** connects **internally** to code: `ParseError` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `msg_.toString`).
- **Function `remove_escaping`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `out.toString`).
- **Function `while`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.keyeventKey, KeyValue.makeCharKey, KeyValue.makeStringKey`).

---

### File: `srcs/juloo.keyboard2/Keyboard2.java`

- **Class `BroadcastReceiver`** connects **internally** to code: `BroadcastReceiver`.
- **Class `Receiver`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `KeyEventHandler.IReceiver`).
- **Class `Runnable`** connects **internally** to code: `Runnable` and connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `Runnable`); file `srcs/juloo.keyboard2/CustomLayoutEditDialog.java` (calling: `Runnable`); file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `Runnable`).
- **Function `current_layout`** connects **externally** to code in file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `LayoutModifier.modify_layout`).
- **Function `current_layout_unmodified`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_config.layouts.get`); file `srcs/juloo.keyboard2/Config.java` (calling: `_config.get_current_layout`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_config.layouts.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_config.layouts.get, _config.layouts.size`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_config.layouts.get`).
- **Function `extra_keys_of_subtype`** connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `ExtraKeys.parse`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `ExtraKeys.parse`).
- **Function `handle_event_key`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `themeCycle.get`); file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalPrefs`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Event, prefs.getString`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.setKeyboard`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `themeCycle.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `themeCycle.get, themeCycle.size`); file `srcs/juloo.keyboard2/VoiceImeSwitcher.java` (calling: `VoiceImeSwitcher.choose_voice_ime, VoiceImeSwitcher.switch_to_voice_ime`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `themeCycle.get`).
- **Function `if`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.reset`).
- **Function `incrTextLayout`** connects **externally** to code in file `srcs/juloo.keyboard2/Config.java` (calling: `_config.get_current_layout`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_config.layouts.size`).
- **Function `loadLayout`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.load`).
- **Function `loadNumpad`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.load`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `LayoutModifier.modify_numpad`).
- **Function `loadPinentry`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.load`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `LayoutModifier.modify_pinentry`).
- **Function `onCreate`** connects **internally** to code: `Receiver, super.onCreate` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardHistoryService.java` (calling: `ClipboardHistoryService.on_startup`); file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalConfig, Config.initGlobalConfig`); file `srcs/juloo.keyboard2/CustomDictionarySettingsActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/DirectBootAwarePreferences.java` (calling: `DirectBootAwarePreferences.get_shared_preferences`); file `srcs/juloo.keyboard2/ExportClipboardActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/FoldStateTracker.java` (calling: `FoldStateTracker, _foldStateTracker.isUnfolded, _foldStateTracker.setChangedCallback`); file `srcs/juloo.keyboard2/ImportClipboardActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `KeyEventHandler`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.reset`); file `srcs/juloo.keyboard2/LauncherActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `LayoutBasedAutoCorrectionProvider`); file `srcs/juloo.keyboard2/Logs.java` (calling: `Logs.set_debug_logs`); file `srcs/juloo.keyboard2/SettingsActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `SuggestionProvider`).
- **Function `onCurrentInputMethodSubtypeChanged`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.setKeyboard`).
- **Function `onDestroy`** connects **internally** to code: `super.onDestroy` and connects **externally** to code in file `srcs/juloo.keyboard2/FoldStateTracker.java` (calling: `_foldStateTracker.close`).
- **Function `onFinishInputView`** connects **internally** to code: `super.onFinishInputView`.
- **Function `onKeyDown`** connects **internally** to code: `super.onKeyDown` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `_keyeventhandler.key_up`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getSpecialKeyByName`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers`).
- **Function `onReceive`** connects **internally** to code: `_suggestionProvider.reloadCustomDictionary` and connects **externally** to code in file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `_suggestionProvider.reloadCustomDictionary`).
- **Function `onSharedPreferenceChanged`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.setKeyboard`).
- **Function `onStartInputView`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_keyeventhandler.started`); file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `_keyeventhandler.started`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.setKeyboard`); file `srcs/juloo.keyboard2/Logs.java` (calling: `Logs.debug_startup_input_view`).
- **Function `onUpdateSelection`** connects **internally** to code: `super.onUpdateSelection` and connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `_keyeventhandler.selection_updated`); file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `_keyeventhandler.selection_updated`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.set_selection_state`).
- **Function `populateSuggestions`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `tv.getText`); file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `_keyeventhandler.replaceCurrentWord`).
- **Function `refreshAccentsOption`** connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `ExtraKeys.merge`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `extra_keys.add`).
- **Function `refreshSubtypeImm`** connects **externally** to code in file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `_autoCorrectionProvider.updateLayout`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `LayoutsPreference.layout_of_string`).
- **Function `refresh_action_label`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `info.actionLabel.toString`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.setKeyboard`).
- **Function `refresh_config`** connects **externally** to code in file `srcs/juloo.keyboard2/Config.java` (calling: `_config.refresh`); file `srcs/juloo.keyboard2/FoldStateTracker.java` (calling: `_foldStateTracker.isUnfolded`).
- **Function `reloadCustomDictionary`** connects **internally** to code: `_suggestionProvider.reloadCustomDictionary` and connects **externally** to code in file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `_suggestionProvider.reloadCustomDictionary`).
- **Function `run`** connects **externally** to code in file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `editor.apply`); file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalPrefs`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `editor.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `editor.apply`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `editor.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `editor.apply`).
- **Function `selection_state_changed`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.set_selection_state`).
- **Function `setInputView`** connects **internally** to code: `super.setInputView`.
- **Function `setSpecialLayout`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.setKeyboard`).
- **Function `setTextLayout`** connects **externally** to code in file `srcs/juloo.keyboard2/Config.java` (calling: `_config.set_current_layout`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.setKeyboard`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `_autoCorrectionProvider.updateLayout`).
- **Function `setTheme`** connects **externally** to code in file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalPrefs`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.setKeyboard`).
- **Function `set_compose_pending`** connects **internally** to code: `_keyboardView.set_compose_pending` and connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.set_compose_pending`).
- **Function `set_shift_state`** connects **internally** to code: `_keyboardView.set_shift_state` and connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_keyboardView.set_shift_state`).
- **Function `updateFullscreenMode`** connects **internally** to code: `super.updateFullscreenMode`.
- **Function `updateSuggestionsFromPrefix`** connects **externally** to code in file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `_suggestionProvider.getSuggestions`).

---

### File: `srcs/juloo.keyboard2/Keyboard2View.java`

- **Class `Keyboard2View`** connects **externally** to code in file `srcs/juloo.keyboard2/Config.java` (calling: `Config.globalConfig`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.load`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers`); file `srcs/juloo.keyboard2/Theme.java` (calling: `Theme`).
- **Function `drawBorder`** connects **externally** to code in file `srcs/juloo.keyboard2/Theme.java` (calling: `Theme.Computed`).
- **Function `drawIndication`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `k.indication.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `k.indication.equals`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `k.indication.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `k.indication.equals`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key, Theme.Computed`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `k.indication.equals`).
- **Function `drawKeyFrame`** connects **externally** to code in file `srcs/juloo.keyboard2/Theme.java` (calling: `Theme.Computed`).
- **Function `drawLabel`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `kv.getString, kv.hasFlagsAny`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `kv.hasFlagsAny`); file `srcs/juloo.keyboard2/Theme.java` (calling: `Theme.Computed, tc.label_paint`).
- **Function `drawSubLabel`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Kind, kv.getKind, kv.getString, kv.hasFlagsAny`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `kv.hasFlagsAny`); file `srcs/juloo.keyboard2/Theme.java` (calling: `Theme.Computed, tc.sublabel_paint`).
- **Function `getKeyAtPosition`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key, KeyboardData.Row`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Function `getKeyPopupText`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `key.getChar, key.getKind, key.getMetaState, key.getString, sb.toString`).
- **Function `getRowAtPosition`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Row`).
- **Function `labelColor`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `k.hasFlagsAny`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_pointers.getKeyFlags, k.hasFlagsAny`).
- **Function `modifyKey`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyModifier.java` (calling: `KeyModifier.modify`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers`).
- **Function `onApplyWindowInsets`** connects **internally** to code: `Vertical`.
- **Function `onDetachedFromWindow`** connects **internally** to code: `super.onDetachedFromWindow` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardView.java` (calling: `super.onDetachedFromWindow`).
- **Function `onDraw`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key, KeyboardData.Row`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_pointers.isKeyDown`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key, Theme.Computed`).
- **Function `onMeasure`** connects **externally** to code in file `srcs/juloo.keyboard2/Theme.java` (calling: `Theme.Computed`).
- **Function `onPointerDown`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `_config.handler.key_down`).
- **Function `onPointerHold`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `_config.handler.key_up`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers`).
- **Function `onPointerUp`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `_config.handler.key_up`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers`).
- **Function `onShowPopup`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Function `onTouch`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_pointers.onTouchCancel, _pointers.onTouchDown, _pointers.onTouchMove, _pointers.onTouchUp`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Function `reset`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `Pointers.Modifiers, _pointers.clear`).
- **Function `scaleTextSize`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `k.hasFlagsAny`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `k.hasFlagsAny`).
- **Function `setKeyboard`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyModifier.java` (calling: `KeyModifier.set_modmap`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `_keyboard.findKeyWithValue`).
- **Function `set_fake_ptr_latched`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_pointers.set_fake_pointer_state`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Function `set_selection_state`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `Keybard2.onUpdateSelection`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Function `updateFlags`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyEventHandler.java` (calling: `_config.handler.mods_changed`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_pointers.getModifiers`).
- **Function `vibrate`** connects **internally** to code: `VibratorCompat.vibrate` and connects **externally** to code in file `srcs/juloo.keyboard2/VibratorCompat.java` (calling: `VibratorCompat.vibrate`).

---

### File: `srcs/juloo.keyboard2/KeyboardData.java`

- **Class `Key`** connects **internally** to code: `Key` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue, KeyValue.getKeyByName`); file `srcs/juloo.keyboard2/Theme.java` (calling: `Key`).
- **Class `PreferredPos`** connects **internally** to code: `KeyPos, PreferredPos` and connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `parser.next`).
- **Class `Row`** connects **internally** to code: `Row, row.updateWidth` and connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `Key.parse`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `keys.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `keys_.size`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `Key.parse`).
- **Function `addExtraKeys`** connects **internally** to code: `KeyboardData` and connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `unplaced_keys.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `extra_keys.hasNext, extra_keys.next`).
- **Function `addNumPad`** connects **internally** to code: `KeyboardData, KeyboardData.Key, Row` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `nps.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `extendedRows.add, keys.add, nps.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `iterNumPadRows.hasNext, iterNumPadRows.next, nps.get, nps.size`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `nps.get`).
- **Function `add_key_to_pos`** connects **internally** to code: `col.getKeyValue, col.withKeyValue` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `row.keys.get, rows.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `row.keys.get, rows.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `row.keys.get, row.keys.size, rows.get, rows.size`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `row.keys.get, rows.get`).
- **Function `apply`** connects **internally** to code: `Key, k.keyHasFlag` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue, KeyValue.getKeyByName`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `Modmap, Modmap.M, mm.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `parser.next`); file `srcs/juloo.keyboard2/Theme.java` (calling: `Key`).
- **Function `attribute_bool`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `val.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `val.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `val.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `val.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `val.equals`).
- **Function `compute_max_width`** connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `Row.parse`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `Row.parse`).
- **Function `copy`** connects **internally** to code: `Row`.
- **Function `findKeyWithValue`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `rows.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `rows.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `rows.get, rows.size`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `rows.get`).
- **Function `getKeys`** connects **internally** to code: `KeyPos`.
- **Function `get_key_at_pos`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `keys.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `keys.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `keys.get, keys.size`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `keys.get`).
- **Function `if`** connects **internally** to code: `next_to_pos.with_dir`.
- **Function `insert_row`** connects **internally** to code: `KeyboardData, row.updateWidth` and connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `rows_.add`).
- **Function `load`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `_layoutCache.get`); file `srcs/juloo.keyboard2/FoldStateTracker.java` (calling: `parser.close`); file `srcs/juloo.keyboard2/Logs.java` (calling: `Logs.exn`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_layoutCache.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_layoutCache.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_layoutCache.get`).
- **Function `load_string`** connects **internally** to code: `KeyboardData` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `numpad_script.equals, script.equals`); file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `Row.parse`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `numpad_script.equals, script.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `numpad_script.equals, script.equals`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `rows.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `numpad_script.equals, script.equals`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `Row.parse`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `numpad_script.equals, script.equals`).
- **Function `scaleWidth`** connects **internally** to code: `Key` and connects **externally** to code in file `srcs/juloo.keyboard2/Theme.java` (calling: `Key`).
- **Function `stripPrefix`** connects **internally** to code: `Key` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `parser.next`); file `srcs/juloo.keyboard2/Theme.java` (calling: `Key`).
- **Function `withKeyValue`** connects **internally** to code: `Key` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue`); file `srcs/juloo.keyboard2/Theme.java` (calling: `Key`).
- **Function `withShift`** connects **internally** to code: `Key` and connects **externally** to code in file `srcs/juloo.keyboard2/Theme.java` (calling: `Key`).
- **Function `with_dir`** connects **internally** to code: `KeyPos`.

---

### File: `srcs/juloo.keyboard2/KeyboardExecutors.java`


---

### File: `srcs/juloo.keyboard2/KeyboardLayoutAnalyzer.java`

- **Function `for`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `adjacencyMap.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `adjacencyMap.get, existingNeighbors.add, neighbors.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `adjacencyMap.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `adjacencyMap.get`).

---

### File: `srcs/juloo.keyboard2/LauncherActivity.java`

- **Class `LauncherActivity`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `Handler.Callback`); file `srcs/juloo.keyboard2/CustomLayoutEditDialog.java` (calling: `Handler.Callback`).
- **Function `onCreate`** connects **internally** to code: `Tryhere_OnUnhandledKeyEventListener, super.onCreate` and connects **externally** to code in file `srcs/juloo.keyboard2/CustomDictionarySettingsActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/ExportClipboardActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/ImportClipboardActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/SettingsActivity.java` (calling: `super.onCreate`).
- **Function `onOptionsItemSelected`** connects **internally** to code: `super.onOptionsItemSelected` and connects **externally** to code in file `srcs/juloo.keyboard2/EmojiGridView.java` (calling: `item.getItemId`).
- **Function `onStart`** connects **internally** to code: `super.onStart` and connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `_animations.add`).
- **Function `onUnhandledKeyEvent`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `s.toString`).

---

### File: `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java`

- **Class `CorrectionCandidate`** connects **externally** to code in file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `SuggestionProvider.WordSource`).
- **Function `equals`** connects **internally** to code: `word.equals` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `word.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `word.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `word.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `word.equals`).
- **Function `getCorrections`** connects **externally** to code in file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `suggestionProvider.isValidWord`).
- **Function `getDeletionCandidates`** connects **internally** to code: `CorrectionCandidate` and connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `candidates.add`); file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `suggestionProvider.getWordSource, suggestionProvider.isValidWord`).
- **Function `getDoublingSinglingCandidates`** connects **internally** to code: `CorrectionCandidate` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `reducedBuilder.toString`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `candidates.add`); file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `suggestionProvider.getWordSource, suggestionProvider.isValidWord`).
- **Function `getReversalCandidates`** connects **internally** to code: `CorrectionCandidate` and connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `candidates.add`); file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `suggestionProvider.getWordSource, suggestionProvider.isValidWord`).
- **Function `getSimilarWords`** connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `result.add`).
- **Function `getSubstitutionCandidates`** connects **internally** to code: `CorrectionCandidate` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `adjacencyMap.get`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `builder.toString`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `adjacencyMap.get, candidates.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `adjacencyMap.get`); file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `suggestionProvider.getWordSource, suggestionProvider.isValidWord`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `adjacencyMap.get`).
- **Function `getTranspositionCandidates`** connects **internally** to code: `CorrectionCandidate` and connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `candidates.add`); file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `suggestionProvider.getWordSource, suggestionProvider.isValidWord`).
- **Function `hashCode`** connects **internally** to code: `word.hashCode` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `word.hashCode`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `word.hashCode`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `word.hashCode`).
- **Function `if`** connects **internally** to code: `CorrectionCandidate` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `adjacencyMap.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `adjacencyMap.get, candidates.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `adjacencyMap.get`); file `srcs/juloo.keyboard2/SuggestionProvider.java` (calling: `suggestionProvider.getWordSource, suggestionProvider.isValidWord`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `adjacencyMap.get`).
- **Function `updateLayout`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardLayoutAnalyzer.java` (calling: `KeyboardLayoutAnalyzer.getAdjacencyMap`).

---

### File: `srcs/juloo.keyboard2/LayoutModifier.java`

- **Class `LayoutModifier`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Row`).
- **Function `apply`** connects **internally** to code: `ComposeKey.apply` and connects **externally** to code in file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `ComposeKey.apply, KeyboardData.MapKeyValues`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `ComposeKey.apply`).
- **Function `modify_key`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName, KeyValue.makeActionKey, orig.getEvent, orig.getKeyevent, orig.getKind`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `globalConfig.layouts.size`).
- **Function `modify_layout`** connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `ExtraKeys.Query, globalConfig.extra_keys_subtype.compute`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.PreferredPos, KeyboardData.Row, added_number_row.getKeys, added_numpad.getKeys, kw.getKeys, kw.insert_row`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `kw.rows.size`).
- **Function `modify_number_row`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.MapKeyValues, KeyboardData.Row, row.mapKeys`).
- **Function `modify_numpad`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyModifier.java` (calling: `KeyModifier.modify_numpad_script`).
- **Function `modify_pinentry`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.MapKeyValues, kw.mapKeys`).
- **Function `numpad_script_map`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyModifier.java` (calling: `KeyModifier.modify_numpad_script`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.MapKeyValues`).

---

### File: `srcs/juloo.keyboard2/Logs.java`

- **Function `debug_startup_input_view`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `info.extras.toString`).
- **Function `exn`** **reads configuration** from file `srcs/compose/accent_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_caron.json` (reading: `Log.e`); file `srcs/compose/accent_cedille.json` (reading: `Log.e`); file `srcs/compose/accent_circonflexe.json` (reading: `Log.e`); file `srcs/compose/accent_dot_above.json` (reading: `Log.e`); file `srcs/compose/accent_dot_below.json` (reading: `Log.e`); file `srcs/compose/accent_double_aigu.json` (reading: `Log.e`); file `srcs/compose/accent_double_grave.json` (reading: `Log.e`); file `srcs/compose/accent_grave.json` (reading: `Log.e`); file `srcs/compose/accent_hook_above.json` (reading: `Log.e`); file `srcs/compose/accent_macron.json` (reading: `Log.e`); file `srcs/compose/accent_ogonek.json` (reading: `Log.e`); file `srcs/compose/accent_slash.json` (reading: `Log.e`); file `srcs/compose/accent_subscript.json` (reading: `Log.e`); file `srcs/compose/accent_superscript.json` (reading: `Log.e`); file `srcs/compose/accent_tilde.json` (reading: `Log.e`); file `srcs/compose/accent_trema.json` (reading: `Log.e`); file `srcs/compose/compose/extra.json` (reading: `Log.e`); file `srcs/compose/fn.json` (reading: `Log.e`).

---

### File: `srcs/juloo.keyboard2/Modmap.java`

- **Function `get`** connects **internally** to code: `mm.get` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `mm.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `mm.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `mm.get`).

---

### File: `srcs/juloo.keyboard2/Pointers.java`

- **Class `IPointerEventHandler`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Class `Pointer`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Class `Sliding`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Slider`).
- **Function `add_fake_pointer`** connects **internally** to code: `Pointer` and connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.onPointerFlagsChanged`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_ptrs.add`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Function `advance`** connects **internally** to code: `m1.size` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `m.compareTo`).
- **Function `apply_gesture`** connects **externally** to code in file `srcs/juloo.keyboard2/Gesture.java` (calling: `Gesture.Name, ptr.gesture.current_direction`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Kind, KeyValue.Modifier, KeyValue.makeStringKey, centralKey.getKind, centralKey.getString`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.onShowPopup`).
- **Function `clear`** connects **internally** to code: `_ptrs.clear`.
- **Function `clearLatched`** connects **internally** to code: `_ptrs.get, _ptrs.size, ptr.hasFlagsAny` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `_ptrs.get`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `ptr.hasFlagsAny`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_ptrs.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_ptrs.get`).
- **Function `diff`** connects **internally** to code: `ModifiersDiffIterator`.
- **Function `equals`** connects **internally** to code: `Arrays.equals` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `Arrays.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `Arrays.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `Arrays.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `Arrays.equals`).
- **Function `getKeyAtDirection`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Function `getKeyFlags`** connects **internally** to code: `p.value.equals` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `p.value.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `p.value.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `p.value.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `p.value.equals`).
- **Function `getLatched`** connects **internally** to code: `p.hasFlagsAny, p.value.equals` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `p.value.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `p.hasFlagsAny, p.value.equals`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `p.value.equals`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `p.value.equals`).
- **Function `getModifiers`** connects **internally** to code: `Modifiers.ofArray, _ptrs.get, _ptrs.size, p.hasFlagsAny` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `_ptrs.get`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue, p.hasFlagsAny`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_ptrs.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `_ptrs.get`).
- **Function `getNearestKeyAtDirection`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Kind, k.getKind`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.modifyKey`).
- **Function `handleLongPress`** connects **internally** to code: `kv.equals, kv.hasFlagsAny, ptr.hasFlagsAny` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `kv.equals`); file `srcs/juloo.keyboard2/KeyModifier.java` (calling: `KeyModifier.modify_long_press`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `kv.equals, kv.hasFlagsAny, ptr.hasFlagsAny`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.onPointerDown, _handler.onShowPopup`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `kv.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `kv.equals`).
- **Function `has`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Modifier, kv.getKind, kv.getModifier`).
- **Function `hashCode`** connects **internally** to code: `Arrays.hashCode` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `Arrays.hashCode`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `Arrays.hashCode`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `Arrays.hashCode`).
- **Function `if`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Modifier, KeyValue.makeModifiedCharKey`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.onShowPopup`).
- **Function `isKeyDown`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Function `isOtherPointerDown`** connects **internally** to code: `p.hasFlagsAny, p.value.hasFlagsAny` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `p.hasFlagsAny, p.value.hasFlagsAny`).
- **Function `isSliding`** connects **internally** to code: `ptr.hasFlagsAny` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `ptr.hasFlagsAny`).
- **Function `lockPointer`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.onPointerFlagsChanged`).
- **Function `make_pointer`** connects **internally** to code: `Pointer` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Function `modify_key_with_extra_modifier`** connects **internally** to code: `ptr.modifiers.with_extra_mod` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Modifier, KeyValue.makeInternalModifier`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.modifyKey`).
- **Function `ofArray`** connects **internally** to code: `Modifiers` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue`).
- **Function `onTouchCancel`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.onPointerFlagsChanged`).
- **Function `onTouchDown`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.modifyKey, _handler.onPointerDown, _handler.onShowPopup`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `_ptrs.add`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Function `onTouchMove`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.sliderKey`); file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.onPointerHold`).
- **Function `onTouchUp`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.onPointerFlagsChanged`).
- **Function `pointer_flags_of_kv`** connects **internally** to code: `kv.hasFlagsAny` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `kv.hasFlagsAny`).
- **Function `set_fake_pointer_state`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2View.java` (calling: `_handler.onPointerFlagsChanged`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.Key`); file `srcs/juloo.keyboard2/Theme.java` (calling: `KeyboardData.Key`).
- **Function `startSliding`** connects **internally** to code: `Sliding` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `kv.getSlider, kv.getSliderRepeat`).

---

### File: `srcs/juloo.keyboard2/SettingsActivity.java`

- **Function `onCreate`** connects **internally** to code: `super.onCreate` and connects **externally** to code in file `srcs/juloo.keyboard2/Config.java` (calling: `Config.migrate`); file `srcs/juloo.keyboard2/CustomDictionarySettingsActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/ExportClipboardActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/FoldStateTracker.java` (calling: `FoldStateTracker.isFoldableDevice`); file `srcs/juloo.keyboard2/ImportClipboardActivity.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `super.onCreate`); file `srcs/juloo.keyboard2/LauncherActivity.java` (calling: `super.onCreate`).
- **Function `onStop`** connects **internally** to code: `super.onStop`.

---

### File: `srcs/juloo.keyboard2/SuggestionProvider.java`

- **Class `SuggestionProvider`** connects **internally** to code: `TrieNode`.
- **Function `findAllWords`** connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `suggestions.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `suggestions.size`).
- **Function `findPrefixNode`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `current.children.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `current.children.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `current.children.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `current.children.get`).
- **Function `getSuggestions`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `suggestions.size`).
- **Function `insert`** connects **internally** to code: `TrieNode`.
- **Function `reloadCustomDictionary`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `customRoot.children.clear`).

---

### File: `srcs/juloo.keyboard2/Theme.java`

- **Class `Computed`** connects **internally** to code: `Key` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `Key`).

---

### File: `srcs/juloo.keyboard2/Utils.java`

- **Function `capitalize_string`** connects **externally** to code in file `srcs/juloo.keyboard2/CustomLayoutEditDialog.java` (calling: `dialog.show`).
- **Function `show_dialog_on_ime`** connects **externally** to code in file `srcs/juloo.keyboard2/CustomLayoutEditDialog.java` (calling: `dialog.show`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `out.toString`).

---

### File: `srcs/juloo.keyboard2/VibratorCompat.java`

- **Function `get_vibrator`** connects **externally** to code in file `srcs/juloo.keyboard2/Keyboard2.java` (calling: `v.getContext`).

---

### File: `srcs/juloo.keyboard2/VoiceImeSwitcher.java`

- **Function `get_display_name`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `subtype_name.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `subtype_name.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `subtype_name.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `subtype_name.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `subtype_name.equals`).
- **Function `get_ime_by_id`** connects **internally** to code: `ime.get_id`.
- **Function `get_ime_display_names`** connects **internally** to code: `ime.get_display_name` and connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `names.add`).
- **Function `get_voice_ime_list`** connects **internally** to code: `IME` and connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `imes.add`).
- **Function `onClick`** connects **internally** to code: `selected.get_id` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `imes.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `imes.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `ime_display_names.size, imes.get`); file `srcs/juloo.keyboard2/Utils.java` (calling: `Utils.show_dialog_on_ime`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `imes.get`).
- **Function `serialize_ime_ids`** connects **internally** to code: `ime.get_id` and connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `b.toString`).
- **Function `switch_input_method`** connects **internally** to code: `ime.get_id`.
- **Function `switch_to_voice_ime`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `last_known_imes.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `last_known_imes.equals, prefs.getString`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `last_known_imes.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `imes.size, last_known_imes.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `last_known_imes.equals`).

---

### File: `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java`

- **Class `CustomExtraKeysPreference`** connects **externally** to code in file `srcs/juloo.keyboard2/prefs/ListGroupPreference.java` (calling: `ListGroupPreference.StringSerializer`).
- **Function `get`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.PreferredPos`).
- **Function `onClick`** connects **internally** to code: `callback.select` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `input.getText, k.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `k.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `k.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `k.equals`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `callback.select`); file `srcs/juloo.keyboard2/prefs/ListGroupPreference.java` (calling: `callback.select`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `k.equals`).

---

### File: `srcs/juloo.keyboard2/prefs/ExtraKeysPreference.java`

- **Class `ExtraKeyCheckBoxPreference`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName`).
- **Function `format_key_combination`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName, out.toString`).
- **Function `format_key_combination_gesture`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName, res.getString`).
- **Function `get_extra_keys`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.PreferredPos`).
- **Function `if`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.KeyPos, KeyboardData.PreferredPos`).
- **Function `key_description`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `res.getString`).
- **Function `key_preferred_pos`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.PreferredPos`).
- **Function `key_title`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `kv.getString`).
- **Function `mk_preferred_pos`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.PreferredPos`).
- **Function `onAttachedToActivity`** connects **internally** to code: `ExtraKeyCheckBoxPreference`.
- **Function `onBindView`** connects **internally** to code: `super.onBindView` and connects **externally** to code in file `srcs/juloo.keyboard2/Theme.java` (calling: `Theme.getKeyFont`).

---

### File: `srcs/juloo.keyboard2/prefs/IntSlideBarPreference.java`


---

### File: `srcs/juloo.keyboard2/prefs/LayoutsPreference.java`

- **Class `Serializer`** connects **internally** to code: `CustomLayout.parse, ListGroupPreference.Serializer, NamedLayout, SystemLayout` and connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `name.equals`); file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `CustomLayout.parse`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `name.equals, obj_.getString`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `name.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `name.equals`); file `srcs/juloo.keyboard2/prefs/ListGroupPreference.java` (calling: `ListGroupPreference.Serializer`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `name.equals`).
- **Function `if`** connects **externally** to code in file `srcs/juloo.keyboard2/ClipboardItem.java` (calling: `cl.parsed.name.equals`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `cl.parsed.name.equals`); file `srcs/juloo.keyboard2/LayoutBasedAutoCorrectionProvider.java` (calling: `cl.parsed.name.equals`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `cl.parsed.name.equals`); file `test/juloo.keyboard2/KeyValueTest.java` (calling: `cl.parsed.name.equals`).
- **Function `layout_of_string`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `KeyboardData.load`).
- **Function `load_from_preferences`** connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `layouts.add`).
- **Function `onClick`** connects **internally** to code: `NamedLayout, SystemLayout, callback.select` and connects **externally** to code in file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `callback.select`); file `srcs/juloo.keyboard2/prefs/ListGroupPreference.java` (calling: `callback.select`).
- **Function `onSetInitialValue`** connects **internally** to code: `super.onSetInitialValue` and connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `_values.size`); file `srcs/juloo.keyboard2/prefs/IntSlideBarPreference.java` (calling: `super.onSetInitialValue`); file `srcs/juloo.keyboard2/prefs/ListGroupPreference.java` (calling: `super.onSetInitialValue`); file `srcs/juloo.keyboard2/prefs/SlideBarPreference.java` (calling: `super.onSetInitialValue`).
- **Function `on_attach_add_button`** connects **internally** to code: `LayoutsAddButton`.
- **Function `parse`** connects **internally** to code: `CustomLayout`.
- **Function `select_custom`** connects **externally** to code in file `srcs/juloo.keyboard2/Autocapitalisation.java` (calling: `CustomLayoutEditDialog.Callback`); file `srcs/juloo.keyboard2/CustomLayoutEditDialog.java` (calling: `CustomLayoutEditDialog.Callback, CustomLayoutEditDialog.show`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `_values.size`); file `srcs/juloo.keyboard2/prefs/ListGroupPreference.java` (calling: `callback.allow_remove`).
- **Function `select_dialog`** connects **internally** to code: `ListGroupPreference.Serializer` and connects **externally** to code in file `srcs/juloo.keyboard2/prefs/ListGroupPreference.java` (calling: `ListGroupPreference.Serializer`).
- **Function `should_allow_remove_item`** connects **externally** to code in file `srcs/juloo.keyboard2/Pointers.java` (calling: `_values.size`).

---

### File: `srcs/juloo.keyboard2/prefs/ListGroupPreference.java`

- **Function `add_item`** connects **externally** to code in file `srcs/juloo.keyboard2/Modmap.java` (calling: `_values.add`).
- **Function `load_from_preferences`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `prefs.getString`).
- **Function `load_from_string`** connects **internally** to code: `serializer.load_item` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `arr.get`); file `srcs/juloo.keyboard2/Logs.java` (calling: `Logs.exn`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `arr.get, l.add`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `arr.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `arr.get`).
- **Function `onAttachedToActivity`** connects **internally** to code: `super.onAttachedToActivity` and connects **externally** to code in file `srcs/juloo.keyboard2/prefs/ExtraKeysPreference.java` (calling: `super.onAttachedToActivity`).
- **Function `onCreateView`** connects **internally** to code: `super.onCreateView`.
- **Function `on_attach_add_button`** connects **internally** to code: `AddButton`.
- **Function `reattach`** connects **internally** to code: `Item`.
- **Function `save_to_string`** connects **internally** to code: `serializer.save_item` and connects **externally** to code in file `srcs/juloo.keyboard2/Logs.java` (calling: `Logs.exn`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `serialized_items.add`).
- **Function `select`** connects **internally** to code: `SelectionCallback`.

---

### File: `srcs/juloo.keyboard2/prefs/SlideBarPreference.java`


---

### File: `srcs/res/SvgToVector.java`


---

### File: `sync_translations.py`

- **Function `key`** connects **externally** to code in file `srcs/compose/compile.py` (calling: `ent.get`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `ent.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `ent.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `ent.get`).
- **Function `parse_strings_file`** connects **internally** to code: `key` and connects **externally** to code in file `srcs/compose/compile.py` (calling: `ent.get`); file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `ET.parse`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `ent.get`); file `srcs/juloo.keyboard2/Pointers.java` (calling: `ent.get`); file `srcs/juloo.keyboard2/prefs/CustomExtraKeysPreference.java` (calling: `ent.get`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `ET.parse`).
- **Function `sync_metadata`** connects **internally** to code: `sync_meta_file`.

---

### File: `test/juloo.keyboard2/ComposeKeyTest.java`

- **Class `ComposeKeyTest`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName, KeyValue.makeStringKey`).
- **Function `apply`** connects **internally** to code: `ComposeKey.apply` and connects **externally** to code in file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `ComposeKey.apply`).

---

### File: `test/juloo.keyboard2/KeyValueParserTest.java`

- **Class `KeyValueParserTest`** connects **internally** to code: `Utils.expect_error` and connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `Utils.parse`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue, KeyValue.getSpecialKeyByName, KeyValue.keyeventKey, KeyValue.makeCharKey, KeyValue.makeMacro, KeyValue.makeStringKey`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `Utils.parse`).
- **Class `Utils`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.getKeyByName`).
- **Function `expect_error`** connects **externally** to code in file `srcs/juloo.keyboard2/ExtraKeys.java` (calling: `KeyValueParser.parse`); file `srcs/juloo.keyboard2/KeyValueParser.java` (calling: `KeyValueParser.ParseError`); file `srcs/juloo.keyboard2/prefs/LayoutsPreference.java` (calling: `KeyValueParser.parse`).

---

### File: `test/juloo.keyboard2/KeyValueTest.java`

- **Function `apply_numpad_script`** connects **externally** to code in file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/KeyModifier.java` (calling: `KeyModifier.modify_numpad_script`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `b.toString`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `ComposeKey.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `ComposeKey.apply`); file `test/juloo.keyboard2/ModmapTest.java` (calling: `ComposeKey.apply`).
- **Function `equals`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue, KeyValue.getSpecialKeyByName, KeyValue.keyeventKey, KeyValue.makeMacro, KeyValue.makeStringKey`).

---

### File: `test/juloo.keyboard2/ModmapTest.java`

- **Function `apply`** connects **externally** to code in file `srcs/juloo.keyboard2/KeyModifier.java` (calling: `KeyModifier.modify, KeyModifier.set_modmap`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Modifier, KeyValue.getKeyByName`).
- **Function `keyevent_mappings`** connects **internally** to code: `Utils.apply` and connects **externally** to code in file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `Utils.apply`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Modifier, KeyValue.getKeyByName`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `Utils.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `Utils.apply`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `Modmap, Modmap.M, mm.add`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `Utils.apply`).
- **Function `test`** connects **internally** to code: `Utils.apply` and connects **externally** to code in file `srcs/juloo.keyboard2/ComposeKey.java` (calling: `Utils.apply`); file `srcs/juloo.keyboard2/KeyValue.java` (calling: `KeyValue.Modifier, KeyValue.getKeyByName`); file `srcs/juloo.keyboard2/KeyboardData.java` (calling: `Utils.apply`); file `srcs/juloo.keyboard2/LayoutModifier.java` (calling: `Utils.apply`); file `srcs/juloo.keyboard2/Modmap.java` (calling: `Modmap, Modmap.M, mm.add`); file `test/juloo.keyboard2/ComposeKeyTest.java` (calling: `Utils.apply`).

---

