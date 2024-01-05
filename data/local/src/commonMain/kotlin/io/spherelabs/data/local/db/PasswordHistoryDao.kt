package io.spherelabs.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.PasswordHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface PasswordHistoryDao {
    suspend fun insertPasswordHistory(entity: PasswordHistoryEntity)
    suspend fun deletePasswordHistory(id: String)
    fun getAllPasswordHistory(): Flow<List<PasswordHistoryEntity>>
}

class DefaultPasswordHistoryDao(
    database: AnyPassDatabase,
) : PasswordHistoryDao {
    private val queries = database.passwordHistoryQueries

    override suspend fun insertPasswordHistory(entity: PasswordHistoryEntity) {
        queries.transaction {
            queries.insertPasswordHistory(
                id = entity.id,
                password = entity.password,
                createdAt = entity.createdAt
            )
        }
    }

    override suspend fun deletePasswordHistory(id: String) {
        queries.transaction {
            queries.deletePasswordHistory(id)
        }
    }

    override fun getAllPasswordHistory(): Flow<List<PasswordHistoryEntity>> {
        return queries.getAllPasswordHistory().asFlow().mapToList(Dispatchers.IO)
    }
}
