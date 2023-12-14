package io.spherelabs.homeapi.repository

import io.spherelabs.homeapi.models.HomeCategory
import io.spherelabs.homeapi.models.HomePassword
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getCategories(): Flow<List<HomeCategory>>
    fun getPasswordsByCategory(id: String): Flow<List<HomePassword>>
}
