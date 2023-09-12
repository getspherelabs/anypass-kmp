package io.spherelabs.addnewpassworddomain.usecase

import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository

interface AddNewPassword {
    suspend fun execute(password: AddNewPasswordDomain?): Result<Unit>
}

class DefaultAddNewPassword(
    private val repository: AddNewPasswordRepository
) : AddNewPassword {

    override suspend fun execute(password: AddNewPasswordDomain?): Result<Unit> {
        return password?.let {
            if (password.password.length <= 10) {
                Result.failure(Exception("Password the length"))
            } else {
                kotlin.runCatching {
                    repository.insertPassword(password)
                }
            }
        } ?: kotlin.run {
            Result.failure(Exception("Invalid the input password"))
        }

    }

}