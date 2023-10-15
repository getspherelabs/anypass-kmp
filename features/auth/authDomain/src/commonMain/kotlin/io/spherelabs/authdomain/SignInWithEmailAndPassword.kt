package io.spherelabs.authdomain

import io.spherelabs.firebase.AuthResult
import io.spherelabs.firebase.FirebaseAuthManager

interface SignInWithEmailAndPassword {
    suspend fun execute(email: String, password: String): Result<AuthResult>
}

class DefaultSignInWithEmailAndPassword(private val authManager: FirebaseAuthManager) :
    SignInWithEmailAndPassword {
    override suspend fun execute(email: String, password: String): Result<AuthResult> {
        return authManager.signInWithEmailAndPassword(email, password)
    }
}
