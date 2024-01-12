package io.spherelabs.common

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalContracts::class)
sealed class Option<out L, out R> {
    data class Left<L>(val value: L) : Option<L, Nothing>()
    data class Right<R>(val value: R) : Option<Nothing, R>()

    public fun isLeft(): Boolean {
        contract {
            returns(true) implies (this@Option is Left<L>)
            returns(false) implies (this@Option is Right<R>)
        }
        return this@Option is Left<L>
    }

    public fun isRight(): Boolean {
        contract {
            returns(true) implies (this@Option is Right<R>)
            returns(false) implies (this@Option is Left<L>)
        }
        return this@Option is Right<R>
    }

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
