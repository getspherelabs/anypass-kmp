package io.spherelabs.crypto.tinypass.database.header

import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.buffer.internal.commonWriteVarDict
import io.spherelabs.crypto.tinypass.database.common.toIntLe
import io.spherelabs.crypto.tinypass.database.common.toUuid
import io.spherelabs.crypto.tinypass.database.signature.KdbxSignature
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
 * CipherID: AES256-EBC
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

data class KdbxOuterHeader(
    val option: OuterHeaderOption,
    val seed: ByteString,
    val encryptionIV: ByteString,
    val kdfParameters: KdfParameters,
    val customData: Map<String, Kdbx4Field>,
) {
    fun serialize(sink: BufferedSink) {
        with(sink) {
            option.serialize(sink)
            serializeSeed()
            serializeEncryptionIv()
            serializeParams()
            println("Option is $option")
            println("Option is $kdfParameters")
            val customData = commonWriteVarDict(customData)
            writeByte(FieldID.PublicCustomData.ordinal)
            writeIntLe(customData.size)
            write(customData)

            writeByte(FieldID.End.ordinal)
            writeIntLe(end.size)
            write(end)
        }
    }

    private fun BufferedSink.serializeSeed() {
        // Write the seed
        writeByte(FieldID.Seed.ordinal)
        writeIntLe(seed.size)
        write(seed)
    }

    private fun BufferedSink.serializeEncryptionIv() {
        this.writeByte(FieldID.EncryptionIV.ordinal)
        this.writeIntLe(encryptionIV.size)
        this.write(encryptionIV)
    }

    private fun BufferedSink.serializeParams() {
        val params = kdfParameters.serialize()
        writeByte(FieldID.KdfParameters.ordinal)
        writeIntLe(params.size)
        write(params)
    }

    companion object {

        fun create(): KdbxOuterHeader {
            val random = buildSecureRandom()

            return KdbxOuterHeader(
                option = OuterHeaderOption.Default,
                seed = random.nextBytes(32).toByteString(),
                encryptionIV = random.nextBytes(16).toByteString(),
                kdfParameters = KdfParameters.Argon2(
                    uuid = KdfParameters.KdfArgon2d,
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

        fun deserialize(source: BufferedSource): KdbxOuterHeader {
            var cipherId: CipherId? = null
            var compressionFlags: CompressionFlags? = null

            var seed: ByteString? = null
            var encryptionIV: ByteString? = null
            var params: KdfParameters? = null
            var customData: Map<String, Kdbx4Field> = mapOf()

            val kdbxSignature = KdbxSignature.deserialize(source)
            val kdbxVersion = KdbxVersion.deserialize(source)

            while (true) {
                val (id, rawData) = readHeaderValue(source, kdbxVersion)
                println("Raw data is $rawData")
                val fieldId = FieldID.values().getOrNull(id)
                println("Field id $fieldId")
                when (fieldId) {
                    FieldID.End -> break
                    FieldID.CipherID -> {
                        cipherId = checkNotNull(
                            rawData.toUuid()?.let {
                                CipherId.values().firstOrNull { it.uuid == it.uuid }
                            },
                        )
                    }

                    FieldID.CompressionFlags -> {
                        compressionFlags = CompressionFlags.values()[rawData.toIntLe()]
                    }

                    FieldID.Seed -> {
                        seed = rawData
                    }

                    FieldID.EncryptionIV -> encryptionIV = rawData
                    FieldID.KdfParameters -> {
                        params = KdfParameters.deserialize(rawData)
                    }

                    FieldID.PublicCustomData -> customData = VarDict.deserialize(rawData)
                    else -> {}
                }
            }

            return KdbxOuterHeader(
                option = OuterHeaderOption(
                    kdbxSignature = kdbxSignature,
                    kdbxVersion = kdbxVersion,
                    cipherId = checkNotNull(cipherId),
                    compressionFlags = checkNotNull(compressionFlags),
                ),
                seed = checkNotNull(seed),
                encryptionIV = checkNotNull(encryptionIV),
                kdfParameters = checkNotNull(params),
                customData = customData,
            )

        }

        private fun readHeaderValue(
            source: BufferedSource,
            kdbxVersion: KdbxVersion,
        ): Pair<Int, ByteString> {
            println("Hey,")
            val id = source.readByte()

            println("Id is $id")
            val length = source.readIntLe().toLong()
            val data = if (length > 0) {
                println("Length is $length")
                source.readByteString(length)
            } else {
                ByteString.EMPTY
            }
            return id.toInt() to data
        }

        val end = ByteString.of(0x0D, 0x0A, 0x0D, 0x0A)

        const val End = 0
        const val SEED = 3
        const val ENCRYPTION_IV = 4
        const val KDF_PARAMETERS = 5
        const val CUSTOM_DATA = 6

        const val DbVer4Aes =
            "A9mimmf7S7UAAAQAAhAAAAAxwfLmv3FDUL5YBSFq/Fr/AwQAAAABAAAABCAAAACCdsEJoa+/HI4PlAqhFR+1ZoZ1aU6vtdlxY5jLWTEcogcQAAAAUiN/IXWVctgggk7HpkgJCwtdAAAAAAFCBQAAACRVVUlEEAAAAMnZ85piikRgv3QNCMGKT+pCAQAAAFMgAAAAcvkOGPCC91iXT7+0PHgKSrz1iyw1hClm2lIY0lKQqz0FAQAAAFIIAAAA4JMEAAAAAAAAAAQAAAAA0K0KMpvUzK+vW5N4VgfrjB6WpW5pQta2y8oXrSdnB/fipqvp0zQrRasNKs2n+nJIsG/z5/cID6qCn9SKTXJyCHBB69DvHXbl/IGT/554qluYE8KSxdWBB7aa5iAOcZcOZFNXcAMAAD4h3BpDHx/phFQR4yEvnLaHiXytgubQTRgo5iukAn8/QmMCu5zjvcGap3BoBKghkjHpOMFdEtDum7pHBqbncsIH81bbqFchEKWj+GPnxWjVau1164emKOMVrre+srFVNUKMpDynw+mZ2oNZcRzApKVPGH2nDqx4QAgaOQGcllCBlYjnsCZlZC2tFOuopdRva8fNOWEXgyMcICd9sUV3KBLBn0mk0W2vAoKXe8MwHUfEzVtCAOzP8vFSEnJcqkGmJsZjx2d6SlZkvGqgrwM7p9VLVxCwSkVDKPuFq2qtNSbGZX1iZsZOM0N4VDBQLrlIaNSxLnKnKxnRYl/8dScD4Q7h4MlYBHQX1OLyLlAR/n78Pe201A22KRCHrjh7sqQ/v0Miq3msN8crnvofMky9xuA65k1HAwH8nIfQPDWxaYKhK9CDXifC7JNfrcCw6jxwY3FSP9mm/HKQQjgF3+n3dEhJofWCGh8Z3yrgws/vo2WDHUay5Ia3puR7L2Wmu4+XDnt8EjwqVkkG58x8OcmfHwxgr3ax46rrDfLMts4UWILGaI8I0vcDSZHvQya6ibQ10gIYFP840pgOj5DPtIASfWWj6qpNSmyRZ4aLfRD7xrjlKcKBtOvLT7Or1KO48a8G+Y13DXucsIWCvSBFUiiMKimvh6nLD/5t1Nu0igsBDChP4a/JMhtpVg7dUuH1jQKvDaD6TdJ/PTj5K7oFDhKqyuwIGLpaP8T7k3+luYZgf0jWSNKGTZ37ZEg7yLI9oZ7G0JqV/UrPWiWg7VMoMmCBm9ZM5JBNp4Qdjf3cJWESxEXNtNgVldDSFoTR6kATiJjhS7gk6kWD/1YvjVS2wkVejTWBIk9xSygwmH++CvtibLr+V5TXawprkDuAeGGhfo46OH7gwuK1wvPdmmrykay9f7AZdGOCpeFiZ+jmxiBj+mXmB0Qrz0Pb3SltkH9rOMhV1YBqSHyyuym84HBq/bWoCzc5a0sM9OQ5jMKOK1r5/BxWGx2Ebx/KQhCGW6546Ld6ny99YUhYKG0UMNI3DumC5FXP+bZL0wbGE084hB544zFCR4v83Eqwp5dSTyG0ktfrnGGV8dHG57nKacoB0jORKuCnCV32fW3+GkdwptdZXTXUA9OLIonWpf1nlH5gIHhlwGhg5DaMCVHNjKRFLzbaaHmuwuGNPdzanrBmQFl1DQncq20FXaedD1hYIa8iBeNnfAAAAAA="
    }


}

enum class CompressionFlags {
    None, GZip
}


enum class FieldID {
    End, // 0
    CipherID, // 1
    CompressionFlags, // 2
    Seed, // 3
    EncryptionIV, // 4
    KdfParameters, // 5
    PublicCustomData // 6
}
