package io.spherelabs.onboardingapi.domain

interface SetIsFirstTimeUseCase {
    suspend fun execute(value: Boolean)
}
