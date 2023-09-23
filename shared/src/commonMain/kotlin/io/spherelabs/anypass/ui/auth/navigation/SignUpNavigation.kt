package io.spherelabs.anypass.ui.auth.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.anypass.ui.auth.SignUpRoute
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateSignUp() {
    this.navigateTo(Route.SignUp)
}

fun NavHostScope<Route>.signUpScreen(
    navigateToSignIn: () -> Unit,
    navigateToConfirmPassword: () -> Unit
) {
    this.composable<Route.SignUp> {
        SignUpRoute(
            navigateToBack = { navigateToSignIn.invoke() },
            navigateToAddPrivatePassword = { navigateToConfirmPassword.invoke() }
        )
    }
}