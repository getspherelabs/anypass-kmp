package io.spherelabs.authenticatordomain.usecase

import io.spherelabs.authenticatordomain.repository.AuthenticatorRepository

interface IncrementCounterUseCase {
    suspend fun execute(id: String)
}

class DefaultIncrementCounterUseCase(
    private val repository: AuthenticatorRepository,
) : IncrementCounterUseCase {

    override suspend fun execute(id: String) {
        repository.incrementCounter(id)
    }
}
