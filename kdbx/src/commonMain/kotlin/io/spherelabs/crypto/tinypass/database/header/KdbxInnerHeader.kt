package io.spherelabs.crypto.tinypass.database.header

import io.spherelabs.crypto.tinypass.database.model.component.BinaryData
import okio.ByteString

/**
 *  A [KdbxInnerHeader] precedes the XML part; especially, it is compressed (if the compression option is turned on) and encrypted (which is the reason why we call it the inner header).
 *  Directly after the inner header, the XML part follows (in the same compression and encryption stream).
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

enum class CrsAlgorithm {
    None,
    ArcFourVariant,
    Salsa20,
    ChaCha20
}



