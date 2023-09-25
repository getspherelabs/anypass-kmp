package io.spherelabs.addnewpassworddomain.usecase

import io.spherelabs.addnewpassworddomain.model.AddNewCategoryDomain
import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository
import kotlinx.coroutines.flow.Flow

interface GetCategories {
  fun execute(): Flow<List<AddNewCategoryDomain>>
}

class DefaultGetCategories(private val repository: AddNewPasswordRepository) : GetCategories {

  override fun execute(): Flow<List<AddNewCategoryDomain>> {
    return repository.getCategories()
  }
}
