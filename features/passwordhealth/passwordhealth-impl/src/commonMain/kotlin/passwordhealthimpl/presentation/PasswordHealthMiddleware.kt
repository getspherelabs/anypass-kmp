package passwordhealthimpl.presentation

import domain.usecase.GetPasswordHealthUseCase
import domain.usecase.GetPasswordStatsUseCase
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest

class PasswordHealthMiddleware(
    private val getPasswordStatsUseCase: GetPasswordStatsUseCase,
    private val getPasswordHealthUseCase: GetPasswordHealthUseCase,
) : Middleware<PasswordHealthState, PasswordHealthWish> {

    override suspend fun process(
        state: PasswordHealthState,
        wish: PasswordHealthWish,
        next: suspend (PasswordHealthWish) -> Unit,
    ) {
        when (wish) {
            PasswordHealthWish.StartLoadingPasswordStats -> {
                handlePasswordStats(next)
            }

            else -> {}
        }
    }

    private suspend fun handlePasswordStats(next: suspend (PasswordHealthWish) -> Unit) {
        getPasswordStatsUseCase.execute().collectLatest { newData ->
            next.invoke(PasswordHealthWish.PasswordStatsChanged(newData))
        }
    }
}
