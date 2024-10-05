@file:OptIn(ExperimentalEncodingApi::class)

package io.spherelabs.crypto.kdbx.database.model.component

import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.hash.sha256
import io.spherelabs.crypto.hash.sha512
import kotlin.experimental.xor
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * [InternalSecureBytes] represents a secure byte array with optional encryption.
 * It provides methods for encryption, decryption, and hashing of byte arrays.
 *
 * Password hashing and salting are two techniques that can strengthen the security of passwords stored in a database.
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
class InternalSecureBytes(
    private val bytes: ByteArray,
    private val salt: ByteArray,
) {
    /**
     * It represents the count of bytes.
     */
    val size: Int get() = bytes.size

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
        if (other == null || other !is InternalSecureBytes) return false

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

        fun from(bytes: ByteArray): InternalSecureBytes {
            val salt = buildSecureRandom().nextBytes(bytes.size)

            for (i in bytes.indices) {
                bytes[i] = bytes[i] xor salt[i]
            }
            return InternalSecureBytes(bytes = bytes, salt = salt)
        }
    }
}
