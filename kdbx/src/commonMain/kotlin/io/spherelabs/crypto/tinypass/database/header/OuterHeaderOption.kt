package io.spherelabs.crypto.tinypass.database.header

import io.spherelabs.crypto.tinypass.database.signature.KdbxSignature
import okio.Buffer
import okio.BufferedSink
import okio.buffer

data class OuterHeaderOption(
    var kdbxSignature: KdbxSignature,
    var kdbxVersion: KdbxVersion,
    var cipherId: CipherId,
    var compressionFlags: CompressionFlags,
) {
    companion object {

        val Default = OuterHeaderOption(
            kdbxSignature = KdbxSignature.Default,
            kdbxVersion = KdbxVersion(major = 4, minor = 1),
            compressionFlags = CompressionFlags.GZip,
            cipherId = CipherId.Aes,
        )

        const val CIPHER_ID_LENGTH = 16
    }
}
