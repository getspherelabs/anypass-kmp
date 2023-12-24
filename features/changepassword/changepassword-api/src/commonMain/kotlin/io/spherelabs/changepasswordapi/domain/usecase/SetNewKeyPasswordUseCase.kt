package io.spherelabs.changepasswordapi.domain.usecase

interface SetNewKeyPasswordUseCase {
  suspend fun execute(newKeyPassword: String)
}
