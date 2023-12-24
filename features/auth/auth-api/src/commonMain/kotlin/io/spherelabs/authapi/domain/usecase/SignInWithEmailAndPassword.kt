package io.spherelabs.authapi.domain.usecase

import io.spherelabs.authapi.model.AuthenticationResult

interface SignInWithEmailAndPassword {
  suspend fun execute(email: String, password: String): Result<AuthenticationResult>
}
