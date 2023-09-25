package io.spherelabs.masterpasswordpresentation

sealed interface MasterPasswordWish {
  data class OnMasterPasswordChanged(val password: String) : MasterPasswordWish

  data class OnConfirmPasswordChanged(val confirmPassword: String) : MasterPasswordWish

  data class OnPasswordCellChanged(val password: String) : MasterPasswordWish

  data class OnConfirmPasswordCellChanged(val confirmPassword: String) : MasterPasswordWish

  data class SetMasterPassword(val password: String) : MasterPasswordWish

  object CheckMasterPassword : MasterPasswordWish

  object SetPasswordSuccessFully : MasterPasswordWish

  data class SetPasswordFailure(val message: String) : MasterPasswordWish

  object PasswordExisted : MasterPasswordWish

  object ClearPassword : MasterPasswordWish

  object SubmitClicked : MasterPasswordWish
}
