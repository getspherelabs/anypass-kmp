package io.spherelabs.accountdi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.accountimpl.ui.AccountScreen as AccountUiScreen
import io.spherelabs.accountnavigation.AccountSharedScreen

val accountUiModule = screenModule {
    register<AccountSharedScreen.AccountScreen> {
        AccountUiScreen()
    }
}
