package io.spherelabs.onboardingapi.domain.usecase

interface IsFirstTimeUseCase {
  suspend fun execute(): Boolean
}
