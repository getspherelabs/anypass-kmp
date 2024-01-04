package io.spherelabs.authimpl.domain

import io.spherelabs.authapi.domain.usecase.InsertUserUseCase
import io.spherelabs.common.uuid4
import io.spherelabs.data.local.db.UserDao
import io.spherelabs.local.db.UserEntity

class DefaultInsertUserUseCase(
    private val userDao: UserDao,
) : InsertUserUseCase {

    override suspend fun execute(name: String, email: String, password: String) {
        userDao.insertUser(
            UserEntity(
                id = uuid4(),
                name = name,
                email = email,
                password = password,
            ),
        )
    }
}
