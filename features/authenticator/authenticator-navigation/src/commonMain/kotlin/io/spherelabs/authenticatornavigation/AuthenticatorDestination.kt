package io.spherelabs.authenticatornavigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface AuthenticatorDestination : ScreenProvider {
    object Authenticator : AuthenticatorDestination
}
