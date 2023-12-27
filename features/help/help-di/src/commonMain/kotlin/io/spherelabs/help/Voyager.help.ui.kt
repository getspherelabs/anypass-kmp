package io.spherelabs.help

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.help.ui.HelpScreen
import io.spherelabs.navigationapi.HelpDestination

val helpUiModule = screenModule {
    register<HelpDestination.Help> {
        HelpScreen()
    }
}
