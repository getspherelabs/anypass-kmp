package io.spherelabs.generatepassworddi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.generatepasswordimpl.ui.GeneratePasswordScreen as GeneratePasswordUiScreen
import io.spherelabs.navigationapi.GeneratePasswordDestination

val generatePasswordUiModule = screenModule {
    register<GeneratePasswordDestination.GeneratePassword> {
        GeneratePasswordUiScreen()
    }
}
