package io.spherelabs.help

import io.spherelabs.helpstore.DefaultHelpStoreManager
import io.spherelabs.helpstore.HelpStoreManager
import org.koin.dsl.module

val helpStoreModule = module {
    single<HelpStoreManager> { DefaultHelpStoreManager() }
}
