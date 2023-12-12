package io.spherelabs.passphrasenavigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface KeyPasswordSharedScreen : ScreenProvider {
    object KeyPassword : KeyPasswordSharedScreen
}
