package io.spherelabs.passwordhealthdi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.navigationapi.PasswordHealthDestination
import passwordhealthimpl.ui.PasswordHealthScreen

val passwordHealthUiModule = screenModule {
    register<PasswordHealthDestination.PasswordHealth> {
        PasswordHealthScreen()
    }
}
