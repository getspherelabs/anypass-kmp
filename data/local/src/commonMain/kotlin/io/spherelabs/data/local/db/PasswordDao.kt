package io.spherelabs.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import io.spherelabs.local.db.LockerDatabase
import io.spherelabs.local.db.Password
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface PasswordDao {
    suspend fun insertPassword(password: Password)
    suspend fun updatePassword(password: Password)
    suspend fun deletePassword(id: String)
    fun getAllPassword(): Flow<List<Password>>
    fun getFavouriteById(id: String): Flow<Password>
}

class DefaultPasswordDao(
    database: LockerDatabase
) : PasswordDao {

    private val queries = database.passwordQueries


    override suspend fun insertPassword(password: Password) {
        queries.transaction {
            queries.insertPassword(
                id = password.id,
                title = password.title,
                username = password.username,
                password = password.password,
                category = password.category
            )
        }
    }

    override suspend fun updatePassword(password: Password) {
        queries.transaction {
            queries.updatePassword(
                title = password.title,
                username = password.username,
                password = password.password,
                category = password.category,
                email = password.email
            )
        }
    }

    override suspend fun deletePassword(id: String) {
        queries.transaction {
            queries.deletePassword(id)
        }
    }

    override fun getAllPassword(): Flow<List<Password>> {
        return queries.getAllPasswords().asFlow().mapToList(Dispatchers.IO)
    }

    override fun getFavouriteById(id: String): Flow<Password> {
        return queries.getPasswordById(id).asFlow().mapToOne(Dispatchers.IO)
    }
}