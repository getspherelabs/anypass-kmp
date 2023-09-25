package io.spherelabs.home.homedomain

import io.spherelabs.home.homedomain.model.HomeCategoryDomain
import io.spherelabs.home.homedomain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

interface GetCategories {
  fun execute(): Flow<List<HomeCategoryDomain>>
}

class DefaultGetCategories(private val repository: HomeRepository) : GetCategories {

  override fun execute(): Flow<List<HomeCategoryDomain>> {
    return repository.getCategories()
  }
}
