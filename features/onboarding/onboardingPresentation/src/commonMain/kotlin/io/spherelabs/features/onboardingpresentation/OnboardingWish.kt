package io.spherelabs.features.onboardingpresentation

sealed interface OnboardingWish {
    object GetStarted : OnboardingWish
    object CheckFirstTime : OnboardingWish
    object GetStartedClick : OnboardingWish
    data class Failed(val message: String) : OnboardingWish
    data class IsFinished(val value: Boolean) : OnboardingWish
}