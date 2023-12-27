package io.spherelabs.passphraseimpl.presentation

import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.passphraseapi.domain.usecase.GetFingerprintStatusUseCase
import io.spherelabs.passphraseapi.domain.usecase.GetKeyPasswordUseCase
import io.spherelabs.passphraseapi.domain.usecase.IsPasswordExistUseCase

class MasterPasswordMiddleware(
    private val isPasswordExistUseCase: IsPasswordExistUseCase,
    private val getMasterPasswordUseCase: GetKeyPasswordUseCase,
    private val getFingerprintStatusUseCase: GetFingerprintStatusUseCase,
) : Middleware<MasterPasswordState, MasterPasswordWish> {

  override suspend fun process(
      state: MasterPasswordState,
      wish: MasterPasswordWish,
      next: suspend (MasterPasswordWish) -> Unit,
  ) {
    when (wish) {
      MasterPasswordWish.CheckMasterPassword -> {
        handlePasswordExisted(next)
      }
      MasterPasswordWish.SubmitClicked -> {
        runCatching {
          if (state.isExistPassword) {
            val password = getMasterPasswordUseCase.execute()
            println("Key password is $password")
            if (state.password == password) {
              next.invoke(MasterPasswordWish.NavigateToHome)
            } else {
              next.invoke(MasterPasswordWish.IsNotMatched("Key password is not match!"))
            }
          }
        }
      }
      else -> {}
    }
  }

  private suspend fun handlePasswordExisted(next: suspend (MasterPasswordWish) -> Unit) {
    runCatching { isPasswordExistUseCase.execute() }
        .onSuccess { result ->
          if (result) {
            next.invoke(MasterPasswordWish.PasswordExisted)
            handleFingerprint(next)
          }
        }
  }

  private suspend fun handleFingerprint(
      next: suspend (MasterPasswordWish) -> Unit,
  ) {
    runCatching { getFingerprintStatusUseCase.execute() }
        .onSuccess { isEnabled ->
          if (isEnabled) {
            next.invoke(MasterPasswordWish.GetFingerprint(isEnabled))
          }
        }
  }
}
