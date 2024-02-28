package io.spherelabs.crypto.tinypass.database.model.autotype

import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.tinypass.database.common.selectAsString

data class AutoTypeItem(
    val window: String,
    val keystrokeSequence: String,
) {
    fun serialize(element: Element) = with(element) {
        appendElement(ASSOCIATION).apply {
            appendElement(Window)
                .appendText(window)
            appendElement(KEYSTORE_SEQUENCE)
                .appendText(keystrokeSequence)
        }
    }

    companion object {
        fun deserialize(element: Element): List<AutoTypeItem> = with(element) {
            val elementByTag = getElementsByTag(ASSOCIATION)

            return elementByTag.map { currentElement ->
                val window = currentElement.selectAsString(Window)
                val sequence = currentElement.selectAsString(KEYSTORE_SEQUENCE)
                AutoTypeItem(window, sequence)
            }
        }

        const val Window = "Window"
        const val KEYSTORE_SEQUENCE = "KeystrokeSequence"
        const val ASSOCIATION = "Association"
    }
}
