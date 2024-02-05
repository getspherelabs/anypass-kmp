package io.spherelabs.crypto.tinypass.database

data class AutoTypeData(
    val enabled: Boolean,
    val obfuscation: AutoTypeObfuscation = AutoTypeObfuscation.None,
    val defaultSequence: String? = null,
    val items: List<AutoTypeItem> = listOf()
)


data class AutoTypeItem(
    val window: String,
    val keystrokeSequence: String
)

enum class AutoTypeObfuscation {
    None,
    UseClipboard
}
