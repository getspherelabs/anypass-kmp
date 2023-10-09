package io.spherelabs.authpresentation.signup

sealed interface SignUpWish {
    data class OnEmailChanged(val email: String) : SignUpWish

    data class OnNameChanged(val name: String) : SignUpWish
    data class OnPasswordChanged(val password: String) : SignUpWish
    data class OnKeyPasswordChanged(val password: String) : SignUpWish
    data class OnConfirmKeyPasswordChanged(val confirmPassword: String) : SignUpWish
    object OnSignUpClick : SignUpWish

    object TogglePasswordVisibility : SignUpWish
    object ToggleKeyPasswordVisibility : SignUpWish
    object ToggleConfirmKeyPasswordVisibility : SignUpWish
    object SignUp : SignUpWish

    object OnEmailFailed : SignUpWish

    object OnPasswordFailed : SignUpWish

    object OnNameFailed : SignUpWish
    object OnKeyPasswordFailed : SignUpWish
    object OnConfirmKeyPasswordFailed : SignUpWish
    object OnKeyPasswordSameFailed : SignUpWish
    data class SignUpFailure(val message: String) : SignUpWish

    object SignUpSuccess : SignUpWish

    object Back : SignUpWish
    data class OnLoadingChanged(val loading: Boolean) : SignUpWish
}
