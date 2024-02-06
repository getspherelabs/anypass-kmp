package io.spherelabs.crypto.tinypass.database.model.autotype

import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.tinypass.database.FormatXml
import io.spherelabs.crypto.tinypass.database.common.selectAsBoolean
import io.spherelabs.crypto.tinypass.database.common.selectAsInt
import io.spherelabs.crypto.tinypass.database.common.selectAsString

data class AutoType(
    val enabled: Boolean,
    val obfuscation: Obfuscation = Obfuscation.None,
    val defaultSequence: String? = null,
    val items: List<AutoTypeItem> = listOf(),
) {
    fun serialize(): Element {
        return Element(TAG_NAME).apply {
            appendElement(ENABLED).text(enabled.toXmlString())
            appendElement(OBFUSCATION).text(obfuscation.ordinal.toString())
            appendElement(SEQUENCE).text(defaultSequence ?: "")

            items.forEach { item ->
                item.serialize(this)
            }
        }
    }

    companion object {
        fun deserialize(element: Element): AutoType = with(element) {
            return AutoType(
                enabled = selectAsBoolean(ENABLED),
                obfuscation = selectAsInt(OBFUSCATION)
                    .let(Obfuscation.values()::getOrNull) ?: Obfuscation.None,
                defaultSequence = selectAsString(SEQUENCE),
                items = AutoTypeItem.deserialize(element),
            )
        }

        const val TAG_NAME = "AutoType"
        const val ENABLED = "Enabled"
        const val OBFUSCATION = "DataTransferObfuscation"
        const val SEQUENCE = "DefaultSequence"
    }
}

internal fun Boolean.toXmlString() = if (this) {
    FormatXml.Values.True
} else {
    FormatXml.Values.False
}
