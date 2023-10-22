package io.spherelabs.home.homedomain.usecase

import io.spherelabs.home.homedomain.model.HomePasswordDomain
import io.spherelabs.home.homedomain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

interface GetPasswordsByCategoryUseCase {
  fun execute(categoryId: String): Flow<List<HomePasswordDomain>>
}

class DefaultGetPasswordByCategoryUseCase(private val homeRepository: HomeRepository) :
    GetPasswordsByCategoryUseCase {
  override fun execute(categoryId: String): Flow<List<HomePasswordDomain>> {
    if (categoryId.isEmpty()) throw Exception("Invalid a category id.")
    return homeRepository.getPasswordsByCategory(categoryId)
  }
}
