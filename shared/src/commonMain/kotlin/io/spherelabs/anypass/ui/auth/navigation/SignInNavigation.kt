package io.spherelabs.anypass.ui.auth.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.anypass.ui.auth.SignInRoute
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateSignIn() {
    this.navigateTo(Route.SignIn)
}

fun NavHostScope<Route>.signInScreen(
    navigateToSignUp: () -> Unit,
    navigateToConfirmPassword: () -> Unit
) {
    this.composable<Route.SignIn> {
        SignInRoute(
            navigateToSignUp = { navigateToSignUp.invoke() },
            navigateToKeyPassword = { navigateToConfirmPassword.invoke() }
        )
    }
}
