package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface AuthenticatorDestination : Destination {
    object Authenticator : AuthenticatorDestination
}
