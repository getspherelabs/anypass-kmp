package io.spherelabs.addnewpasswordapi.domain.repository

import io.spherelabs.addnewpasswordapi.model.AddNewCategory
import io.spherelabs.addnewpasswordapi.model.AddNewPassword
import kotlinx.coroutines.flow.Flow

interface AddNewPasswordRepository {
    suspend fun insertPassword(password: AddNewPassword)

    fun getCategories(): Flow<List<AddNewCategory>>
}
