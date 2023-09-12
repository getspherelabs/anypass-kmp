package io.spherelabs.biometry

expect class BiometricManager {
    suspend fun biometricAuthentication(
        title: String,
        description: String,
        failureContext: String,
        isDeviceAllowed: Boolean = false,
        callback: (Result<Boolean>) -> Unit
    ): Boolean

    fun hasBiometricCapabilities(): Boolean
}