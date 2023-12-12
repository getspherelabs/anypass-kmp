package io.spherelabs.generatepassworddi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.generatepasswordimpl.ui.GeneratePasswordScreen as GeneratePasswordUiScreen
import io.spherelabs.generatepasswordnavigation.GeneratePasswordSharedScreen

val generatePasswordUiModule = screenModule {
    register<GeneratePasswordSharedScreen.GeneratePasswordScreen> {
        GeneratePasswordUiScreen()
    }
}
