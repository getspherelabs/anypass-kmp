package io.spherelabs.onboardingdomain

import io.spherelabs.data.settings.onboarding.OnboardingSetting


interface SetIsFirstTime {
    suspend fun execute(value: Boolean)
}

class DefaultSetIsFirstTime(
    private val onboardingSetting: OnboardingSetting
) : SetIsFirstTime {

    override suspend fun execute(value: Boolean) {
        onboardingSetting.setIsFirstTime(value)
    }
}