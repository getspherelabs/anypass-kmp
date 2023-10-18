package io.spherelabs.onboardingdomain.usecase

import io.spherelabs.data.settings.onboarding.OnboardingSetting

interface SetIsFirstTimeUseCase {
  suspend fun execute(value: Boolean)
}

class DefaultSetIsFirstTimeUseCase(private val onboardingSetting: OnboardingSetting) :
    SetIsFirstTimeUseCase {

  override suspend fun execute(value: Boolean) {
    onboardingSetting.setIsFirstTime(value)
  }
}
