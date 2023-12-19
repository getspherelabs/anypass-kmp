package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface AccountDestination : Destination {
    object Account : AccountDestination
}
