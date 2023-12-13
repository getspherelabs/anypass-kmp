package io.spherelabs.changepasswordimpl.presentation

sealed interface ChangePasswordWish {
    object GetStartedCurrentKeyPassword : ChangePasswordWish
    data class GetFailedCurrentKeyPassword(val message: String) : ChangePasswordWish
    data class GetCurrentKeyPassword(val currentKeyPassword: String) : ChangePasswordWish
    data class OnCurrentKeyPasswordChanged(val currentKeyPassword: String) : ChangePasswordWish
    data class OnNewKeyPasswordChanged(val keyPassword: String) : ChangePasswordWish
    data class OnConfirmNewKeyPasswordChanged(val confirmKeyPassword: String) : ChangePasswordWish
    object OnNewKeyPasswordFailed : ChangePasswordWish
    object OnConfirmNewKeyPasswordFailed : ChangePasswordWish
    object OnCurrentKeyPasswordFailed : ChangePasswordWish
    object OnKeyPasswordSameFailed : ChangePasswordWish
    data class SetKeyPassword(val newKeyPassword: String) : ChangePasswordWish
    object OnUpdateClicked : ChangePasswordWish
    data class UpdatedSuccessfully(val message: String) : ChangePasswordWish
    object ToggleCurrentKeyPasswordVisibility : ChangePasswordWish
    object ToggleNewKeyPasswordVisibility : ChangePasswordWish
    object ToggleConfirmNewKeyPasswordVisibility : ChangePasswordWish
    object NavigateToBack : ChangePasswordWish
}
