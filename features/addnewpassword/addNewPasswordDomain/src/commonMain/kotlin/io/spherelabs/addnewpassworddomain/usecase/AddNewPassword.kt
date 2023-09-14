package io.spherelabs.addnewpassworddomain.usecase

import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository

interface AddNewPassword {
    suspend fun execute(password: AddNewPasswordDomain)
}

class DefaultAddNewPassword(
    private val repository: AddNewPasswordRepository
) : AddNewPassword {

    override suspend fun execute(password: AddNewPasswordDomain) {
        repository.insertPassword(password)
        println("Result succes is add neww password")

    }

}