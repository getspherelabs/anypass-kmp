package io.spherelabs.anypass.ui.onboarding.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.anypass.ui.onboarding.OnboardingRoute
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.composable

fun NavHostScope<Route>.onboardingScreen(
    navigateToPassword: () -> Unit
) {
    this.composable<Route.Onboarding> {
        OnboardingRoute {
            navigateToPassword.invoke()
        }
    }
}
