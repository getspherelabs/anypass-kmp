package io.spherelabs.authdi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.authimpl.ui.SignInScreen
import io.spherelabs.authimpl.ui.SignUpScreen
import io.spherelabs.authnavigation.AuthSharedScreen


val authScreenModule = screenModule {
    register<AuthSharedScreen.SignUpScreen> {
        SignUpScreen()
    }
    register<AuthSharedScreen.SignInScreen> {
        SignInScreen()
    }
}
