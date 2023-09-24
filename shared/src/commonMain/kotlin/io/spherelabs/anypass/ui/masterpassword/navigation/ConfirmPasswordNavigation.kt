package io.spherelabs.anypass.ui.masterpassword.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.anypass.ui.masterpassword.ConfirmPassword
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateToPassword() {
    this.navigateTo(Route.InputPassword)
}

fun NavHostScope<Route>.passwordScreen(
    navigateToHome: () -> Unit
) {
    this.composable<Route.InputPassword> {
        ConfirmPassword {
            navigateToHome.invoke()
        }
    }
}