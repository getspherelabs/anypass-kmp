package io.spherelabs.lockerkmp.ui.confirmpassword.navigation

import io.spherelabs.lockerkmp.navigation.Route
import io.spherelabs.lockerkmp.ui.confirmpassword.ConfirmPassword
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