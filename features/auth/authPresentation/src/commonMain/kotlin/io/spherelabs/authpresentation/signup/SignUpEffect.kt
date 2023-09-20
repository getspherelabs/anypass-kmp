package io.spherelabs.authpresentation.signup

sealed interface SignUpEffect {
    data class Failure(val message: String) : SignUpEffect
    object SignIn : SignUpEffect
    object AddPrivatePassword : SignUpEffect
}
