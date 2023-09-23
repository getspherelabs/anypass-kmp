package io.spherelabs.firebase


import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRAuthDataResult
import cocoapods.FirebaseAuth.FIRUser
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSError
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual class FirebaseUser internal constructor(private val ios: FIRUser) {
    actual val currentName: String? get() = ios.displayName
    actual val email: String? get() = ios.email
    actual val uid: String? get() = ios.uid
}

actual class AuthResult internal constructor(private val ios: FIRAuthDataResult) {
    actual val user: FirebaseUser? get() = FirebaseUser(ios.user)
}

actual class FirebaseAuthManager constructor(
    private val firAuth: FIRAuth
) {

    actual val currentUser: FirebaseUser?
        get() = firAuth.currentUser?.let {
            FirebaseUser(it)
        }

    actual suspend fun createEmailAndPassword(email: String, password: String): Result<AuthResult> {
        return try {
            println("Result low level")

            println("${AuthResult(ios = firAuth.awaitResult {
                {
                    createUserWithEmail(
                        email = email,
                        password = password,
                        completion = it
                    )
                }
            })}")
            val result = AuthResult(ios = firAuth.awaitResult {
                {
                    createUserWithEmail(
                        email = email,
                        password = password,
                        completion = it
                    )
                }
            })
            println("Result is $result")
            Result.success(result)
        } catch (e: Exception) {
            println("Exception is $e")
            Result.failure(e)
        }
    }

    actual suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Result<AuthResult> {
        return runCatching {
            AuthResult(ios = firAuth.awaitResult {
                signInWithEmail(
                    email = email,
                    password = password,
                    completion = it
                )
            })
        }
    }
}


internal suspend inline fun <T, reified R> T.awaitResult(function: T.(callback: (R?, NSError?) -> Unit) -> Unit): R {
    val job = CompletableDeferred<R?>()
    function { result, error ->
        if (error == null) {
            println("Result in job: $result")
            job.complete(result)
        } else {
            job.completeExceptionally(error.toThrowable())
            println("Await result is ${error.toThrowable()}")
        }
    }
    return job.await() as R
}

internal suspend inline fun <T> T.await(function: T.(callback: (NSError?) -> Unit) -> Unit) {
    val job = CompletableDeferred<Unit>()
    function { error ->
        if (error == null) {
            job.complete(Unit)
        } else {
            job.completeExceptionally(error.toThrowable())
        }
    }
    job.await()
}

fun NSError.toThrowable(): Throwable {
    println(localizedDescription)
    return Throwable(localizedDescription)
}
