package io.spherelabs.authapi.domain.usecase

interface SetKeyPasswordUseCase {
  suspend fun execute(value: String)
}
