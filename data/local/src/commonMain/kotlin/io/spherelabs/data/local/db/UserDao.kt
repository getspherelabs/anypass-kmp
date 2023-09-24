package io.spherelabs.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import io.spherelabs.local.db.LockerDatabase
import io.spherelabs.local.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface UserDao {
    suspend fun insertUser(password: User)
    suspend fun updateUser(password: User)
    fun getUserById(id: String): Flow<User>
    fun getUser(): Flow<User>
}

class DefaultUserDao(
    val db: LockerDatabase
) : UserDao {

    private val queries = db.userQueries

    override suspend fun insertUser(password: User) {
        queries.transaction {
            queries.insertUser(
                id = password.id,
                email = password.email,
                name = password.name,
                password = password.password
            )
        }
    }

    override suspend fun updateUser(password: User) {
        queries.transaction {
            queries.updateUser(
                name = password.name,
                password = password.password
            )
        }
    }

    override fun getUserById(id: String): Flow<User> {
        return queries.getUserById(id).asFlow().mapToOne(Dispatchers.IO)
    }

    override fun getUser(): Flow<User> {
        return queries.getUser().asFlow().mapToOne(Dispatchers.IO)
    }
}