package io.spherelabs.passwordhistoryimpl.presentation

import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.passwordhistoryapi.usecase.ClearAllPasswordHistoryUseCase
import io.spherelabs.passwordhistoryapi.usecase.GetAllPasswordHistoryUseCase
import kotlinx.coroutines.flow.collectLatest

class PasswordHistoryMiddleware(
    private val getAllPasswordHistoryUseCase: GetAllPasswordHistoryUseCase,
    private val clearAllPasswordHistoryUseCase: ClearAllPasswordHistoryUseCase,
) : Middleware<PasswordHistoryState, PasswordHistoryWish> {
    override suspend fun process(
        state: PasswordHistoryState,
        wish: PasswordHistoryWish,
        next: suspend (PasswordHistoryWish) -> Unit,
    ) {
        when (wish) {
            PasswordHistoryWish.StartLoadingPasswordHistory -> {
                getAllPasswordHistoryUseCase.execute()
                    .collectLatest { data ->
                        next.invoke(PasswordHistoryWish.LoadedPasswordHistory(data.map { it.toUi() }))
                    }
            }
            PasswordHistoryWish.OnClearPasswordHistory -> {
                runCatching {
                    clearAllPasswordHistoryUseCase.execute()
                }.onSuccess {
                    next.invoke(PasswordHistoryWish.LoadedPasswordHistory(emptyList()))
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(PasswordHistoryWish.Failure(failureMessage))
                }

            }
            else -> {}
        }
    }
}
