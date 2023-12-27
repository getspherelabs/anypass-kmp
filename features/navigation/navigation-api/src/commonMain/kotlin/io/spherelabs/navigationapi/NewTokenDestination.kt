package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface NewTokenDestination : Destination {
    object NewToken : NewTokenDestination
}

