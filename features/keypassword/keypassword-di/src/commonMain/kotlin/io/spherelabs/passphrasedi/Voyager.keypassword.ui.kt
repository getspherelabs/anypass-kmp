package io.spherelabs.passphrasedi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.navigationapi.KeyPasswordDestination
import io.spherelabs.passphraseimpl.ui.KeyPasswordScreen

val keyPasswordUiModule = screenModule {
    register<KeyPasswordDestination.KeyPassword> {
        KeyPasswordScreen()
    }
}
