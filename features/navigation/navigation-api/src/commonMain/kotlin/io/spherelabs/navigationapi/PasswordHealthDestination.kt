package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface PasswordHealthDestination : Destination {
    object PasswordHealth : Destination
}
