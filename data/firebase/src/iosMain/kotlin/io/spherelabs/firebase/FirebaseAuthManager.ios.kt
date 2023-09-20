package io.spherelabs.firebase

actual class FirebaseUser constructor() {
    actual val currentName: String? get() = ""
    actual val email: String? get() = "android.email"
    actual val uid: String? get() = "android.uid"
}

actual class AuthResult internal constructor() {
    actual val user: io.spherelabs.firebase.FirebaseUser? get() = null
}

actual class FirebaseAuthManager() {

    actual val currentUser: FirebaseUser? get() = TODO()

    actual suspend fun createEmailAndPassword(email: String, password: String): Result<AuthResult> {
        TODO()
    }

    actual suspend fun signInWithEmailAndPassword(email: String, password: String): Result<AuthResult> {
        TODO()
    }
}