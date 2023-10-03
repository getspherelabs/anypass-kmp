package io.spherelabs.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.Password
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

interface PasswordDao {
    suspend fun insertPassword(password: Password)
    suspend fun updatePassword(password: Password)
    suspend fun deletePassword(id: String)
    fun getAllPassword(): Flow<List<Password>>
    fun getPasswordById(id: String): Flow<Password>
    fun getPasswordsByCategory(id: String): Flow<List<Password>>
    fun getSizeOfStrongPasswords(): Flow<Int>
    fun getSizeOfWeakPasswords(): Flow<Int>
}

class DefaultPasswordDao(
    database: AnyPassDatabase,
) : PasswordDao {

    private val queries = database.passwordQueries


    override suspend fun insertPassword(password: Password) {
        queries.transaction {
            queries.insertPassword(
                id = password.id,
                email = password.email,
                title = password.title,
                username = password.username,
                password = password.password,
                category_id = password.category_id,
                websiteAddress = password.websiteAddress,
                notes = password.notes,
                image = password.image,
            )
        }
    }

    override suspend fun updatePassword(password: Password) {
        queries.transaction {
            queries.updatePassword(
                title = password.title,
                username = password.username,
                password = password.password,
                category_id = password.category_id,
                email = password.email,
                websiteAddress = password.websiteAddress,
                notes = password.notes,
                image = password.image,
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

    override fun getPasswordById(id: String): Flow<Password> {
        return queries.getPasswordById(id).asFlow().mapToOne(Dispatchers.IO)
    }

    override fun getPasswordsByCategory(id: String): Flow<List<Password>> {
        return queries.getPasswordsByCategory(id).asFlow().mapToList(Dispatchers.IO)
    }

    override fun getSizeOfStrongPasswords(): Flow<Int> {
        return queries.getAllPasswords()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { currentPassword ->
                currentPassword.filter {
                    it.isStrongPassword()
                }.size
            }
    }

    override fun getSizeOfWeakPasswords(): Flow<Int> {
        return queries.getAllPasswords()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { currentPassword ->
                currentPassword.filterNot {
                    it.isStrongPassword()
                }.size
            }
    }
}
