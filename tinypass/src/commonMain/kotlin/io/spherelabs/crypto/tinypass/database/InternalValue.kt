package io.spherelabs.crypto.tinypass.database

import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.hash.Algorithm
import io.spherelabs.crypto.hash.digest
import kotlin.experimental.xor
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class EncryptedValue(
    private val value: ByteArray,
    private val salt: ByteArray,
) {
    private val sha256 = digest(Algorithm.Sha256)

    /**
     * Length of encrypted value in bytes.
     */
    val byteLength: Int get() = value.size

    /**
     * Decrypts value and parses as [UTF_8][Charsets.UTF_8] string.
     */
    val text: String get() = getBinary().decodeToString()

    /**
     * Decrypts value and calculates SHA256.
     */
    fun binaryHash() = sha256.digest(getBinary())

    /**
     * Decrypts value and returns raw bytes.
     */
    fun getBinary(): ByteArray {
        val bytes = ByteArray(value.size)

        for (i in bytes.indices) {
            bytes[i] = value[i] xor salt[i]
        }
        return bytes
    }

    /**
     * Encodes value with [newSalt].
     */
    fun setSalt(newSalt: ByteArray) {
        for (i in value.indices) {
            value[i] = (value[i] xor salt[i]) xor newSalt[i]
            salt[i] = newSalt[i]
        }
    }

    /**
     * Decrypts value and returns as base 64.
     */
    @OptIn(ExperimentalEncodingApi::class)
    fun toBase64(): String = Base64.encode(getBinary())

    override fun toString(): String = value.decodeToString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is EncryptedValue) return false
        other as EncryptedValue

        return value.contentEquals(other.value) &&
            salt.contentEquals(other.salt)
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + salt.contentHashCode()

        return result
    }

    companion object {
        fun fromString(text: String) = fromBinary(text.encodeToByteArray())

        @OptIn(ExperimentalEncodingApi::class)
        fun fromBase64(base64: String) = fromBinary(Base64.decode(base64.encodeToByteArray()))

        fun fromBinary(bytes: ByteArray): EncryptedValue {
            val salt = buildSecureRandom().nextBytes(bytes.size)

            for (i in bytes.indices) {
                bytes[i] = bytes[i] xor salt[i]
            }
            return EncryptedValue(bytes, salt)
        }
    }
}
