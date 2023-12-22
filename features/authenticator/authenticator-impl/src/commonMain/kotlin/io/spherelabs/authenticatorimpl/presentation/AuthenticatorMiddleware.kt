package io.spherelabs.authenticatorimpl.presentation

import io.spherelabs.authenticatorapi.domain.usecase.GetOtpUseCase
import io.spherelabs.authenticatorapi.domain.usecase.GetRealTimeOneTimePassword
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest

class AuthenticatorMiddleware(
    private val getRealTimeOneTimePasswordUseCase: GetRealTimeOneTimePassword,
    private val getOtpUseCase: GetOtpUseCase,
) : Middleware<AuthenticatorState, AuthenticatorWish> {

    override suspend fun process(
        state: AuthenticatorState,
        wish: AuthenticatorWish,
        next: suspend (AuthenticatorWish) -> Unit,
    ) {
        when (wish) {
            AuthenticatorWish.GetStartedRealTimeOtp -> {
                handleGetOneTimePasswordByRealTime(state, next)
            }

            AuthenticatorWish.GetStartedAccounts -> {
                handleGetAccounts(next)
            }

            AuthenticatorWish.OnCancellationOtp -> {
                getRealTimeOneTimePasswordUseCase.cancel()
            }

            else -> {}
        }
    }

    private suspend fun handleGetOneTimePasswordByRealTime(
        state: AuthenticatorState,
        next: suspend (AuthenticatorWish) -> Unit,
    ) {
        getRealTimeOneTimePasswordUseCase.execute { result ->
            next.invoke(AuthenticatorWish.GetOneTimePassword(result))
        }
    }

    private suspend fun handleGetAccounts(next: suspend (AuthenticatorWish) -> Unit) {
        getOtpUseCase.execute()
            .catch { newFailureMessage ->
                next.invoke(
                    AuthenticatorWish.Failure(
                        newFailureMessage.message ?: "Error is occurred.",
                    ),
                )
            }
            .collectLatest { newData ->
                next.invoke(AuthenticatorWish.GetAccounts(newData))
            }
    }
}
