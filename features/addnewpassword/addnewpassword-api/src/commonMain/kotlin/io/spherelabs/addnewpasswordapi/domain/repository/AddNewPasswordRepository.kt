package io.spherelabs.addnewpasswordapi.domain.repository

import io.spherelabs.addnewpasswordapi.model.AddNewCategory
import io.spherelabs.addnewpasswordapi.model.AddNewPassword
import io.spherelabs.addnewpasswordapi.model.Websites
import io.spherelabs.common.Option
import kotlinx.coroutines.flow.Flow

interface AddNewPasswordRepository {
    suspend fun insertPassword(password: AddNewPassword)

    fun getCategories(): Flow<List<AddNewCategory>>
    fun getWebsites(): Flow<Result<Websites>>
}
