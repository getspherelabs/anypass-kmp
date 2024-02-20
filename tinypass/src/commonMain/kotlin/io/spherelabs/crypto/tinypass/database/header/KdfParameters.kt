package io.spherelabs.crypto.tinypass.database.header

import okio.ByteString

/**
 * As of KDBX 4, key derivation function parameters are stored in the header field with ID 11 (KdfParameters).
 * The parameters are serialized as a FieldStorage (with the KDF UUID being stored in '$UUID');
 * For details on the parameters being used by AES-KDF and Argon2.
 */
sealed class KdfParameters(
    open val uuid: ByteString,
) {
    data class AES(
        override val uuid: ByteString,
        val rounds: ULong,
        val seed: ByteString,
    ) : KdfParameters(uuid)

    data class Argon2(
        override val uuid: ByteString,
        val salt: ByteString,
        val parallelism: UInt,
        val memory: ULong,
        val iterations: ULong,
        val version: UInt,
        val key: ByteString?,
        val associatedData: ByteString?,
    ) : KdfParameters(uuid)


    fun serialize(): ByteString {
        val fields = when (this) {
            is AES -> mapOf(
                UUID to Kdbx4Field.Bytes(uuid),
                ROUNDS to Kdbx4Field.UInt64(rounds),
                SALT_OR_SEED to Kdbx4Field.Bytes(seed),
            )

            is Argon2 -> mapOf(
                UUID to Kdbx4Field.Bytes(uuid),
                SALT_OR_SEED to Kdbx4Field.Bytes(salt),
                PARALLELISM to Kdbx4Field.UInt32(parallelism),
                MEMORY to Kdbx4Field.UInt64(memory),
                ITERATIONS to Kdbx4Field.UInt64(iterations),
                VERSION to Kdbx4Field.UInt32(version),
            )
        }

        return VarDict.serialize(fields)
    }

    companion object {

        fun deserialize(output: ByteString): KdfParameters {
            val bucket = VarDict.deserialize(output)

            return when (val uuid = (bucket[UUID] as? Kdbx4Field.Bytes)?.rawValue) {
                KdfAes -> {
                    AES(
                        uuid = uuid,
                        rounds = (bucket[ROUNDS] as? Kdbx4Field.UInt64)?.rawValue
                            ?: throw IllegalArgumentException(),
                        seed = (bucket[SALT_OR_SEED] as? Kdbx4Field.Bytes)?.rawValue
                            ?: throw IllegalArgumentException(),
                    )
                }

                KdfArgon2d, KdfArgon2id -> {
                    Argon2(
                        uuid = uuid,
                        salt = (bucket[SALT_OR_SEED] as? Kdbx4Field.Bytes)?.rawValue
                            ?: throw IllegalArgumentException(),
                        parallelism = (bucket[PARALLELISM] as? Kdbx4Field.UInt32)?.rawValue
                            ?: throw IllegalArgumentException(),
                        memory = (bucket[MEMORY] as? Kdbx4Field.UInt64)?.rawValue
                            ?: throw IllegalArgumentException(),
                        iterations = (bucket[ITERATIONS] as? Kdbx4Field.UInt64)?.rawValue
                            ?: throw IllegalArgumentException(),
                        version = (bucket[VERSION] as? Kdbx4Field.UInt32)?.rawValue
                            ?: throw IllegalArgumentException(),
                        null,
                        null,
                    )
                }

                else -> throw IllegalArgumentException()
            }
        }

        const val UUID = "\$UUID"
        const val ROUNDS = "R"
        const val SALT_OR_SEED = "S"
        const val PARALLELISM = "P"
        const val MEMORY = "M"
        const val ITERATIONS = "I"
        const val VERSION = "V"
        const val KEY = "K" // Unsupported
        const val ASSOC_DATA = "A" // Unsupported

        val KdfAes = ByteString.of(
            0xC9.toByte(),
            0xD9.toByte(),
            0xF3.toByte(),
            0x9A.toByte(),
            0x62,
            0x8A.toByte(),
            0x44,
            0x60,
            0xBF.toByte(),
            0x74,
            0x0D,
            0x08,
            0xC1.toByte(),
            0x8A.toByte(),
            0x4F,
            0xEA.toByte(),
        )

        val KdfArgon2d = ByteString.of(
            0xEF.toByte(),
            0x63,
            0x6D,
            0xDF.toByte(),
            0x8C.toByte(),
            0x29,
            0x44,
            0x4B.toByte(),
            0x91.toByte(),
            0xF7.toByte(),
            0xA9.toByte(),
            0xA4.toByte(),
            0x03,
            0xE3.toByte(),
            0x0A,
            0x0C,
        )

        val KdfArgon2id = ByteString.of(
            0x9E.toByte(),
            0x29,
            0x8B.toByte(),
            0x19,
            0x56,
            0xDB.toByte(),
            0x47,
            0x73,
            0xB2.toByte(),
            0x3D,
            0xFC.toByte(),
            0x3E,
            0xC6.toByte(),
            0xF0.toByte(),
            0xA1.toByte(),
            0xE6.toByte(),
        )
    }
}
