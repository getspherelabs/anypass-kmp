package io.spherelabs.biometry

import androidx.compose.runtime.Composable

fun interface BiometryAuthenticatorFactory {
    fun createBiometryAuthenticator(): BiometricManager
}

@Composable
expect fun rememberBiometricManager(): BiometryAuthenticatorFactory