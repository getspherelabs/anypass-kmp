package io.spherelabs.authimpl.presentation.signin

sealed interface SignInEffect {
    data class Failure(val message: String) : SignInEffect
    object CreateNew : SignInEffect
    object KeyPassword : SignInEffect
}
