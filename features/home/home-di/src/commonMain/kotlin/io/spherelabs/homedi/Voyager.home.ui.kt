package io.spherelabs.homedi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.homeimpl.ui.HomeScreen
import io.spherelabs.homenavigation.HomeSharedScreen


val homeScreenUiModule = screenModule {
    register<HomeSharedScreen.HomeScreen> {
        HomeScreen()
    }
}
