package io.spherelabs.data.local.repository

import io.spherelabs.data.local.db.CategoryDao
import io.spherelabs.data.local.db.UserDao
import io.spherelabs.data.local.mapper.asDomain
import io.spherelabs.home.homedomain.model.HomeCategoryDomain
import io.spherelabs.home.homedomain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultHomeRepository(
    private val categoryDao: CategoryDao,
    private val userDao: UserDao
) : HomeRepository {

    override fun getCategories(): Flow<List<HomeCategoryDomain>> {
        return categoryDao.getAllCategory()
            .map { categories -> categories.map { category -> category.asDomain() } }
    }

    override fun getEmail(): Flow<String> {
        return userDao.getUser().map {
            requireNotNull(it.email)
        }
    }
}