package io.spherelabs.anypass.ui.space.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.anypass.ui.space.AccountRoute
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateSpace() {
    this.navigateTo(Route.Space)
}

fun NavHostScope<Route>.spaceScreen(
    navigateToHome: () -> Unit,
) {
    this.composable<Route.Space> {
        AccountRoute { navigateToHome.invoke() }
    }
}