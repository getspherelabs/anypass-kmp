package io.spherelabs.features.onboardingpresentation

import androidx.compose.runtime.Immutable

@Immutable
data class OnboardingState(
  val name: String = "",
  val isFirstTime: Boolean = true,
  val isLogged: Boolean = false,
  val isLoading: Boolean = false
) {
  companion object {
    val Empty = OnboardingState()
  }
}
