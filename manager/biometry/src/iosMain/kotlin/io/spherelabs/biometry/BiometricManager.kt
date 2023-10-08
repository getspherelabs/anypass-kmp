@file:OptIn(ExperimentalForeignApi::class)

package io.spherelabs.biometry

import kotlinx.cinterop.*
import platform.Foundation.NSError
import platform.LocalAuthentication.LAContext
import platform.LocalAuthentication.LAPolicyDeviceOwnerAuthentication
import platform.LocalAuthentication.LAPolicyDeviceOwnerAuthenticationWithBiometrics

actual class BiometricManager {
    actual suspend fun biometricAuthentication(
        title: String,
        description: String,
        failureContext: String,
        isDeviceAllowed: Boolean,
        callback: (Result<Boolean>) -> Unit,
    ): Boolean {
        val laContext = LAContext()
        laContext.setLocalizedFallbackTitle(failureContext)

        val policy = if (isDeviceAllowed) {
            LAPolicyDeviceOwnerAuthentication
        } else {
            LAPolicyDeviceOwnerAuthenticationWithBiometrics
        }

        val (canEvaluate: Boolean?, error: NSError?) = memScoped {
            val p = alloc<ObjCObjectVar<NSError?>>()
            val canEvaluate: Boolean? = runCatching {
                laContext.canEvaluatePolicy(policy, error = p.ptr)
            }.getOrNull()
            canEvaluate to p.value
        }

        if (error != null) throw error.toException()
        if (canEvaluate == null) return false

        return awaitResult { block ->
            laContext.evaluatePolicy(
                policy = policy,
                localizedReason = description,
                reply = mainContinuation { result: Boolean, error: NSError? ->
                    block(result, error)
                },
            )
        }
    }

    actual fun hasBiometricCapabilities(): Boolean {
        val laContext = LAContext()
        return laContext.canEvaluatePolicy(
            LAPolicyDeviceOwnerAuthenticationWithBiometrics,
            error = null,
        )
    }
}
