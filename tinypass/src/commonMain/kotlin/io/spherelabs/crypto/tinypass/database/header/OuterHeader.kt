package io.spherelabs.crypto.tinypass.database.header

import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.common.toIntLe
import io.spherelabs.crypto.tinypass.database.common.toUuid
import io.spherelabs.crypto.tinypass.database.signature.Signature
import okio.BufferedSink
import okio.BufferedSource
import okio.ByteString
import okio.ByteString.Companion.toByteString

/**
 * https://palant.info/2023/03/29/documenting-keepass-kdbx4-file-format/
 *
 * 03 d9 a2 9a   67 fb 4b b5   01 00   04 00
 * ┗━━━━━━━━━┛   ┗━━━━━━━━━┛   ┗━━━┛   ┗━━━┛
 * Signature1    Signature2    Version: 4.1
 *
 * 02   10 00 00 00   31 c1 f2 e6 bf 71 43 50 be 58 05 21 6a fc 5a ff
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * CipherID: AES256-CBC
 *
 * 03   04 00 00 00   01 00 00 00
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * Compression: Gzip
 *
 * 04   20 00 00 00   12 34 56 78 12 34 56 78 12 34 56 78 12 34 56 78
 * 12 34 56 78 12 34 56 78 12 34 56 78 12 34 56 78
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * MainSeed (random)
 *
 * 07   10 00 00 00   12 34 56 78 12 34 56 78 12 34 56 78 12 34 56 78
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * EncryptionIV (random)
 *
 * 0b   8b 00 00 00          00 01
 * ┗━━━━━━━━━━━━━━┛          ┗━━━┛
 * KdfParameters starting    FieldStorage version: 0x100
 *
 * 42   05 00 00 00   24 55 55 49 44   10 00 00 00
 * ef 63 6d df 8c 29 44 4b 91 f7 a9 a4 03 e3 0a 0c
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * bytes[] entry, Key $UUID, Value Argon2d
 *
 * 05   01 00 00 00   49   08 00 00 00   02 00 00 00 00 00 00 00
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * UInt64 entry, Key I, Value 2
 *
 * 05   01 00 00 00   4d   08 00 00 00   00 00 00 40 00 00 00 00
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * UInt64 entry, Key M, Value 0x40000000 (1 GB)
 *
 * 04   01 00 00 00   50   04 00 00 00   08 00 00 00
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * UInt32 entry, Key P, Value 8
 *
 * 42   01 00 00 00   53   20 00 00 00
 * 12 34 56 78 12 34 56 78 12 34 56 78 12 34 56 78
 * 12 34 56 78 12 34 56 78 12 34 56 78 12 34 56 78
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * bytes[] entry, Key S, Value random
 *
 * 04   01 00 00 00   56   04 00 00 00   13 00 00 00
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * UInt32 entry, Key V, Value 0x13
 *
 * 00
 * ┗┛
 * End of VariantMap
 *
 * 00    04 00 00 00   0d 0a 0d 0a
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * EndOfHeader
 */


data class OuterHeader(
    val options: OuterHeaderOption,
    val seed: ByteString,
    val encryptionIV: ByteString,
    val keyDerivationParameters: KeyDerivationParameters,
    val customData: Map<String, Kdbx4Field>,
) {
    fun writeTo(sink: BufferedSink) {
        options.serialize(sink)

        // Writing the seed.
        sink.writeByte(SEED)
        sink.writeIntLe(seed.size)
        sink.write(seed)

        // Writing the encryption IV.
        sink.writeByte(ENCRYPTION_IV)
        sink.writeIntLe(encryptionIV.size)
        sink.write(encryptionIV)

        // Writing the key derivation parameters.
        sink.writeByte(KDF_PARAMETERS)
        val keyDerivationParam = keyDerivationParameters.serialize()
        sink.writeIntLe(keyDerivationParam.size)
        sink.write(keyDerivationParam)

        // Writing the custom data.
        sink.writeByte(CUSTOM_DATA)
        sink.writeIntLe(customData.size)
        val data = FieldStorage.write(customData)
        sink.write(data)
    }

    companion object {
        fun create(): OuterHeader {
            val random = buildSecureRandom()

            return OuterHeader(
                options = OuterHeaderOption.Default,
                seed = random.nextBytes(32).toByteString(),
                encryptionIV = random.nextBytes(16).toByteString(),
                keyDerivationParameters = KeyDerivationParameters.Argon2(
                    uuid = KeyDerivationParameters.KdfArgon2d,
                    salt = random.nextBytes(32).toByteString(),
                    parallelism = 2U,
                    memory = 32UL * 1024UL * 1024UL,
                    iterations = 8U,
                    version = 0x13.toUInt(),
                    key = null,
                    associatedData = null,
                ),
                customData = mapOf(),
            )
        }

        fun deserialize(source: BufferedSource): OuterHeader {
            val options: OuterHeaderOption? = null
//
//            var cipherId: CipherId? = null
//            var compressionFlags: CompressionFlags? = null
            var seed: ByteString? = null
            var encryptionIV: ByteString? = null
            var keyDerivationParameters: KeyDerivationParameters? = null
            var customData: Map<String, Kdbx4Field> = mapOf()

            val signature = Signature.deserialize(source)
            val version = Version.deserialize(source)

            while (true) {
                val id = source.readByte().toInt()
                val length = source.readIntLe().toLong()
                val data = source.readByteString(length)

                when (id) {
                    END_OF_HEADER -> break
                    CIPHER_ID -> {
                        cipherId = data.toUuid().let { cipherId ->
                            CipherId.values().firstOrNull { newCipherId ->
                                newCipherId.uuid == cipherId
                            }
                        }
                    }

                    COMPRESSION -> {
                        compressionFlags = CompressionFlags.values()[data.toIntLe()]
                    }

                    SEED -> {
                        seed = data
                    }

                    ENCRYPTION_IV -> {
                        encryptionIV = data
                    }

                    KDF_PARAMETERS -> {
                        keyDerivationParameters = KeyDerivationParameters.deserialize(data)
                    }

                    CUSTOM_DATA -> {
                        customData = FieldStorage.read(data)
                    }
                }
            }
            return OuterHeader(
                signature = signature,
                cipherId = requireNotNull(cipherId) { "Cipher id is null." },
                version = version,
                compressionFlags = requireNotNull(compressionFlags) { "Compression is null." },
                encryptionIV = requireNotNull(encryptionIV) { "Encryption IV is null." },
                seed = requireNotNull(seed) { "Seed is null." },
                keyDerivationParameters = requireNotNull(keyDerivationParameters) { "Key derivation is null." },
                customData = customData,
            )
        }

        const val END_OF_HEADER = 0
        const val CIPHER_ID = 1
        const val COMPRESSION = 2
        const val SEED = 3
        const val ENCRYPTION_IV = 4
        const val KDF_PARAMETERS = 5
        const val CUSTOM_DATA = 6
    }


}

enum class CompressionFlags {
    None, GZip
}

