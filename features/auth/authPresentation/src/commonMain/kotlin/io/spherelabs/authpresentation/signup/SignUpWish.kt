package io.spherelabs.authpresentation.signup

sealed interface SignUpWish {
  data class OnEmailChanged(val email: String) : SignUpWish

  data class OnNameChanged(val name: String) : SignUpWish

  data class OnPasswordChanged(val password: String) : SignUpWish

  object OnSignUpClick : SignUpWish

  object TogglePasswordVisibility : SignUpWish

  object SignUp : SignUpWish

  object OnEmailFailed : SignUpWish

  object OnPasswordFailed : SignUpWish

  object OnNameFailed : SignUpWish

  data class SignUpFailure(val message: String) : SignUpWish

  object SignUpSuccess : SignUpWish

  object Back : SignUpWish
}
