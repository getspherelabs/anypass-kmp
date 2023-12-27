package io.spherelabs.homeimpl.domain

import io.spherelabs.homeapi.domain.usecase.GetCategoriesUseCase
import io.spherelabs.homeapi.models.HomeCategory
import io.spherelabs.homeapi.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class DefaultGetCategoriesUseCase(private val repository: HomeRepository) : GetCategoriesUseCase {

  override fun execute(): Flow<List<HomeCategory>> {
    return repository.getCategories()
  }
}
