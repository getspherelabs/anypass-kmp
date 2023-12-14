package io.spherelabs.authenticatorimpl.domain.usecase

import io.spherelabs.authenticatorapi.domain.repository.AuthenticatorRepository
import io.spherelabs.authenticatorapi.domain.usecase.IncrementCounterUseCase

class DefaultIncrementCounterUseCase(
    private val repository: AuthenticatorRepository,
) : IncrementCounterUseCase {

    override suspend fun execute(id: String) {
        repository.incrementCounter(id)
    }
}
