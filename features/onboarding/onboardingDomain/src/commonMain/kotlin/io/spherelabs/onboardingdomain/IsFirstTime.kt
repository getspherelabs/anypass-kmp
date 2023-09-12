package io.spherelabs.onboardingdomain

import io.spherelabs.data.settings.onboarding.OnboardingSetting

interface IsFirstTime {
    suspend fun execute(): Boolean
}

class DefaultIsFirstTime(
    private val onboardingSetting: OnboardingSetting
) : IsFirstTime {
    override suspend fun execute(): Boolean {
        return onboardingSetting.isFirstTime()
    }
}