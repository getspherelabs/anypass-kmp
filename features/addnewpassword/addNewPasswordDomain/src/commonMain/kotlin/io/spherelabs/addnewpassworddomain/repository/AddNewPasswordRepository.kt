package io.spherelabs.addnewpassworddomain.repository

import io.spherelabs.addnewpassworddomain.model.AddNewCategoryDomain
import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import kotlinx.coroutines.flow.Flow

interface AddNewPasswordRepository {
    suspend fun insertPassword(password: AddNewPasswordDomain)
    fun getCategories(): Flow<List<AddNewCategoryDomain>>
}