package io.spherelabs.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import io.spherelabs.data.local.extension.countReusedPassword
import io.spherelabs.data.local.extension.isStrongPassword
import io.spherelabs.data.local.extension.reusedPasswords
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.PasswordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PasswordDao {
    suspend fun insertPassword(password: PasswordEntity)
    suspend fun insertPasswords(passwords: List<PasswordEntity>)
    suspend fun updatePassword(password: PasswordEntity)
    suspend fun deletePassword(id: String)
    fun getAllPassword(): Flow<List<PasswordEntity>>
    fun getPasswordById(id: String): Flow<PasswordEntity>
    fun getPasswordsByCategory(id: String): Flow<List<PasswordEntity>>
    fun getSizeOfStrongPasswords(): Flow<Int>
    fun getSizeOfWeakPasswords(): Flow<Int>
    fun getTotalPasswords(): Flow<Int>
    fun getStrongPasswords(): Flow<List<PasswordEntity>>
    fun getWeakPasswords(): Flow<List<PasswordEntity>>
    fun getSizeOfReusedPasswords(): Flow<Int>
    fun getReusedPasswords(): Flow<List<PasswordEntity>>
}

class DefaultPasswordDao(
    database: AnyPassDatabase,
) : PasswordDao {

    private val queries = database.passwordQueries


    override suspend fun insertPassword(password: PasswordEntity) {
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

    override suspend fun insertPasswords(passwords: List<PasswordEntity>) {
        queries.transaction {
            for (password in passwords) {
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
    }

    override suspend fun updatePassword(password: PasswordEntity) {
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

    override fun getAllPassword(): Flow<List<PasswordEntity>> {
        return queries.getAllPasswords().asFlow().mapToList(Dispatchers.IO)
    }

    override fun getPasswordById(id: String): Flow<PasswordEntity> {
        return queries.getPasswordById(id).asFlow().mapToOne(Dispatchers.IO)
    }

    override fun getPasswordsByCategory(id: String): Flow<List<PasswordEntity>> {
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

    override fun getTotalPasswords(): Flow<Int> {
        return queries.getAllPasswords()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { currentPasswords ->
                currentPasswords.size
            }
    }

    override fun getStrongPasswords(): Flow<List<PasswordEntity>> {
        return queries.getAllPasswords()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { currentPasswords ->
                currentPasswords.filter { password -> password.isStrongPassword() }
            }
    }

    override fun getWeakPasswords(): Flow<List<PasswordEntity>> {
        return queries.getAllPasswords()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { currentPasswords ->
                currentPasswords.filterNot { password -> password.isStrongPassword() }
            }
    }

    override fun getSizeOfReusedPasswords(): Flow<Int> {
        return queries.getAllPasswords()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { currentPasswords ->
                currentPasswords.countReusedPassword()
            }
    }

    override fun getReusedPasswords(): Flow<List<PasswordEntity>> {
        return queries.getAllPasswords()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { currentPasswords ->
                currentPasswords.reusedPasswords()
            }
    }
}
