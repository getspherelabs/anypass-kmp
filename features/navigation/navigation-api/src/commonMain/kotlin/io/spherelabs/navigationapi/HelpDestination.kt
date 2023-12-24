package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface HelpDestination : Destination {
    object Help : HelpDestination
}
