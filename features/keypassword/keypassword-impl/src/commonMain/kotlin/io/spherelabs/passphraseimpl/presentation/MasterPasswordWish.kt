package io.spherelabs.passphraseimpl.presentation

sealed interface MasterPasswordWish {
  data class OnMasterPasswordChanged(val password: String) : MasterPasswordWish

  data class OnPasswordCellChanged(val password: String) : MasterPasswordWish

  object CheckMasterPassword : MasterPasswordWish

  data class SetPasswordFailure(val message: String) : MasterPasswordWish

  object PasswordExisted : MasterPasswordWish

  object ClearPassword : MasterPasswordWish

  object SubmitClicked : MasterPasswordWish

  object NavigateToHome : MasterPasswordWish

  data class GetFingerprint(val isEnabled: Boolean) : MasterPasswordWish

  data class IsNotMatched(val message: String) : MasterPasswordWish
}
