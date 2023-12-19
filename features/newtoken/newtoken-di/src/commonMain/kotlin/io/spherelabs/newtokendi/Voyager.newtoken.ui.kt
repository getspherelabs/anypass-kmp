package io.spherelabs.newtokendi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.navigationapi.NewTokenDestination
import io.spherelabs.newtokenimpl.ui.NewTokenScreen

val newTokenUiModule = screenModule {
    register<NewTokenDestination.NewToken> {
        NewTokenScreen()
    }
}
