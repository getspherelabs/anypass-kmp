package io.spherelabs.homenavigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface HomeSharedScreen : ScreenProvider {
    object HomeScreen : HomeSharedScreen
}
