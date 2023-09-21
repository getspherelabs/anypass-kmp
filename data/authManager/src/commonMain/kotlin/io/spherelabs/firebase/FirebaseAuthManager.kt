package io.spherelabs.firebase

expect class AuthResult {
    val user: FirebaseUser?
}

expect class FirebaseUser {
    val currentName: String?
    val email: String?
    val uid: String?
}


expect class FirebaseAuthManager {
    val currentUser: FirebaseUser?
    suspend fun createEmailAndPassword(email: String, password: String): Result<AuthResult>
    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<AuthResult>
}