package io.spherelabs.home.homedomain

import io.spherelabs.home.homedomain.model.HomePasswordDomain
import io.spherelabs.home.homedomain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

interface GetPasswordsByCategory {
  fun execute(): Flow<List<HomePasswordDomain>>
}

class DefaultGetPasswordByCategory(private val homeRepository: HomeRepository) :
  GetPasswordsByCategory {
  override fun execute(): Flow<List<HomePasswordDomain>> {
    TODO("Not yet implemented")
  }
}
