package io.spherelabs.newtokendi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.newtokenimpl.ui.NewTokenScreen
import io.spherelabs.newtokennavigation.NewTokenDestination

val newTokenUiModel = screenModule {
    register<NewTokenDestination.NewToken> {
        NewTokenScreen()
    }
}
