package io.spherelabs.passphraseapi.domain.usecase


interface GetFingerprintStatusUseCase {
    suspend fun execute(): Boolean
}
