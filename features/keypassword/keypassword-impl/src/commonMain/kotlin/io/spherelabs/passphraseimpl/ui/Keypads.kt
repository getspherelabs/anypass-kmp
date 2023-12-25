package io.spherelabs.passphraseimpl.ui

import io.spherelabs.designsystem.button.KeypadType

typealias Keypad = Pair<KeypadType, String>

val keypads: Array<List<Keypad>> =
    arrayOf(
        listOf(KeypadType.Number to "1", KeypadType.Number to "2", KeypadType.Number to "3"),
        listOf(KeypadType.Number to "4", KeypadType.Number to "5", KeypadType.Number to "6"),
        listOf(KeypadType.Number to "7", KeypadType.Number to "8", KeypadType.Number to "9"),
        listOf(
            KeypadType.FingerPrint to "FingerPrint",
            KeypadType.Number to
                "0",
            KeypadType.Clear to "Clear",
        ),
    )
