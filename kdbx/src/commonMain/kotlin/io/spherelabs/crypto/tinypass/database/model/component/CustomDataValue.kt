package io.spherelabs.crypto.tinypass.database.model.component

import com.fleeksoft.ksoup.nodes.Element
import kotlinx.datetime.Instant


/**
 * Arbitrary string data holder for database/group/entry metadata.
 */
data class CustomDataValue(
    val value: String,
    val lastModified: Instant? = null,
) {


    companion object {
        private const val TagName = "CustomData"
        private const val Item = "Item"
        private const val ItemKey = "Key"
        private const val ItemValue = "Value"

        fun serialize(
            data: Map<String, CustomDataValue>
        ): Element {
            return Element(TagName).apply {
                for ((key, item) in data) {
                    appendElement(Item).apply {
                        appendElement(ItemKey).text(key)
                        appendElement(ItemValue).text(item.value)
                    }
                }
            }
        }

        fun deserialize(document: Element): Map<String, CustomDataValue> {
            val tag = document.getElementsByTag(Item)

            return tag.associate { element ->
                val key = element.select(ItemKey).text()
                val value = element.select(ItemValue).text()

                key to CustomDataValue(value)
            }
        }
    }
}

