package io.spherelabs.anypass.ui.authenticator.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.anypass.ui.authenticator.AuthenticatorRoute
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateToAuthenticator() {
    this.navigateTo(Route.Authenticator)
}

fun NavHostScope<Route>.authenticatorScreen(
    navigateToNewToken: () -> Unit,
    navigateToBack: () -> Unit,
) {
    this.composable<Route.Authenticator> {
        AuthenticatorRoute(navigateToNewToken = navigateToNewToken)

    }
}
