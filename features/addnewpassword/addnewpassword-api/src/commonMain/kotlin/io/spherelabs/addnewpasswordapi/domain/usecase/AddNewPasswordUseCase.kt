package io.spherelabs.addnewpasswordapi.domain.usecase

import io.spherelabs.addnewpasswordapi.model.AddNewPassword

interface AddNewPasswordUseCase {
    suspend fun execute(password: AddNewPassword): Result<Unit>
}
