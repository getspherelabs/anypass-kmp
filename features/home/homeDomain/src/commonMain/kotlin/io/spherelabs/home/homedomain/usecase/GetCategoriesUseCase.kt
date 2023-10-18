package io.spherelabs.home.homedomain.usecase

import io.spherelabs.home.homedomain.model.HomeCategoryDomain
import io.spherelabs.home.homedomain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUseCase {
  fun execute(): Flow<List<HomeCategoryDomain>>
}

class DefaultGetCategoriesUseCase(private val repository: HomeRepository) : GetCategoriesUseCase {

  override fun execute(): Flow<List<HomeCategoryDomain>> {
    return repository.getCategories()
  }
}
