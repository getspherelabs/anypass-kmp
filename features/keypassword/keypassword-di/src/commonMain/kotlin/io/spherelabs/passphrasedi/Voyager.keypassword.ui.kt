package io.spherelabs.passphrasedi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.passphraseimpl.ui.KeyPasswordScreen
import io.spherelabs.passphrasenavigation.KeyPasswordSharedScreen

val keyPasswordUiModule = screenModule {
    register<KeyPasswordSharedScreen.KeyPassword> {
        KeyPasswordScreen()
    }
}
