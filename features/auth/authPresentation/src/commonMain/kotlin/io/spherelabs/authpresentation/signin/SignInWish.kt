package io.spherelabs.authpresentation.signin

sealed interface SignInWish {
    data class OnEmailChanged(val email: String) : SignInWish
    data class OnPasswordChanged(val password: String) : SignInWish
    object OnSignInClick : SignInWish
    object TogglePasswordVisibility : SignInWish
    object SignIn : SignInWish
    object OnEmailFailed : SignInWish
    object OnPasswordFailed : SignInWish
    data class SignInFailure(val message: String) : SignInWish
    object SignInSuccess : SignInWish
    object SignUpClick : SignInWish
    object CheckCurrentUser : SignInWish
    data class HasCurrentUser(val value: Boolean) : SignInWish
}
