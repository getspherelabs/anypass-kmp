package io.spherelabs.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import io.spherelabs.local.db.LockerDatabase
import io.spherelabs.local.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface UserDao {
    suspend fun insertPassword(password: User)
    suspend fun updatePassword(password: User)
    fun getFavouriteById(id: String): Flow<User>
}

class DefaultUserDao(
    private val db: LockerDatabase
) : UserDao {

    private val queries = db.userQueries

    override suspend fun insertPassword(password: User) {
        queries.transaction {
            queries.insertUser(
                id = password.id,
                email = password.email,
                name = password.name,
                password = password.password
            )
        }
    }

    override suspend fun updatePassword(password: User) {
        queries.transaction {
            queries.updateUser(
                name = password.name,
                password = password.password
            )
        }
    }

    override fun getFavouriteById(id: String): Flow<User> {
        return queries.getUserById(id).asFlow().mapToOne(Dispatchers.IO)
    }
}