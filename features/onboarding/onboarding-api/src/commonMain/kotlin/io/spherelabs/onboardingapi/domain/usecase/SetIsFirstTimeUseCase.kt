package io.spherelabs.onboardingapi.domain.usecase

interface SetIsFirstTimeUseCase {
    suspend fun execute(value: Boolean)
}
