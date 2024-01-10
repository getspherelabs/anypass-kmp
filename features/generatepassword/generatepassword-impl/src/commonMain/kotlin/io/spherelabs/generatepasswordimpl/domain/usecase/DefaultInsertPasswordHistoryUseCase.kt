package io.spherelabs.generatepasswordimpl.domain.usecase

import io.spherelabs.generatepasswordapi.domain.repository.GeneratePasswordRepository
import io.spherelabs.generatepasswordapi.domain.usecase.InsertPasswordHistoryUseCase

class DefaultInsertPasswordHistoryUseCase(
    private val repository: GeneratePasswordRepository,
) : InsertPasswordHistoryUseCase {

    override suspend fun execute(password: String, createdAt: Long) {
        repository.insertPasswordHistory(password, createdAt)
    }
}
