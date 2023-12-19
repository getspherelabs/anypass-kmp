package io.spherelabs.homedi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.homeimpl.ui.HomeScreen
import io.spherelabs.navigationapi.HomeDestination


val homeScreenUiModule = screenModule {
    register<HomeDestination.HomeScreen> {
        HomeScreen()
    }
}
