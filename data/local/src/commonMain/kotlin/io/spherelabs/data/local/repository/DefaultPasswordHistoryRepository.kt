package io.spherelabs.data.local.repository

import io.spherelabs.data.local.db.PasswordHistoryDao
import io.spherelabs.data.local.mapper.toDomain
import io.spherelabs.passwordhistoryapi.model.PasswordHistoryResult
import io.spherelabs.passwordhistoryapi.repository.PasswordHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultPasswordHistoryRepository(
    private val dao: PasswordHistoryDao,
) : PasswordHistoryRepository {

    override suspend fun clearPasswordHistory() {
        dao.clearAllPasswordHistory()
    }

    override fun getAllPasswordHistory(): Flow<PasswordHistoryResult> {
        return dao.getAllPasswordHistory().map { it.toDomain() }
    }
}
