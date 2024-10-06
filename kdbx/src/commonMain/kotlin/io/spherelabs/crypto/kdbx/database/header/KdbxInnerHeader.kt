package io.spherelabs.crypto.kdbx.database.header

import io.spherelabs.crypto.kdbx.database.model.component.BinaryData
import okio.ByteString

/**
 * Represents the inner header of a KDBX file, which precedes the XML payload.
 * The inner header handles data compression and encryption, ensuring secure storage of the XML data.
 * Compression and encryption depend on the selected stream cipher algorithm.
 *
 * Immediately following the [KdbxInnerHeader], the compressed and encrypted XML part is streamed.
 *
 * @property streamCipher The algorithm used to compress and encrypt the XML payload.
 * @property streamKey The key for the selected stream cipher algorithm.
 * @property binaries A map holding binary data associated with the KDBX file. This is often used for attachments.
 */
data class KdbxInnerHeader(
    val streamCipher: CrsAlgorithm,
    val streamKey: ByteString,
    val binaries: Map<ByteString, BinaryData> = linkedMapOf(),
) {

    companion object {
        const val END_MARKER = 0x00
        const val CIPHER = 0x01
        const val KEY = 0x02
        const val BINARY = 0x03

        fun of(streamKey: ByteString): KdbxInnerHeader {
            return KdbxInnerHeader(
                streamCipher = CrsAlgorithm.ChaCha20,
                streamKey = streamKey,
                binaries = linkedMapOf(),
            )
        }
    }
}

/**
 * [CrsAlgorithm] representing supported stream cipher algorithms for KDBX file encryption.
 * This controls both encryption and decryption of the XML payload.
 */
enum class CrsAlgorithm {
    None,            // No encryption/compression applied (generally unsafe)
    ArcFourVariant,  // Legacy encryption algorithm (deprecated)
    Salsa20,         // Older encryption algorithm (less secure than ChaCha20)
    ChaCha20         // Recommended algorithm for secure encryption
}

