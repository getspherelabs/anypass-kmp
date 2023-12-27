@file:OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)

package io.spherelabs.firebase

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRAuthDataResult
import cocoapods.FirebaseAuth.FIRUser
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.*
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

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun logout(): Result<Boolean> {
        return runCatching {
            firAuth.throwError { signOut(it) }.run { true }
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
            job.completeExceptionally(error.toThrowable())
        }
    }
    return job.await() as T
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

@OptIn(ExperimentalForeignApi::class)
internal fun <T, R> T.throwError(block: T.(errorPointer: CPointer<ObjCObjectVar<NSError?>>) -> R): R {
    memScoped {
        val errorPointer: CPointer<ObjCObjectVar<NSError?>> = alloc<ObjCObjectVar<NSError?>>().ptr
        val result = block(errorPointer)
        val error: NSError? = errorPointer.pointed.value
        if (error != null) {
            throw error.toThrowable()
        }
        return result
    }
}


fun NSError.toThrowable(): Throwable {
    println(localizedDescription)
    return Throwable(localizedDescription)
}
