package io.spherelabs.homeapi.domain.usecase

import io.spherelabs.homeapi.models.HomeCategory
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUseCase {
  fun execute(): Flow<List<HomeCategory>>
}
