package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.common.toIntLe
import io.spherelabs.crypto.tinypass.database.common.toUuid
import io.spherelabs.crypto.tinypass.database.header.*
import io.spherelabs.crypto.tinypass.database.signature.Signature
import okio.BufferedSource
import okio.ByteString

object KdbxSource {
    fun read(source: BufferedSource, input: KdbxInput): KdbxOutput {
        return when (input) {
            KdbxInput.OuterHeader -> {
                val outerHeader = readOuterHeader(source)
                KdbxOutput.OuterHeader(outerHeader)
            }
            KdbxInput.InnerHeader -> TODO()
        }
    }

    private fun readOuterHeader(source: BufferedSource): KdbxOuterHeader {
        var cipherId: CipherId? = null
        var compressionFlags: CompressionFlags? = null

        var seed: ByteString? = null
        var encryptionIV: ByteString? = null
        var params: KdfParameters? = null
        var customData: Map<String, Kdbx4Field> = mapOf()

        val signature = Signature.deserialize(source)
        val version = Version.deserialize(source)

        while (true) {
            val (id, rawData) = readHeaderValue(source, version)
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
                signature = signature,
                version = version,
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
        version: Version,
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
}
