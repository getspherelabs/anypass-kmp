package passwordhealthimpl.presentation

import domain.usecase.GetPasswordHealthUseCase
import domain.usecase.GetPasswordProgressUseCase
import domain.usecase.GetPasswordStatsUseCase
import io.spherelabs.common.exception.NotAvailableException
import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.meteorlogger.log
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class PasswordHealthMiddleware(
    private val getPasswordStatsUseCase: GetPasswordStatsUseCase,
    private val getPasswordHealthUseCase: GetPasswordHealthUseCase,
    private val getPasswordProgressUseCase: GetPasswordProgressUseCase,
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
            PasswordHealthWish.StartLoadingPasswords -> {
                handlePasswords(next)
            }
            PasswordHealthWish.StartLoadingPasswordProgress -> {
                handlePasswordProgress(next)
            }

            else -> {}
        }
    }

    private suspend fun handlePasswordStats(next: suspend (PasswordHealthWish) -> Unit) {
        getPasswordStatsUseCase.execute()
            .collectLatest { newData ->
                next.invoke(PasswordHealthWish.LoadedPasswordStats(newData))
            }
    }

    private suspend fun handlePasswords(next: suspend (PasswordHealthWish) -> Unit) {
        getPasswordHealthUseCase.execute()
            .catch { exception ->
                if (exception is NotAvailableException) {
                    next.invoke(PasswordHealthWish.NotAvailablePasswords(exception.message))
                }
            }.collectLatest { newData ->
                log("new data is $newData")
                next.invoke(PasswordHealthWish.LoadedPasswords(newData))
            }
    }

    private suspend fun handlePasswordProgress(next: suspend (PasswordHealthWish) -> Unit) {
        getPasswordProgressUseCase.execute().collectLatest {

            next.invoke(PasswordHealthWish.LoadedPasswordProgress(it))
        }
    }
}
