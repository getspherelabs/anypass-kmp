package io.spherelabs.homeapi.domain.usecase

import io.spherelabs.homeapi.models.HomePassword
import kotlinx.coroutines.flow.Flow

interface GetPasswordsByCategoryUseCase {
    fun execute(categoryId: String): Flow<List<HomePassword>>
}
