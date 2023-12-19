package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface KeyPasswordDestination : Destination {
    object KeyPassword : KeyPasswordDestination
}
