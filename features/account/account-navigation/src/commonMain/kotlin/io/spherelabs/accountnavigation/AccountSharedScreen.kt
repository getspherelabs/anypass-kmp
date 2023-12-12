package io.spherelabs.accountnavigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface AccountSharedScreen : ScreenProvider {
    object AccountScreen : AccountSharedScreen
}
