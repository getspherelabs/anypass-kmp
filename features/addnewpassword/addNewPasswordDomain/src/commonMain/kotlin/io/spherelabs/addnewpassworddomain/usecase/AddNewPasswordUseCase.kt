package io.spherelabs.addnewpassworddomain.usecase

import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository

interface AddNewPasswordUseCase {
  suspend fun execute(password: AddNewPasswordDomain): Result<Unit>
}

class DefaultAddNewPasswordUseCaseUseCase(private val repository: AddNewPasswordRepository) : AddNewPasswordUseCase {

  override suspend fun execute(password: AddNewPasswordDomain): Result<Unit> {
    return runCatching {
      if (password.category.isNotEmpty()) {
        repository.insertPassword(password)
      }
    }
  }
}
