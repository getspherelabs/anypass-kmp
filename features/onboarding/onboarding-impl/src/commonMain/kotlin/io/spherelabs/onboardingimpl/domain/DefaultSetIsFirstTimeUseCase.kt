package io.spherelabs.onboardingimpl.domain

import io.spherelabs.data.settings.onboarding.OnboardingSetting
import io.spherelabs.onboardingapi.domain.SetIsFirstTimeUseCase

class DefaultSetIsFirstTimeUseCase(private val onboardingSetting: OnboardingSetting) :
    SetIsFirstTimeUseCase {

    override suspend fun execute(value: Boolean) {
        onboardingSetting.setIsFirstTime(value)
    }
}
