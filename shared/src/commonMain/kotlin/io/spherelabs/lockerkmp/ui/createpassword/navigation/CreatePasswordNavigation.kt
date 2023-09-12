package io.spherelabs.lockerkmp.ui.createpassword.navigation

import io.spherelabs.lockerkmp.navigation.Route
import io.spherelabs.lockerkmp.ui.createpassword.CreatePassword
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateToCreatePassword() {
    this.navigateTo(Route.CreatePassword)
}

fun NavHostScope<Route>.createPasswordScreen(
    navigateToHome: () -> Unit
) {
    this.composable<Route.CreatePassword> {
        CreatePassword()
    }
}