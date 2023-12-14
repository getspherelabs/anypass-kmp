package io.spherelabs.addnewpassworddi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.addnewpasswordimpl.ui.AddNewPasswordScreen
import io.spherelabs.addnewpasswordnavigation.AddNewPasswordSharedScreen

val addNewPasswordUiModule = screenModule {
    register<AddNewPasswordSharedScreen.AddNewPasswordScreen> {
        AddNewPasswordScreen()
    }
    register<AddNewPasswordSharedScreen.Back> { provider ->
        AddNewPasswordScreen(provider.password)
    }
}
