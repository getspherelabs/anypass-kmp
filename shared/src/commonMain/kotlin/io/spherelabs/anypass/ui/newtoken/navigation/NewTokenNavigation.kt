package io.spherelabs.anypass.ui.newtoken.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.anypass.ui.newtoken.NewTokenRoute
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateToNewToken() {
    this.navigateTo(Route.NewToken)
}

fun NavHostScope<Route>.newTokenScreen(
    navigateToBack: () -> Unit,
) {
    this.composable<Route.NewToken> {
        NewTokenRoute(navigateToBack = navigateToBack)
    }
}
