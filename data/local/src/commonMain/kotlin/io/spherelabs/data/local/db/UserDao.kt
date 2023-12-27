package io.spherelabs.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface UserDao {
    suspend fun insertUser(password: UserEntity)
    suspend fun updateUser(password: UserEntity)
    fun getUserById(id: String): Flow<UserEntity>
    fun getUser(): Flow<UserEntity>
    fun deleteUserById(id: String)
}

class DefaultUserDao(
    val db: AnyPassDatabase,
) : UserDao {

    private val queries = db.userQueries

    override suspend fun insertUser(password: UserEntity) {
        queries.transaction {
            queries.insertUser(
                id = password.id,
                email = password.email,
                name = password.name,
                password = password.password,
            )
        }
    }

    override suspend fun updateUser(password: UserEntity) {
        queries.transaction {
            queries.updateUser(
                name = password.name,
                password = password.password,
            )
        }
    }

    override fun getUserById(id: String): Flow<UserEntity> {
        return queries.getUserById(id).asFlow().mapToOne(Dispatchers.IO)
    }

    override fun getUser(): Flow<UserEntity> {
        return queries.getUser().asFlow().mapToOne(Dispatchers.IO)
    }

    override fun deleteUserById(id: String) {
        queries.transaction { queries.deleteUserById(id) }
    }
}
