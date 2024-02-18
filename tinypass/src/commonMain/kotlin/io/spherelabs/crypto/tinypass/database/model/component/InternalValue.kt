@file:OptIn(ExperimentalEncodingApi::class)

package io.spherelabs.crypto.tinypass.database.model.component

import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.hash.sha256
import io.spherelabs.crypto.hash.sha512
import kotlin.experimental.xor
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * [SecureBytes] represents a secure byte array with optional encryption.
 * This class provides methods for encryption, decryption, and hashing of byte arrays.
 *
 * Password hashing and salting are two techniques that
 * can strengthen the security of passwords stored in a database.
 * The hashing algorithm involves a mathematical operation that alters or transforms a password into a string of random characters.
 *
 * However, hackers can try to guess a password by comparing hashes of common passwords.
 * To prevent this, password salting comes into play.
 *
 * Password salting is the method of appending a random piece of data, known as salt, to the password before applying hashing algorithm.
 * The salt ensures that the hash is distinct and that two users with identical passwords will have different hashes.
 *
 * Keeps the bytes in memory.
 */
class SecureBytes(
    private val bytes: ByteArray,
    private val salt: ByteArray,
) {
    /**
     * It represents the count of bytes.
     */
    val count: Int get() = bytes.size

    val isEmpty: Boolean get() = bytes.isEmpty()

    val plainText: String = decrypt().decodeToString()

    val sha256 get() = decrypt().sha256()

    val sha512 get() = decrypt().sha512()

    val raw: ByteArray = decrypt()

    override fun toString(): String = bytes.decodeToString()

    private fun decrypt(): ByteArray {
        val bytes = ByteArray(bytes.size)

        bytes.indices.forEach { i ->
            bytes[i] = this.bytes[i] xor salt[i]
        }

        return bytes
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is SecureBytes) return false

        return bytes.contentEquals(other.bytes) &&
            salt.contentEquals(other.salt)
    }

    override fun hashCode(): Int {
        var result = bytes.contentHashCode()
        result = 31 * result + salt.contentHashCode()

        return result
    }

    companion object {
        fun fromPlainText(plainText: String) = from(plainText.encodeToByteArray())

        fun fromBase64(base64: String) = from(Base64.decode(base64.encodeToByteArray()))

        fun from(bytes: ByteArray): SecureBytes {
            val salt = buildSecureRandom().nextBytes(bytes.size)

            for (i in bytes.indices) {
                bytes[i] = bytes[i] xor salt[i]
            }
            return SecureBytes(bytes = bytes, salt = salt)
        }
    }
}

class EncryptedValue(
    private val value: ByteArray,
    private val salt: ByteArray,
) {

    /**
     * Length of encrypted value in bytes.
     */
    val byteLength: Int get() = value.size

    /**
     * Decrypts value and parses as UTF_8 string.
     */
    val text: String get() = getBinary().decodeToString()

    /**
     * Decrypts value and calculates SHA256.
     */
    fun binaryHash() = getBinary().sha256()

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
    fun toBase64(): String = Base64.encode(getBinary())

    override fun toString(): String = value.decodeToString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is EncryptedValue) return false
        other

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

        fun fromBinary(bytes: ByteArray): EncryptedValue {
            val salt = buildSecureRandom().nextBytes(bytes.size)

            for (i in bytes.indices) {
                bytes[i] = bytes[i] xor salt[i]
            }
            return EncryptedValue(bytes, salt)
        }
    }
}
