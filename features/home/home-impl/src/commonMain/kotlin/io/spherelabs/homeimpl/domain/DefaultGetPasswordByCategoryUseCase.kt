package io.spherelabs.homeimpl.domain

import io.spherelabs.homeapi.domain.usecase.GetPasswordsByCategoryUseCase
import io.spherelabs.homeapi.models.HomePassword
import io.spherelabs.homeapi.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class DefaultGetPasswordByCategoryUseCase(private val homeRepository: HomeRepository) :
    GetPasswordsByCategoryUseCase {
  override fun execute(categoryId: String): Flow<List<HomePassword>> {
    if (categoryId.isEmpty()) throw Exception("Invalid a category id.")
    return homeRepository.getPasswordsByCategory(categoryId)
  }
}
