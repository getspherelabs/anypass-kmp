package io.spherelabs.authimpl.domain

import io.spherelabs.authapi.domain.usecase.SignInWithEmailAndPassword
import io.spherelabs.authapi.model.AuthenticationResult
import io.spherelabs.firebase.FirebaseAuthManager

class DefaultSignInWithEmailAndPasswordUseCase(private val authManager: FirebaseAuthManager) :
    SignInWithEmailAndPassword {
    override suspend fun execute(email: String, password: String): Result<AuthenticationResult> {
        return runCatching {
            authManager.signInWithEmailAndPassword(email, password)
        }.fold(
            onSuccess = { Result.success(AuthenticationResult.Success) },
            onFailure = { Result.failure(it) },
        )
    }
}
