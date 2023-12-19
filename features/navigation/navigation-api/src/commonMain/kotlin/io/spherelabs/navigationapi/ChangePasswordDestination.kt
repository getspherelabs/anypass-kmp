package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface ChangePasswordDestination : Destination {
    object ChangePassword : ChangePasswordDestination
}
