package io.spherelabs.accountdi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.accountimpl.ui.AccountScreen as AccountUiScreen
import io.spherelabs.navigationapi.AccountDestination

val accountUiModule = screenModule {
    register<AccountDestination.Account> {
        AccountUiScreen()
    }
}
