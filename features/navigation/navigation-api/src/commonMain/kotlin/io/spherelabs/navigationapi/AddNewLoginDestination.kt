package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface AddNewLoginDestination : Destination {
    object AddNewLogin : AddNewLoginDestination
}
