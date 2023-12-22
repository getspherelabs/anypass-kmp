package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface AuthDestination : Destination {
    object SignIn : AuthDestination
    object SignUp : AuthDestination
}
