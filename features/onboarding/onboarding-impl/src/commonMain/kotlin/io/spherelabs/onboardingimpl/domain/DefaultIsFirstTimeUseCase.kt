package io.spherelabs.onboardingimpl.domain

import io.spherelabs.data.settings.onboarding.OnboardingSetting
import io.spherelabs.onboardingapi.domain.usecase.IsFirstTimeUseCase


class DefaultIsFirstTimeUseCase(private val onboardingSetting: OnboardingSetting) :
    IsFirstTimeUseCase {
    override suspend fun execute(): Boolean {
        return onboardingSetting.isFirstTime()
    }
}
