package io.spherelabs.anycrypto.securerandom

import java.security.SecureRandom as JvmSecureRandom

class AndroidSecureRandom(
    private val secureRandom: JvmSecureRandom,
) : SecureRandom {
    override fun nextBytes(size: Int): ByteArray {
        val byteArray = ByteArray(size)
        secureRandom.nextBytes(byteArray)
        return byteArray
    }

    override fun nextBytes(byteArray: ByteArray): ByteArray {
        secureRandom.nextBytes(byteArray)
        return byteArray
    }
}

actual fun buildSecureRandom(): SecureRandom = AndroidSecureRandom(JvmSecureRandom())
