package io.spherelabs.anycrypto.securerandom

/**
 * [SecureRandom] interface provides a cryptographically strong number generator(RNG).
 */
interface SecureRandom {
    /**
     * Generates random bytes by size.
     */
    fun nextBytes(size: Int): ByteArray

    /**
     * Generates a user-specified number of random bytes.
     */
    fun nextBytes(byteArray: ByteArray): ByteArray
}

/**
 * This function is used to create a [SecureRandom].
 */
expect fun buildSecureRandom(): SecureRandom
