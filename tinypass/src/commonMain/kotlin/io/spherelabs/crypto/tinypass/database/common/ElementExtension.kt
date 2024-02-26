package io.spherelabs.crypto.tinypass.database.common

import com.benasher44.uuid.Uuid
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.select.Elements
import io.spherelabs.crypto.tinypass.database.xml.XmlOption
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlinx.datetime.Instant
import okio.ByteString.Companion.decodeBase64

internal fun Element.selectAsInt(query: String): Int {
    return this.select(query).text().toInt()
}

internal fun Element.selectAsBoolean(query: String): Boolean {
    return this.select(query).text().toBoolean()
}

internal fun Element.selectAsUuid(query: String): Uuid? {
    return select(query).text().decodeBase64()?.toUuid()
}

internal fun Element.selectAsInstant(query: String): Instant {
    val data = select(query).text()
    return Instant.parse(data)
}

internal fun Element.selectAsString(query: String): String {
    return this.select(query).text()
}

internal fun Element.addUuid(uuid: Uuid?) {
    text(uuid?.toBase64() ?: "")
}

internal fun Element.addInstant(instant: Instant?, option: XmlOption) {
    if (instant != null) {
        text(instant.deserialize(option))
    }
}

internal fun Element.addBytes(bytes: ByteArray) {
    text(bytes.decodeToString())
}

@OptIn(ExperimentalEncodingApi::class)
internal fun Instant.deserialize(context: XmlOption): String {
    val binary = context.kdbxVersion.major >= 4 && !context.isExportable

    return if (binary) {
        Base64.encode((epochSeconds + 62135596800).toByteArray())
    } else {
        this.toString()
    }
}

@OptIn(ExperimentalEncodingApi::class)
internal fun Elements.getInstant(): Instant = text().let { text ->
    // Check if ISO text or binary timestamp
    if (text.indexOf(':') > 0) {
        Instant.parse(text)
    } else {
        val seconds = Long.fromByteArray(Base64.decode(text))
        Instant.fromEpochSeconds(seconds -62135596800 )
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

