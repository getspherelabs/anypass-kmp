package io.spherelabs.crypto.tinypass.database.model.autotype

import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.tinypass.database.FormatXml
import io.spherelabs.crypto.tinypass.database.xml.toXmlString

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
                appendElement(FormatXml.Tags.Entry.AutoType.Association).apply {
                    item.serialize(this)
                }
            }
        }
    }

    companion object {
        fun deserialize(): AutoType {

        }

        const val TAG_NAME = "AutoType"
        const val ENABLED = "Enabled"
        const val OBFUSCATION = "DataTransferObfuscation"
        const val SEQUENCE = "DefaultSequence"
    }
}

