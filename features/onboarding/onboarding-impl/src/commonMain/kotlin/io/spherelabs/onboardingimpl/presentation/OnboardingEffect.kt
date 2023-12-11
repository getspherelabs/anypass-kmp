package io.spherelabs.onboardingimpl.presentation

sealed interface OnboardingEffect {
  object SignIn : OnboardingEffect
  object SignUp : OnboardingEffect
  data class Failure(val msg: String) : OnboardingEffect
  object IsFirstTime : OnboardingEffect
  object IsFinished : OnboardingEffect
  object Home : OnboardingEffect
}
