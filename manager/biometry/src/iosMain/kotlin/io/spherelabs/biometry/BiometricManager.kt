package io.spherelabs.biometry

actual class BiometricManager {
    actual suspend fun biometricAuthentication(
        title: String,
        description: String,
        failureContext: String,
        isDeviceAllowed: Boolean,
        callback: (Result<Boolean>) -> Unit
    ): Boolean {
        return false
    }

    actual fun hasBiometricCapabilities(): Boolean {
        return false
    }
}