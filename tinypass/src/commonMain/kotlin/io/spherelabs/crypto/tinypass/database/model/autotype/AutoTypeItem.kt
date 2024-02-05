package io.spherelabs.crypto.tinypass.database.model.autotype

import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.tinypass.database.FormatXml
import io.spherelabs.crypto.tinypass.database.common.selectAsString

data class AutoTypeItem(
    val window: String,
    val keystrokeSequence: String,
) {
    fun serialize(element: Element) = with(element) {
        appendElement(Window)
            .appendText(window)
        appendElement(KeystrokeSequence)
            .appendText(keystrokeSequence)
    }

    companion object {
        fun deserialize(element: Element): List<AutoTypeItem> = with(element) {
            val elementByTag = getElementsByTag(FormatXml.Tags.Entry.AutoType.Association)

            return elementByTag.map { currentElement ->
                val window = currentElement.selectAsString(Window)
                val sequence = currentElement.selectAsString(KeystrokeSequence)
                AutoTypeItem(window, sequence)
            }
        }

        const val Window = "Window"
        const val KeystrokeSequence = "KeystrokeSequence"
    }
}
