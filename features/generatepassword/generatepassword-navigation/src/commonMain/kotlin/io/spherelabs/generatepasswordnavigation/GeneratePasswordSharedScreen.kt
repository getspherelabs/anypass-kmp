package io.spherelabs.generatepasswordnavigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface GeneratePasswordSharedScreen : ScreenProvider {
    object GeneratePasswordScreen : GeneratePasswordSharedScreen
}
