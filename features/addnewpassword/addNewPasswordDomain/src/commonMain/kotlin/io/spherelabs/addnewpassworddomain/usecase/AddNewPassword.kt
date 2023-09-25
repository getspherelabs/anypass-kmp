package io.spherelabs.addnewpassworddomain.usecase

import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository

interface AddNewPassword {
  suspend fun execute(password: AddNewPasswordDomain): Result<Unit>
}

class DefaultAddNewPassword(private val repository: AddNewPasswordRepository) : AddNewPassword {

  override suspend fun execute(password: AddNewPasswordDomain): Result<Unit> {
    return runCatching {
      if (password.category.isNotEmpty()) {
        repository.insertPassword(password)
      }
    }
  }
}
