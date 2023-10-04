package io.spherelabs.accountpresentation

import io.spherelabs.accountdomain.repository.*
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest

class AccountMiddleware(
    private val getWeakPasswordSize: GetSizeOfWeakPassword,
    private val getStrongPasswordSize: GetSizeOfStrongPassword,
    private val getTotalPasswords: GetTotalPassword,
    private val openUrl: OpenUrl,
    private val setFingerPrint: SetFingerPrint,
    private val getFingerPrint: GetFingerPrint,
) : Middleware<AccountState, AccountWish> {

    override suspend fun process(
        state: AccountState,
        wish: AccountWish,
        next: suspend (AccountWish) -> Unit,
    ) {
        when (wish) {
            AccountWish.GetStartedSizeOfStrongPassword -> {
                handleStrongPassword(next)
            }

            AccountWish.GetStartedSizeOfWeakPassword -> {
                handleWeakPassword(next)
            }

            AccountWish.GetStartedTotalPassword -> {
                handleTotalPassword(next)
            }
            is AccountWish.OpenUrl -> {
                handleOpenUrl(url = wish.url, next)
            }
            AccountWish.GetStartedFingerPrint -> {
                handleGetFingerPrint(next)
            }
            is AccountWish.SetFingerPrint -> {
                handleSetFingerPrint(wish.isEnabled, next)
            }
            else -> {}
        }
    }

    private suspend inline fun handleStrongPassword(noinline next: suspend (AccountWish) -> Unit) {
        getStrongPasswordSize.execute().collectLatest { result ->
            next.invoke(AccountWish.GetSizeOfStrongPassword(result))
        }
    }

    private suspend inline fun handleWeakPassword(noinline next: suspend (AccountWish) -> Unit) {
        getWeakPasswordSize.execute().collectLatest { result ->
            next.invoke(AccountWish.GetSizeOfWeakPassword(result))
        }
    }

    private suspend inline fun handleTotalPassword(noinline next: suspend (AccountWish) -> Unit) {
        getTotalPasswords.execute().collectLatest { result ->
            next.invoke(AccountWish.GetTotalPassword(result))
        }
    }

    private suspend inline fun handleOpenUrl(
        url: String,
        noinline next: suspend (AccountWish) -> Unit,
    ) {
        runCatching {
            openUrl.execute(url)
        }.onFailure {
            val failureMsg = it.message ?: ""
            next.invoke(AccountWish.Failure(failureMsg))
        }
    }

    private suspend inline fun handleSetFingerPrint(
        isEnabled: Boolean,
        noinline next: suspend (AccountWish) -> Unit,
    ) {
        runCatching {
            setFingerPrint.execute(isEnabled)
        }.onSuccess {
            next.invoke(AccountWish.OnFingerPrintChanged(isEnabled))
        }
    }

    private suspend inline fun handleGetFingerPrint(noinline next: suspend (AccountWish) -> Unit) {
        runCatching {
            getFingerPrint.execute()
        }.onSuccess { newResult ->
            next.invoke(AccountWish.GetFingerPrint(newResult))
        }
    }
}
