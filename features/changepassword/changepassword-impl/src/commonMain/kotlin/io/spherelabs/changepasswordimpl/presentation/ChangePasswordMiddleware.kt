package io.spherelabs.changepasswordimpl.presentation

import io.spherelabs.changepasswordapi.domain.usecase.GetCurrentKeyPasswordUseCase
import io.spherelabs.changepasswordapi.domain.usecase.SetNewKeyPasswordUseCase
import io.spherelabs.meteor.middleware.Middleware

class ChangePasswordMiddleware(
    private val getCurrentKeyPasswordUseCase: GetCurrentKeyPasswordUseCase,
    private val setCurrentKeyPasswordUseCase: SetNewKeyPasswordUseCase,
) : Middleware<ChangePasswordState, ChangePasswordWish> {

  override suspend fun process(
      state: ChangePasswordState,
      wish: ChangePasswordWish,
      next: suspend (ChangePasswordWish) -> Unit,
  ) {
    when (wish) {
      ChangePasswordWish.GetStartedCurrentKeyPassword -> {
        handleGetCurrentKeyPassword(next)
      }
      is ChangePasswordWish.SetKeyPassword -> {
        handleSetNewKeyPassword(wish.newKeyPassword, next)
      }
      else -> {}
    }
  }

  private suspend fun handleGetCurrentKeyPassword(
      next: suspend (ChangePasswordWish) -> Unit,
  ) {
    runCatching { getCurrentKeyPasswordUseCase.execute() }
        .onSuccess { newKeyPassword ->
          next.invoke(ChangePasswordWish.GetCurrentKeyPassword(newKeyPassword))
        }
        .onFailure { newException ->
          val failureMessage = newException.message ?: "Error is occurred."
          next.invoke(ChangePasswordWish.GetFailedCurrentKeyPassword(failureMessage))
        }
  }

  private suspend fun handleSetNewKeyPassword(
      newKeyPassword: String,
      next: suspend (ChangePasswordWish) -> Unit,
  ) {
    runCatching { setCurrentKeyPasswordUseCase.execute(newKeyPassword) }
        .onSuccess { next.invoke(ChangePasswordWish.UpdatedSuccessfully("Updated successfully!")) }
  }
}
