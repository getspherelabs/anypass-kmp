package io.spherelabs.crypto.tinypass.database.core

import io.spherelabs.crypto.hash.sha256
import io.spherelabs.crypto.hash.sha512
import io.spherelabs.crypto.tinypass.database.common.constantTimeEquals
import io.spherelabs.crypto.tinypass.database.common.intToLittleEndian
import io.spherelabs.crypto.tinypass.database.common.longToLittleEndian
import okio.Buffer
import okio.BufferedSink
import okio.BufferedSource
import okio.ByteString.Companion.toByteString

/**
 * Takes a stream of (encrypted GZIP) data and formats it as HMAC Hashed Blocks to the
 * underlying output stream.
 * <p>
 * An HMac block consists of
 * <ol>
 * <li>a 32 byte HMac checksum</li>
 * <li>a 4 byte block size</li>
 * <li>{blockSize} bytes of data</li>
 * </ol>
 * <p>
 * The Class is initialized with an initial key digest. For each block this key is transformed
 * using the (implied, starting from 0) block number and used as the key for the block verification process.
 * That process consists of digesting the block number, its length, and its content.
 * <p>
 * Streams in Keepass are Little Endian.
 */
internal object ContentBlocks {
    private const val BlockSplitRate = 1048576

    private class Block(
        val index: Long,
        val length: Int,
        val data: ByteArray,
    )

    fun readContentBlocksVer3x(source: BufferedSource): ByteArray {
        val contentData = Buffer()

        while (true) {
            val index = source.readIntLe()
            val hash = source.readByteArray(32)
            val length = source.readIntLe()

            if (length > 0) {
                val data = source.readByteArray(length.toLong())
                if (!data.sha256().contentEquals(hash)) {
                    throw Exception("Hash for block $index does not match.")
                }
                contentData.write(data)
            } else {
                break
            }
        }

        return contentData.readByteArray()
    }

    fun readContentBlocksVer4x(
        source: BufferedSource,
        masterSeed: ByteArray,
        transformedKey: ByteArray,
    ): ByteArray {
        val contentData = Buffer()
        val hmacKey = createBlockHmacKey(masterSeed, transformedKey)
        var index = 0L

        while (true) {
            val hash = source.readByteArray(32)
            val length = source.readIntLe()

            if (length > 0) {
                val data = source.readByteArray(length.toLong())
                if (!createBlockHmac(hmacKey, index, length, data).constantTimeEquals(hash)) {
                    throw Exception("HMAC for block $index does not match.")
                }
                contentData.write(data)
                index++
            } else {
                break
            }
        }

        return contentData.readByteArray()
    }

    internal fun writeContentBlocksVer3x(
        sink: BufferedSink,
        contentData: ByteArray,
    ) = writeContentBlocks(sink, contentData, true) {
        if (data.isNotEmpty()) {
            data.sha256()
        } else {
            ByteArray(32) { 0x0 }
        }
    }

    internal fun writeContentBlocksVer4x(
        sink: BufferedSink,
        contentData: ByteArray,
        masterSeed: ByteArray,
        transformedKey: ByteArray,
    ) {
        val hmacKey = createBlockHmacKey(masterSeed, transformedKey)

        writeContentBlocks(sink, contentData, false) {
            createBlockHmac(hmacKey, index, length, data)
        }
    }

    private fun writeContentBlocks(
        sink: BufferedSink,
        contentData: ByteArray,
        writeIndexes: Boolean,
        hashFunc: Block.() -> ByteArray,
    ): Unit = with(sink) {
        var index = 0L
        var offset = 0

        while (offset < contentData.size) {
            val length = minOf(contentData.size - offset, BlockSplitRate)
            val data = contentData.sliceArray(offset until offset + length)
            val hash = hashFunc(Block(index, length, data))

            if (writeIndexes) {
                writeIntLe(index.toInt())
            }
            write(hash)
            writeIntLe(length)
            write(data)
            index++
            offset += length
        }
        if (writeIndexes) {
            writeIntLe(index.toInt())
        }
        write(hashFunc(Block(index, 0, ByteArray(0))))
        writeIntLe(0)
    }

    private fun createBlockHmacKey(
        masterSeed: ByteArray,
        transformedKey: ByteArray,
    ) = byteArrayOf(*masterSeed, *transformedKey, 0x01).sha512()

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


