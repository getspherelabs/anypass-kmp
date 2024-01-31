package io.spherelabs.anycrypto.securerandom

interface SecureRandom {
    fun nextBytes(size: Int): ByteArray
}

expect fun buildSecureRandom(): SecureRandom
