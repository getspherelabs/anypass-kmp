package io.spherelabs.passphraseapi.domain.usecase

interface GetKeyPasswordUseCase {
  suspend fun execute(): String
}
