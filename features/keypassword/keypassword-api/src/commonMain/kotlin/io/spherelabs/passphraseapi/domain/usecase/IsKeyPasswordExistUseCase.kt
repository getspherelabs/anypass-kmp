package io.spherelabs.passphraseapi.domain.usecase


interface IsPasswordExistUseCase {
    suspend fun execute(): Boolean
}
