package io.spherelabs.biometry

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
import kotlin.coroutines.suspendCoroutine

actual class BiometricManager(
    private val context: Context,
) {
    actual suspend fun biometricAuthentication(
        title: String,
        description: String,
        failureContext: String,
        isDeviceAllowed: Boolean,
        callback: (Result<Boolean>) -> Unit,
    ): Boolean {
        return suspendCoroutine { _ ->
            var resumed = false

            val fragmentActivity = context as FragmentActivity
            val executor = ContextCompat.getMainExecutor(fragmentActivity)

            val biometricPrompt = BiometricPrompt(
                fragmentActivity,
                executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(
                        errorCode: Int,
                        errString: CharSequence,
                    ) {
                        super.onAuthenticationError(errorCode, errString)
                        if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON ||
                            errorCode == BiometricPrompt.ERROR_USER_CANCELED
                        ) {
                            callback.invoke(Result.success(false))
                        } else {
                            callback.invoke(Result.failure(Exception(errString.toString())))
                        }
                    }
                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult,
                    ) {
                        super.onAuthenticationSucceeded(result)
                        callback.invoke(Result.success(true))
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        callback.invoke(Result.failure(Exception("Auth failed")))
                    }
                },
            )

            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(title)
                .setSubtitle(description)
                .setAllowedAuthenticators(BIOMETRIC_STRONG or BIOMETRIC_WEAK)
                .setNegativeButtonText("Cancel")
                .build()

            biometricPrompt.authenticate(promptInfo)

        }
    }

    actual fun hasBiometricCapabilities(): Boolean {
        val manager: BiometricManager = BiometricManager.from(context)
        return manager.canAuthenticate(BIOMETRIC_WEAK) == BiometricManager.BIOMETRIC_SUCCESS
    }
}
