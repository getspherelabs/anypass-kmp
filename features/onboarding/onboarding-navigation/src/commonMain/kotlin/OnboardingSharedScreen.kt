import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface OnboardingSharedScreen : ScreenProvider {
    object OnboardingScreen : OnboardingSharedScreen
}
