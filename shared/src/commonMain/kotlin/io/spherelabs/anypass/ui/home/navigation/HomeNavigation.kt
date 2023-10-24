package io.spherelabs.anypass.ui.home.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.anypass.ui.home.HomeRoute
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavHostScope<Route>.homeScreen(
    navigateToAuthenticator: () -> Unit,
    navigateToMyAccount: () -> Unit,
    navigateToCreatePassword: () -> Unit,
) {
    this.composable<Route.Home> {
        HomeRoute(
            navigateToMyAccount = {
                navigateToMyAccount.invoke()
            },
            navigateToCreatePassword = {
                navigateToCreatePassword.invoke()

            },
            navigateToAuthenticator = {
                navigateToAuthenticator.invoke()
            }
        )
    }
}

fun NavigationController<Route>.navigateToHome() {
    this.navigateTo(Route.Home)
}
