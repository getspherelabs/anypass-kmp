package io.spherelabs.addnewpasswordapi.domain.usecase

import io.spherelabs.addnewpasswordapi.model.AddNewCategory
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUseCase {
    fun execute(): Flow<List<AddNewCategory>>
}
