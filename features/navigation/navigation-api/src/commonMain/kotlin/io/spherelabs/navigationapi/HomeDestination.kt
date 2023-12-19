package io.spherelabs.navigationapi


import io.spherelabs.navigationapi.core.Destination

sealed interface HomeDestination : Destination {
    object HomeScreen : HomeDestination
}
