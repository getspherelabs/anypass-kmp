package io.spherelabs.crypto.tinypass.database.xml.internal

import com.benasher44.uuid.Uuid
import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.tinypass.database.common.writeBytes
import io.spherelabs.crypto.tinypass.database.common.writeUuid
import io.spherelabs.crypto.tinypass.database.model.autotype.toXmlString
import io.spherelabs.crypto.tinypass.database.xml.XmlOption


internal fun element(tagName: String, builder: Element.() -> Unit): Element =
    Element(tagName).apply(builder)

internal inline fun Element.writeElement(
    tagName: String,
    value: String,
    option: XmlOption,
) {
    appendElement(tagName).text(value)
}

internal inline fun Element.writeElement(
    tagName: String,
    value: String,
) {
    appendElement(tagName).text(value)
}

internal inline fun Element.writeElement(
    tagName: String,
    raw: ByteArray,
) {
    appendElement(tagName).writeBytes(raw)
}

internal inline fun Element.writeElement(
    tagName: String,
    raw: Boolean,
) {
    appendElement(tagName).text(raw.toXmlString())
}

internal inline fun Element.writeElement(
    tagName: String,
    uuid: Uuid,
) {
    appendElement(tagName).writeUuid(uuid)
}

internal inline fun Element.writeIfElement(
    predicate: Boolean,
    tagName: String,
    raw: String,
) {
    if (predicate) appendElement(tagName).text(raw)
}
