package io.spherelabs.addnewpassworddi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.addnewpasswordimpl.ui.AddNewLoginScreen
import io.spherelabs.addnewpasswordimpl.ui.AddNewPasswordScreen
import io.spherelabs.navigationapi.AddNewLoginDestination
import io.spherelabs.navigationapi.AddNewPasswordDestination

val addNewPasswordUiModule = screenModule {
    register<AddNewPasswordDestination.AddNewPasswordScreen> {
        AddNewPasswordScreen()
    }
    register<AddNewPasswordDestination.Back> { provider ->
        AddNewPasswordScreen(provider.password)
    }
}

val addNewLoginUiModule = screenModule {
    register<AddNewLoginDestination.AddNewLogin> {
        AddNewLoginScreen()
    }
}
