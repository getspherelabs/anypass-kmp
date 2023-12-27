package io.spherelabs.changepassworddi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.changepasswordimpl.ui.ChangePasswordScreen
import io.spherelabs.navigationapi.ChangePasswordDestination

val changePasswordUiModule = screenModule {
    register<ChangePasswordDestination.ChangePassword> {
        ChangePasswordScreen()
    }
}
