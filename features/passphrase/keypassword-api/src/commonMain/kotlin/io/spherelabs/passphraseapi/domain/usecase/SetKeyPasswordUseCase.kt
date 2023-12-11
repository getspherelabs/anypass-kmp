package io.spherelabs.passphraseapi.domain.usecase

interface SetKeyPasswordUseCase {
    suspend fun execute(value: String)
}
