package io.spherelabs.anypass.ui.keypassword.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateToPassword() {
    this.navigateTo(Route.MasterPassword)
}

fun NavHostScope<Route>.passwordScreen(
    navigateToHome: () -> Unit
) {
    this.composable<Route.MasterPassword> {
//        KeyPasswordRoute {
//            navigateToHome.invoke()
//        }
    }
}
