package io.spherelabs.anycrypto.securerandom

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.usePinned
import platform.Security.SecRandomCopyBytes
import platform.Security.kSecRandomDefault

@OptIn(ExperimentalForeignApi::class)
internal class IosSecureRandom : SecureRandom {
    override fun nextBytes(size: Int): ByteArray {
        val result = ByteArray(size)

        if (size != 0) {
            result.usePinned { pin ->
                val address =  pin.addressOf(0)

                SecRandomCopyBytes(
                    kSecRandomDefault,
                    result.size.convert(),
                    address,
                )
            }
        }

        return result
    }
}

actual fun buildSecureRandom(): SecureRandom = IosSecureRandom()