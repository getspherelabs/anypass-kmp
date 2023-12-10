package io.spherelabs.onboardingapi.domain

interface IsFirstTimeUseCase {
    suspend fun execute(): Boolean
}
