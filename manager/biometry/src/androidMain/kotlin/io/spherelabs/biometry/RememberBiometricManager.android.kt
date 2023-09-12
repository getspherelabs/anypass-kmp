package io.spherelabs.biometry

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun rememberBiometricManager(): BiometryAuthenticatorFactory {
    val context: Context = LocalContext.current

    return remember(
        context
    ) {
        BiometryAuthenticatorFactory {
            BiometricManager(context)
        }
    }
}