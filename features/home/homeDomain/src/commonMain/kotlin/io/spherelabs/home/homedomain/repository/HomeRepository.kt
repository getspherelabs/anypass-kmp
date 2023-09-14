package io.spherelabs.home.homedomain.repository

import io.spherelabs.home.homedomain.model.HomeCategoryDomain
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getCategories(): Flow<List<HomeCategoryDomain>>
}