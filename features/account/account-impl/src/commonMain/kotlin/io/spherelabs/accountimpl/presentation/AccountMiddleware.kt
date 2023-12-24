package io.spherelabs.accountimpl.presentation

import io.spherelabs.accountapi.domain.usecase.*
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine

class AccountMiddleware(
    private val getWeakPasswordSize: GetSizeOfWeakPasswordUseCase,
    private val getStrongPasswordSize: GetSizeOfStrongPasswordUseCase,
    private val getTotalPasswordsUseCase: GetTotalPasswordUseCase,
    private val openUrlUseCase: OpenUrlUseCase,
    private val setFingerPrintUseCase: SetFingerPrintUseCase,
    private val getFingerPrintUseCase: GetFingerPrintUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase,
) : Middleware<AccountState, AccountWish> {

    override suspend fun process(
        state: AccountState,
        wish: AccountWish,
        next: suspend (AccountWish) -> Unit,
    ) {
        when (wish) {
            is AccountWish.OpenUrl -> {
                handleOpenUrl(url = wish.url, next)
            }

            AccountWish.GetStartedFingerPrint -> {
                handleGetFingerPrint(next)
            }

            is AccountWish.SetFingerPrint -> {
                handleSetFingerPrint(wish.isEnabled, next)
            }

            AccountWish.GetAccount -> {
                combine(
                    getTotalPasswordsUseCase.execute(),
                    getStrongPasswordSize.execute(),
                    getWeakPasswordSize.execute(),
                    getUserUseCase.execute(),
                ) { totalPassword, strongPassword, weakPassword, newUser ->
                    next.invoke(AccountWish.GetTotalPassword(totalPassword))
                    next.invoke(AccountWish.GetSizeOfStrongPassword(strongPassword))
                    next.invoke(AccountWish.GetSizeOfWeakPassword(weakPassword))
                    next.invoke(AccountWish.GetUser(newUser))
                }
                    .collect()
            }
            AccountWish.Logout -> {
                val result = logoutUseCase.execute()

                result.fold(
                    onSuccess = { isLogout ->
                        next.invoke(AccountWish.LogoutChanged(isLogout))
                    },
                    onFailure = {
                        val failureMsg = it.message ?: "Error is occurred."
                        next.invoke(AccountWish.Failure(failureMsg))
                    },
                )
            }

            else -> {}
        }
    }

    private suspend inline fun handleOpenUrl(
        url: String,
        noinline next: suspend (AccountWish) -> Unit,
    ) {
        runCatching { openUrlUseCase.execute(url) }
            .onFailure {
                val failureMsg = it.message ?: ""
                next.invoke(AccountWish.Failure(failureMsg))
            }
    }

    private suspend inline fun handleSetFingerPrint(
        isEnabled: Boolean,
        noinline next: suspend (AccountWish) -> Unit,
    ) {
        runCatching { setFingerPrintUseCase.execute(isEnabled) }
            .onSuccess { next.invoke(AccountWish.OnFingerPrintChanged(isEnabled)) }
    }

    private suspend inline fun handleGetFingerPrint(noinline next: suspend (AccountWish) -> Unit) {
        runCatching { getFingerPrintUseCase.execute() }
            .onSuccess { newResult -> next.invoke(AccountWish.GetFingerPrint(newResult)) }
    }
}
