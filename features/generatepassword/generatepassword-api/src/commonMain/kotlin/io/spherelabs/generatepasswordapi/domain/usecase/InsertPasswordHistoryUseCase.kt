package io.spherelabs.generatepasswordapi.domain.usecase

interface InsertPasswordHistoryUseCase {
    suspend fun execute(password: String, createdAt: Long)
}
