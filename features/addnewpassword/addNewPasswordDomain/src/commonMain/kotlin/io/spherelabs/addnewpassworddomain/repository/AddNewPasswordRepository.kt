package io.spherelabs.addnewpassworddomain.repository

import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain

interface AddNewPasswordRepository {
    suspend fun insertPassword(password: AddNewPasswordDomain)
}