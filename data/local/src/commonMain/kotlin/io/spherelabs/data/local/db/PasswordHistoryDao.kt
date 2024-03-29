package io.spherelabs.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.spherelabs.common.uuid4
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.PasswordHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface PasswordHistoryDao {
    suspend fun insertPasswordHistory(password: String, createdAt: Long): String
    suspend fun deletePasswordHistory(id: String)
    fun getAllPasswordHistory(): Flow<List<PasswordHistoryEntity>>

    fun clearAllPasswordHistory()
}

class DefaultPasswordHistoryDao(
    database: AnyPassDatabase,
) : PasswordHistoryDao {
    private val queries = database.passwordHistoryQueries

    override suspend fun insertPasswordHistory(
        password: String,
        createdAt: Long,
    ): String {
        val newId = uuid4()
        queries.transaction {
            queries.insertPasswordHistory(
                id = newId,
                password = password,
                createdAt = createdAt,
            )
        }
        return newId
    }

    override suspend fun deletePasswordHistory(id: String) {
        queries.transaction {
            queries.deletePasswordHistory(id)
        }
    }

    override fun getAllPasswordHistory(): Flow<List<PasswordHistoryEntity>> {
        return queries.getAllPasswordHistory().asFlow().mapToList(Dispatchers.IO)
    }

    override fun clearAllPasswordHistory() {
        queries.transaction {
            queries.clearAllPasswords()
        }
    }
}
