package io.spherelabs.crypto.tinypass.database.compressor


import io.spherelabs.crypto.tinypass.database.common.toByteArray
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream
import okio.*
import okio.ByteString.Companion.decodeHex
import okio.ByteString.Companion.of
import okio.ByteString.Companion.toByteString


actual fun ByteArray.ungzip(): ByteArray {
    val bis = ByteArrayInputStream(this)
    GZIPInputStream(bis).use { it.readBytes() }
    return bis.readBytes()
}


actual fun ByteArray.gzip(): ByteArray {
    val bos = ByteArrayOutputStream()
    val gzip = GZIPOutputStream(bos).use { it.write(this) }
    return bos.toByteArray()
}

