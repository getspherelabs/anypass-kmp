package io.spherelabs.authenticatorimpl.presentation

sealed interface AuthenticatorEffect {
    data class Failure(val message: String) : AuthenticatorEffect
}
