package io.spherelabs.data.local.repository

import io.spherelabs.data.local.db.PasswordHistoryDao
import io.spherelabs.generatepasswordapi.domain.repository.GeneratePasswordRepository

class DefaultGeneratePasswordRepository(
    private val dao: PasswordHistoryDao,
) : GeneratePasswordRepository {

    override suspend fun insertPasswordHistory(password: String, createdAt: Long) {
        dao.insertPasswordHistory(password, createdAt)
    }
}
