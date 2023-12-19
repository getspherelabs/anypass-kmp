package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination

sealed interface OnboardingDestination : Destination {
    object Onboarding : OnboardingDestination
}

