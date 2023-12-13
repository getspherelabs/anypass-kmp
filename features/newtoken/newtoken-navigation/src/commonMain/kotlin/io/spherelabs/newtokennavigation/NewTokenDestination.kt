package io.spherelabs.newtokennavigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface NewTokenDestination : ScreenProvider {
    object NewToken : NewTokenDestination
}

