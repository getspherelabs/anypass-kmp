package io.spherelabs.changepasswordimpl.presentation

import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.validation.KeyPasswordValidation

class ChangePasswordValidateMiddleware(
    private val validateKeyPassword: KeyPasswordValidation,
) : Middleware<ChangePasswordState, ChangePasswordWish> {

  override suspend fun process(
      state: ChangePasswordState,
      wish: ChangePasswordWish,
      next: suspend (ChangePasswordWish) -> Unit,
  ) {
    when (wish) {
      ChangePasswordWish.OnUpdateClicked -> {
        checkAndSameWithCurrentPassword(
            getKeyPassword = state.getKeyPassword,
            currentKeyPassword = state.currentKeyPassword,
            next,
        )
        checkAndHandleKeyPassword(state.newKeyPassword, next)
        checkAndHandleConfirmKeyPassword(state.confirmNewKeyPassword, next)
        hasKeyPasswordsSame(state.newKeyPassword, state.confirmNewKeyPassword, next)
        checkAllFields(
            getKeyPassword = state.getKeyPassword,
            currentKeyPassword = state.currentKeyPassword,
            newKeyPassword = state.newKeyPassword,
            confirmKeyPassword = state.confirmNewKeyPassword,
            next,
        )
      }
      else -> {}
    }
  }

  private suspend fun checkAndHandleKeyPassword(
      keyPassword: String,
      next: suspend (ChangePasswordWish) -> Unit,
  ) {
    val isKeyPasswordNotValid = !validateKeyPassword.execute(keyPassword)
    if (isKeyPasswordNotValid) {
      next.invoke(ChangePasswordWish.OnNewKeyPasswordFailed)
    }
  }

  private suspend fun checkAndHandleConfirmKeyPassword(
      confirmKeyPassword: String,
      next: suspend (ChangePasswordWish) -> Unit,
  ) {
    val isConfirmKeyPasswordValidation = !validateKeyPassword.execute(confirmKeyPassword)

    if (isConfirmKeyPasswordValidation) {
      next.invoke(ChangePasswordWish.OnNewKeyPasswordFailed)
    }
  }

  private suspend fun hasKeyPasswordsSame(
      keyPassword: String,
      confirmKeyPassword: String,
      next: suspend (ChangePasswordWish) -> Unit,
  ) {
    val isKeyPasswordSame = keyPassword == confirmKeyPassword

    if (!isKeyPasswordSame) {
      next.invoke(ChangePasswordWish.OnKeyPasswordSameFailed)
    }
  }

  private suspend fun checkAndSameWithCurrentPassword(
      getKeyPassword: String,
      currentKeyPassword: String,
      next: suspend (ChangePasswordWish) -> Unit,
  ) {
    if (getKeyPassword != currentKeyPassword) {
      next.invoke(ChangePasswordWish.OnCurrentKeyPasswordFailed)
    }
  }

  private suspend fun checkAllFields(
      getKeyPassword: String,
      currentKeyPassword: String,
      newKeyPassword: String,
      confirmKeyPassword: String,
      next: suspend (ChangePasswordWish) -> Unit,
  ) {
    val isKeyPasswordSame = newKeyPassword == confirmKeyPassword
    val isCurrentPasswordSame = getKeyPassword == currentKeyPassword

    if (isKeyPasswordSame && isCurrentPasswordSame) {
      next.invoke(ChangePasswordWish.SetKeyPassword(newKeyPassword))
    }
  }
}
