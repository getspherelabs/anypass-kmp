package io.spherelabs.crypto.tinypass.database.xml

import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.tinypass.database.*
import io.spherelabs.crypto.tinypass.database.FormatXml
import io.spherelabs.crypto.tinypass.database.common.selectAsBoolean
import io.spherelabs.crypto.tinypass.database.common.selectAsInt
import io.spherelabs.crypto.tinypass.database.common.selectAsString

//internal fun Element.deserializeToAutoType(): AutoTypeData {
//    return AutoTypeData(
//        enabled = this
//            .selectAsBoolean(FormatXml.Tags.Entry.AutoType.Enabled),
//        obfuscation = this
//            .selectAsInt(FormatXml.Tags.Entry.AutoType.Obfuscation)
//            .let(AutoTypeObfuscation.values()::getOrNull) ?: AutoTypeObfuscation.None,
//        defaultSequence = this
//            .selectAsString(FormatXml.Tags.Entry.AutoType.DefaultSequence),
//        items = deserializeXmlToAutoTypeItems(this),
//    )
//}
//
//private fun deserializeXmlToAutoTypeItems(node: Element): List<AutoTypeItem> {
//    val elementByTag = node.getElementsByTag(FormatXml.Tags.Entry.AutoType.Association)
//
//    return elementByTag.map { currentElement ->
//        val window = currentElement.selectAsString(FormatXml.Tags.Entry.AutoType.Window)
//        val sequence = currentElement.selectAsString(
//            FormatXml.Tags.Entry.AutoType.KeystrokeSequence,
//        )
//        AutoTypeItem(window, sequence)
//    }
//}
//
//internal fun AutoTypeData.serialize(): Element {
//    return Element(FormatXml.Tags.Entry.AutoType.TagName).apply {
//        appendElement(FormatXml.Tags.Entry.AutoType.Enabled).text(enabled.toXmlString())
//        appendElement(FormatXml.Tags.Entry.AutoType.Obfuscation).text(obfuscation.ordinal.toString())
//        appendElement(FormatXml.Tags.Entry.AutoType.DefaultSequence).text(defaultSequence ?: "")
//
//        items.forEach { item ->
//            appendElement(FormatXml.Tags.Entry.AutoType.Association).apply {
//                appendElement(FormatXml.Tags.Entry.AutoType.Window)
//                    .appendText(
//                        item.window,
//                    )
//                appendElement(FormatXml.Tags.Entry.AutoType.KeystrokeSequence)
//                    .appendText(
//                        item.keystrokeSequence,
//                    )
//            }
//        }
//    }
//}
//
//internal fun Boolean.toXmlString() = if (this) {
//    FormatXml.Values.True
//} else {
//    FormatXml.Values.False
//}

