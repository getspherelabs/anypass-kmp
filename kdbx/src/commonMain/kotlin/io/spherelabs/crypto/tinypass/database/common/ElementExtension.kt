package io.spherelabs.crypto.tinypass.database.common

import com.benasher44.uuid.Uuid
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.select.Elements
import io.spherelabs.crypto.tinypass.database.xml.XmlOption
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlinx.datetime.Instant
import okio.ByteString
import okio.ByteString.Companion.decodeBase64

internal fun Element.readInt(tagName: String): Int {
    return this.select(tagName).text().toInt()
}

internal fun Element.readBoolean(tagName: String): Boolean {
    return this.select(tagName).text().toBoolean()
}

internal fun Element.readUuid(tagName: String): Uuid? {
    return select(tagName).text().decodeBase64()?.toUuid()
}

internal fun Element.readInstant(tagName: String): Instant? {
    return selectFirst(tagName)?.readInstant()
}

internal fun Element.readString(tagName: String): String {
    return this.select(tagName).text()
}

internal fun Element.readByteString(tagName: String): ByteString? {
    return select(tagName).text().decodeBase64()
}

internal fun Element.readFirst(tagName: String): Element? {
    return select(tagName).first()
}
internal fun Element.writeUuid(uuid: Uuid?) {
    text(uuid?.toBase64() ?: "")
}

internal fun Element.writeInstant(instant: Instant?, option: XmlOption) {
    if (instant != null) {
        text(instant.deserialize(option))
    } else {
        text("")
    }
}

internal fun Element.writeBytes(bytes: ByteArray) {
    text(bytes.decodeToString())
}

@OptIn(ExperimentalEncodingApi::class)
internal fun Instant.deserialize(context: XmlOption): String {
    val binary = context.kdbxVersion.major >= 4 && !context.isExportable

    return if (binary) {
        val data = Base64.encode((epochSeconds + 62135596800).toByteArray())
        println("Binary is $data")
        data
    } else {
        this.toString()
    }
}

@OptIn(ExperimentalEncodingApi::class)
internal fun Elements.readInstant(): Instant? = text().takeIf { it.isNotEmpty() }?.let { text ->

    // Check if ISO text or binary timestamp
    if (text.indexOf(':') > 0) {
        Instant.parse(text)
    } else {
        val seconds = Long.fromByteArray(Base64.decode(text))
        Instant.fromEpochSeconds(seconds - 62135596800)
    }
}


@OptIn(ExperimentalEncodingApi::class)
internal fun Element.readInstant(): Instant? = text().takeIf { it.isNotEmpty() }?.let { text ->
    if (text.indexOf(':') > 0) {
        Instant.parse(text)
    } else {
        val seconds = Long.fromByteArray(Base64.decode(text))
        Instant.fromEpochSeconds(seconds - 62135596800)
    }
}

internal fun Long.toByteArray(): ByteArray {
    return byteArrayOf(
        this.toByte(),
        (this shr 8).toByte(),
        (this shr 16).toByte(),
        (this shr 24).toByte(),
        (this shr 32).toByte(),
        (this shr 40).toByte(),
        (this shr 48).toByte(),
        (this shr 56).toByte(),
    )
}


internal fun Long.Companion.fromByteArray(bytes: ByteArray): Long {
    return (
        bytes[7].toLong() shl 56
            or (bytes[6].toLong() and 0xff shl 48)
            or (bytes[5].toLong() and 0xff shl 40)
            or (bytes[4].toLong() and 0xff shl 32)
            or (bytes[3].toLong() and 0xff shl 24)
            or (bytes[2].toLong() and 0xff shl 16)
            or (bytes[1].toLong() and 0xff shl 8)
            or (bytes[0].toLong() and 0xff)
        )
}

