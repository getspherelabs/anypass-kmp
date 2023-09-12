package io.spherelabs.data.local.repository

import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository
import io.spherelabs.data.local.db.PasswordDao
import io.spherelabs.data.local.mapper.asEntity

class DefaultAddNewPasswordRepository(
    private val dao: PasswordDao
) : AddNewPasswordRepository {

    override suspend fun insertPassword(password: AddNewPasswordDomain) {
        dao.insertPassword(password = password.asEntity())
    }
}