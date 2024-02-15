package io.spherelabs.anycrypto.securerandom

interface SecureRandom {
    fun nextBytes(size: Int): ByteArray
    fun nextBytes(byteArray: ByteArray): ByteArray
}

expect fun buildSecureRandom(): SecureRandom
