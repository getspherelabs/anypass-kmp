package io.spherelabs.passwordhistoryapi.repository

import io.spherelabs.passwordhistoryapi.model.PasswordHistoryResult
import kotlinx.coroutines.flow.Flow

interface PasswordHistoryRepository {
    suspend fun clearPasswordHistory()
    fun getAllPasswordHistory(): Flow<PasswordHistoryResult>
}
