package io.spherelabs.onboardingimpl.presentation

sealed interface OnboardingWish {
    object GetStarted : OnboardingWish

    object CheckFirstTime : OnboardingWish

    object GetStartedClick : OnboardingWish

    data class Failed(val message: String) : OnboardingWish

    data class IsFinished(val value: Boolean) : OnboardingWish

    data class OnLoadingChanged(val loading: Boolean) : OnboardingWish
    object OnCheckCurrentUserExist : OnboardingWish
    object OnCurrentUserExisted : OnboardingWish
}
