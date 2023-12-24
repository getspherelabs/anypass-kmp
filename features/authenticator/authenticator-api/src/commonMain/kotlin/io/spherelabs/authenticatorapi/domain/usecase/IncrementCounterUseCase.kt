package io.spherelabs.authenticatorapi.domain.usecase

interface IncrementCounterUseCase {
  suspend fun execute(id: String)
}
