package io.spherelabs.addnewpassworddomain.usecase

import io.spherelabs.addnewpassworddomain.model.AddNewCategoryDomain
import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUseCase {
  fun execute(): Flow<List<AddNewCategoryDomain>>
}

class DefaultGetCategoriesUseCaseUseCase(private val repository: AddNewPasswordRepository) : GetCategoriesUseCase {

  override fun execute(): Flow<List<AddNewCategoryDomain>> {
    return repository.getCategories()
  }
}
