package io.spherelabs.crypto.tinypass.database.common

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuidFrom
import com.benasher44.uuid.uuidOf
import com.fleeksoft.ksoup.nodes.Element
import kotlinx.datetime.Instant
import okio.Buffer
import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.toByteString

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


internal fun Element.addBytes(bytes: ByteArray) {
    text(bytes.decodeToString())
}
