package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface PasswordHistoryDestination : Destination {
    object PasswordHistory : PasswordHistoryDestination
}
