package io.spherelabs.biometry

import androidx.compose.runtime.Composable

@Composable
actual fun rememberBiometricManager(): BiometryAuthenticatorFactory {
    return BiometryAuthenticatorFactory { BiometricManager()}
}