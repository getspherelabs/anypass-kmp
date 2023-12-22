package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface GeneratePasswordDestination : Destination {
    object GeneratePassword : GeneratePasswordDestination
}
