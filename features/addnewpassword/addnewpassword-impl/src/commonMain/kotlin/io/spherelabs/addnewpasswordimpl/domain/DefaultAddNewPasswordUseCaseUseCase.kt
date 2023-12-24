package io.spherelabs.addnewpasswordimpl.domain

import io.spherelabs.addnewpasswordapi.domain.repository.AddNewPasswordRepository
import io.spherelabs.addnewpasswordapi.domain.usecase.AddNewPasswordUseCase
import io.spherelabs.addnewpasswordapi.model.AddNewPassword

class DefaultAddNewPasswordUseCaseUseCase(private val repository: AddNewPasswordRepository) :
    AddNewPasswordUseCase {

  override suspend fun execute(password: AddNewPassword): Result<Unit> {
    return runCatching {
      if (password.category.isNotEmpty()) {
        repository.insertPassword(password)
      }
    }
  }
}
