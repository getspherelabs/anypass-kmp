package io.spherelabs.home.homedomain

import io.spherelabs.home.homedomain.model.HomePasswordDomain
import io.spherelabs.home.homedomain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

interface GetPasswordsByCategory {
  fun execute(categoryId: String): Flow<List<HomePasswordDomain>>
}

class DefaultGetPasswordByCategory(private val homeRepository: HomeRepository) :
  GetPasswordsByCategory {
  override fun execute(categoryId: String): Flow<List<HomePasswordDomain>> {
    if (categoryId.isEmpty()) throw Exception("Invalid a category id.")
    return homeRepository.getPasswordsByCategory(categoryId)
  }
}
