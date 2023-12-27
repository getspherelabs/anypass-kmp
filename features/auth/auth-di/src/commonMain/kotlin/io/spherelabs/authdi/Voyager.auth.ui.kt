package io.spherelabs.authdi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.authimpl.ui.SignInScreen
import io.spherelabs.authimpl.ui.SignUpScreen
import io.spherelabs.navigationapi.AuthDestination


val authScreenModule = screenModule {
    register<AuthDestination.SignUp> {
        SignUpScreen()
    }
    register<AuthDestination.SignIn> {
        SignInScreen()
    }
}
