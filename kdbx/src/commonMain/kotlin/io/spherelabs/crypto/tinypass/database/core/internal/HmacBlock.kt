package io.spherelabs.crypto.tinypass.database.core.internal

import io.spherelabs.crypto.hash.sha512
import io.spherelabs.crypto.tinypass.database.common.constantTimeEquals
import io.spherelabs.crypto.tinypass.database.common.intToLittleEndian
import io.spherelabs.crypto.tinypass.database.common.longToLittleEndian
import okio.Buffer
import okio.BufferedSink
import okio.BufferedSource
import okio.ByteString.Companion.toByteString

/**
 * A [HmacBlock] provides functionality for writing and reading HMAC-protected content blocks.
 * Each block's integrity is verified using HMAC (Hash-based Message Authentication Code) computed
 * with a dynamically derived key based on the block number, block length, and block content.
 *
 * ### HMAC?
 * Hash-based Message Authentication Code (HMAC) is a type of message authentication code (MAC) involving a cryptographic hash function
 * and a secret cryptographic key. HMAC makes it possible to confirm the data integrity and authenticity of a message.
 * This is especially useful in scenarios like digital signatures, certificate authorities, and transport layer security
 * and secure sockets layer (TLS and SSL) protocols.
 *
 * ```kotlin
 * HMAC = hashFunc(secret key + message)
 * ```
 *
 * ### Secret Key
 * In the HMAC process, cryptographic keys play a crucial role. The operation begins when both the sender
 * and receiver of a message agree on a secret key. This key will be used in the hashing process,
 * ensuring that both parties can verify the authenticity of the message. The secret key is combined with the original message
 * to create a hash.
 *
 * ### Hash Function
 * The hash function is a crucial part of the HMAC process.
 * Once combined with the secret key, the hash function is applied to the resulting message.
 * This hash function can be any cryptographic hash function, such as MD5 or SHA-1.
 * The output of the hash function is a fixed-size string of bytes—the final HMAC.
 *
 */
internal object HmacBlock {
    /** The rate at which the content data is split into blocks for processing. */
    private const val RATE_SPLIT = 1048576

    fun write(
        sink: BufferedSink,
        bytes: ByteArray,
        seed: ByteArray,
        transformedKey: ByteArray,
    ) {
        val hmacKey = createBlockHmacKey(seed, transformedKey)
        var index = 0L
        var offset = 0

        while (offset < bytes.size) {
            val (length, data) = getNextBlock(bytes, offset)
            val hash = createBlockHmac(hmacKey, index, length, data)

            sink.write(hash)
            sink.writeIntLe(length)
            sink.write(data)
            index++
            offset += length
        }

        val (lastLength, lastData) = getLastBlock(bytes, offset)
        val lastBlockHash = createBlockHmac(hmacKey, index, lastLength, lastData)

        sink.write(lastBlockHash)
        sink.writeIntLe(lastLength)
    }

    fun read(
        source: BufferedSource,
        seed: ByteArray,
        transformedKey: ByteArray,
    ): ByteArray {
        val buffer = Buffer()
        val hmacKey = createBlockHmacKey(seed, transformedKey)

        var index = 0L

        while (true) {
            val hash = source.readByteArray(32)
            val length = source.readIntLe()

            if (length > 0) {
                val data = source.readByteArray(length.toLong())
                if (!createBlockHmac(hmacKey, index, length, data).constantTimeEquals(hash)) {
                    throw Exception("HMAC for block $index does not match.")
                }
                buffer.write(data)
                index++
            } else {
                break
            }
        }

        return buffer.readByteArray()
    }

    private fun getNextBlock(bytes: ByteArray, offset: Int): Pair<Int, ByteArray> {
        val length = minOf(bytes.size - offset)
        val data = bytes.sliceArray(offset until offset + length)
        return length to data
    }

    private fun getLastBlock(bytes: ByteArray, offset: Int): Pair<Int, ByteArray> {
        val length = minOf(bytes.size - offset, RATE_SPLIT)
        val data = bytes.sliceArray(offset until offset + length)
        return length to data
    }

    private fun createBlockHmacKey(
        seed: ByteArray,
        transformedKey: ByteArray,
    ) = byteArrayOf(*seed, *transformedKey, 0x01).sha512()

    private fun createBlockHmac(
        hmacKey: ByteArray,
        index: Long,
        length: Int,
        data: ByteArray,
    ): ByteArray {
        val indexBytes = longToLittleEndian(index)
        val blockKey = (indexBytes + hmacKey)
            .toByteString()
            .sha512()

        return (indexBytes + intToLittleEndian(length) + data)
            .toByteString()
            .hmacSha256(blockKey)
            .toByteArray()
    }
}

