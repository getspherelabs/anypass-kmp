package io.spherelabs.authenticatordi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.authenticatorimpl.ui.AuthenticatorScreen
import io.spherelabs.navigationapi.AuthenticatorDestination

val authenticatorUiModule = screenModule {
    register<AuthenticatorDestination.Authenticator> {
        AuthenticatorScreen()
    }
}
