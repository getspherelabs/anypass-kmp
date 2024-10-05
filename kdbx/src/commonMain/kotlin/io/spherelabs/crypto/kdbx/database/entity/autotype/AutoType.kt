package io.spherelabs.crypto.kdbx.database.entity.autotype

import io.spherelabs.crypto.kdbx.database.entity.Obfuscation


data class AutoType(
    val enabled: Boolean,
    val obfuscation: Obfuscation = Obfuscation.None,
    val defaultSequence: String? = null,
    val items: List<AutoTypeItem> = listOf(),
)
