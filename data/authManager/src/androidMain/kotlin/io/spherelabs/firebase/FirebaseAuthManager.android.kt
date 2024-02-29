package io.spherelabs.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

actual class AuthResult internal constructor(private val android: com.google.firebase.auth.AuthResult) {
    actual val user: io.spherelabs.firebase.FirebaseUser?
        get() = android.user?.let {
            FirebaseUser(it)
        }
}

actual class FirebaseUser constructor(private val android: FirebaseUser?) {
    actual val currentName: String? get() = android?.displayName
    actual val email: String? get() = android?.email
    actual val uid: String? get() = android?.uid
}

actual class FirebaseAuthManager(
    private val firebaseAuth: FirebaseAuth,
) {
    actual val currentUser: io.spherelabs.firebase.FirebaseUser? get() = FirebaseUser(firebaseAuth.currentUser)
    actual suspend fun createEmailAndPassword(email: String, password: String): Result<AuthResult> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(AuthResult(result))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    actual suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Result<AuthResult> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            println("Result of firebase")
            Result.success(AuthResult(checkNotNull(result)))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    actual suspend fun logout(): Result<Boolean> {
        return try {
            firebaseAuth.signOut()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
