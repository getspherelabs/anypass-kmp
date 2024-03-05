package io.spherelabs.anycrypto.securerandom

import java.security.SecureRandom as JvmSecureRandom

class AndroidSecureRandom(
    private val secureRandom: JvmSecureRandom,
) : SecureRandom {
    override fun nextBytes(size: Int): ByteArray {
        return if (size != 0) {
            val byteArray = ByteArray(size)
            secureRandom.nextBytes(byteArray)
            byteArray
        } else {
            byteArrayOf()
        }
    }

    override fun nextBytes(byteArray: ByteArray): ByteArray {
        return if (byteArray.isNotEmpty()) {
            secureRandom.nextBytes(byteArray)
            byteArray
        } else {
            byteArrayOf()
        }
    }
}

actual fun buildSecureRandom(): SecureRandom = AndroidSecureRandom(JvmSecureRandom())
