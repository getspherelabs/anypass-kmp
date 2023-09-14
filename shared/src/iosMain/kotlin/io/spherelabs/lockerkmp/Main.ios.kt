package io.spherelabs.lockerkmp

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import io.spherelabs.lockerkmp.ui.LockerApp
import io.spherelabs.navigation.Finisher
import io.spherelabs.navigation.LocalAppFinisher

fun MainViewController() = ComposeUIViewController {
    CompositionLocalProvider(LocalAppFinisher provides iosAppFinisher()) {
        LockerApp()
    }

}

fun iosAppFinisher():  Finisher{
    return object : Finisher {
        override fun finish() {
            // no-op
        }
    }
}