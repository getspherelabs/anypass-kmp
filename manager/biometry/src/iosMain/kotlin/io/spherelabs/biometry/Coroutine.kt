package io.spherelabs.biometry

import platform.Foundation.NSError
import platform.Foundation.NSThread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import kotlin.coroutines.CoroutineContext


internal suspend inline fun <T> awaitResult(crossinline block: ((T?, NSError?) -> Unit) -> Unit): T {
    return suspendCoroutine { continuation ->
        block { data, error ->
            if (data != null) {
                continuation.resume(data)
            } else {
                continuation.resumeWithException(error.toException())
            }
        }
    }
}

internal fun NSError?.toException(): Exception {
    return this?.run { Exception(description()) } ?: NullPointerException("NSError is null")
}

internal object MainRunDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) =
        NSRunLoop.mainRunLoop.performBlock {
            block.run() }
}


internal inline fun <T1, T2> mainContinuation(
    crossinline block: (T1, T2) -> Unit
): (T1, T2) -> Unit = { arg1, arg2 ->
    if (NSThread.isMainThread()) {
        block(arg1, arg2)
    } else {
        MainRunDispatcher.run {
            block(arg1, arg2)
        }
    }
}



