package io.spherelabs.addnewpasswordimpl.domain

import io.spherelabs.addnewpasswordapi.domain.repository.AddNewPasswordRepository
import io.spherelabs.addnewpasswordapi.domain.usecase.GetCategoriesUseCase
import io.spherelabs.addnewpasswordapi.model.AddNewCategory
import kotlinx.coroutines.flow.Flow

class DefaultGetCategoriesUseCaseUseCase(private val repository: AddNewPasswordRepository) :
    GetCategoriesUseCase {

  override fun execute(): Flow<List<AddNewCategory>> {
    return repository.getCategories()
  }
}
