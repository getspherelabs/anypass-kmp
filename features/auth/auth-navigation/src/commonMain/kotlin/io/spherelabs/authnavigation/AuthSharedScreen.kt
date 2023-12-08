package io.spherelabs.authnavigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface AuthSharedScreen : ScreenProvider {
    object SignInScreen : AuthSharedScreen
    object SignUpScreen : AuthSharedScreen
}
