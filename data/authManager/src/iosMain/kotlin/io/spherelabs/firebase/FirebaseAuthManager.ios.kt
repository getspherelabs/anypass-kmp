package io.spherelabs.firebase


import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRAuthDataResult
import cocoapods.FirebaseAuth.FIRUser
import kotlinx.coroutines.CompletableDeferred
import platform.Foundation.NSError

actual class FirebaseUser internal constructor(private val ios: FIRUser) {
    actual val currentName: String? get() = ios.displayName
    actual val email: String? get() = ios.email
    actual val uid: String? get() = ios.uid
}

actual class AuthResult internal constructor(private val ios: FIRAuthDataResult) {
    actual val user: FirebaseUser? get() = FirebaseUser(ios.user)
}

actual class FirebaseAuthManager constructor(
    private val firAuth: FIRAuth,
) {

    actual val currentUser: FirebaseUser?
        get() = firAuth.currentUser?.let {
            FirebaseUser(it)
        }

    actual suspend fun createEmailAndPassword(email: String, password: String): Result<AuthResult> {
        return runCatching {
            AuthResult(
                awaitResult { result ->
                    firAuth.createUserWithEmail(email, password, completion = result)
                },
            )
        }
    }

    actual suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Result<AuthResult> {
        return runCatching {
            AuthResult(
                awaitResult {
                    firAuth.signInWithEmail(
                        email = email,
                        password = password,
                        completion = it,
                    )
                },
            )
        }
    }
}

suspend inline fun <reified T> awaitResult(function: (callback: (T?, NSError?) -> Unit) -> Unit): T {
    val job = CompletableDeferred<T?>()
    function { result, error ->
        if (error == null) {
            job.complete(result)
        } else {
            job.completeExceptionally(Exception(""))
        }
    }
    return job.await() as T
}

fun NSError.toThrowable(): Throwable {
    println(localizedDescription)
    return Throwable(localizedDescription)
}
