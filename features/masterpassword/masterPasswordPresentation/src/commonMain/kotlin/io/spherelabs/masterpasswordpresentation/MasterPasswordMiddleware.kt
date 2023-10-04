package io.spherelabs.masterpasswordpresentation

import io.spherelabs.masterpassworddomain.GetMasterPassword
import io.spherelabs.masterpassworddomain.IsPasswordExist
import io.spherelabs.masterpassworddomain.SetMasterPassword
import io.spherelabs.meteor.middleware.Middleware

class MasterPasswordMiddleware(
  private val setMasterPassword: SetMasterPassword,
  private val isPasswordExist: IsPasswordExist,
  private val getMasterPassword: GetMasterPassword,
) : Middleware<MasterPasswordState, MasterPasswordWish> {

  override suspend fun process(
    state: MasterPasswordState,
    wish: MasterPasswordWish,
    next: suspend (MasterPasswordWish) -> Unit,
  ) {
    when (wish) {
      MasterPasswordWish.CheckMasterPassword -> {
        runCatching { isPasswordExist.execute() }
          .onSuccess { result ->
            if (result) {
              next.invoke(MasterPasswordWish.PasswordExisted)
            }
          }
      }
      is MasterPasswordWish.SetMasterPassword -> {
        handlePassword(wish.password, next)
      }
      MasterPasswordWish.SubmitClicked -> {
        runCatching {
          if (state.isExistPassword) {
            val password = getMasterPassword.execute()

            println("Existed password is $password")
            if (state.password == password) {
              next.invoke(MasterPasswordWish.NavigateToHome)
            }
          } else {
            next.invoke(MasterPasswordWish.SetMasterPassword(state.password))
          }
        }
      }
      else -> {}
    }
  }

  private suspend fun handlePassword(
    password: String,
    next: suspend (MasterPasswordWish) -> Unit,
  ) {
    runCatching { setMasterPassword.execute(password) }
      .onSuccess { next.invoke(MasterPasswordWish.SetPasswordSuccessFully) }
      .onFailure {
        val failureMessage = it.message ?: "Error is occurred"
        next.invoke(MasterPasswordWish.SetPasswordFailure(failureMessage))
      }
  }
}
