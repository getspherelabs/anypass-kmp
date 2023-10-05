package io.spherelabs.home.homedomain.repository

import io.spherelabs.home.homedomain.model.HomeCategoryDomain
import io.spherelabs.home.homedomain.model.HomePasswordDomain
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  fun getCategories(): Flow<List<HomeCategoryDomain>>


  fun getPasswordsByCategory(id: String): Flow<List<HomePasswordDomain>>
}
