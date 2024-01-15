package io.spherelabs.common

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.coroutines.cancellation.CancellationException


@OptIn(ExperimentalContracts::class)
sealed class Option<out L, out R> {
    data class Left<L>(val value: L) : Option<L, Nothing>()
    data class Right<R>(val value: R) : Option<Nothing, R>()


    companion object {
        inline fun <R> catch(block: () -> R): Option<Throwable, R> {
            return try {
                Right(block())
            } catch (e: CancellationException) {
                throw e
            } catch (e: Error) {
                throw e
            } catch (e: Throwable) {
                Left(e)
            }
        }
    }
}


fun <L : Throwable, R> Option<L, R>.getOrNull(): R? = when (this) {
    is Option.Right -> value
    is Option.Left -> null
}
