package io.spherelabs.onboardingdomain.usecase

import io.spherelabs.data.settings.onboarding.OnboardingSetting

interface IsFirstTimeUseCase {
  suspend fun execute(): Boolean
}

class DefaultIsFirstTimeUseCase(private val onboardingSetting: OnboardingSetting) :
    IsFirstTimeUseCase {
  override suspend fun execute(): Boolean {
    return onboardingSetting.isFirstTime()
  }
}
