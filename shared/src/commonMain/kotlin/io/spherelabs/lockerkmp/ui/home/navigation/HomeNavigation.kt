package io.spherelabs.lockerkmp.ui.home.navigation

import io.spherelabs.lockerkmp.navigation.Route
import io.spherelabs.lockerkmp.ui.confirmpassword.ConfirmPassword
import io.spherelabs.lockerkmp.ui.home.HomeScreen
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavHostScope<Route>.homeScreen(
    navigateToCreatePassword: () -> Unit
) {
    this.composable<Route.Home> {
        HomeScreen {
            navigateToCreatePassword.invoke()
        }
    }
}

fun NavigationController<Route>.navigateToHome() {
    this.navigateTo(Route.Home)
}