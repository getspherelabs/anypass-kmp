package io.spherelabs.passwordhistorydi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.navigationapi.PasswordHistoryDestination
import io.spherelabs.passwordhistoryimpl.ui.PasswordHistoryScreen

val passwordHistoryUiModule = screenModule {
    register<PasswordHistoryDestination.PasswordHistory> {
        PasswordHistoryScreen()
    }
}
