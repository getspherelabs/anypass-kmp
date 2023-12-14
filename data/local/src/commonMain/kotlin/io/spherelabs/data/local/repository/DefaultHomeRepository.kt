package io.spherelabs.data.local.repository

import io.spherelabs.data.local.db.CategoryDao
import io.spherelabs.data.local.db.PasswordDao
import io.spherelabs.data.local.mapper.asDomain
import io.spherelabs.data.local.mapper.asHomeDomain
import io.spherelabs.homeapi.models.HomeCategory
import io.spherelabs.homeapi.models.HomePassword
import io.spherelabs.homeapi.repository.HomeRepository
import io.spherelabs.local.db.PasswordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultHomeRepository(
    private val categoryDao: CategoryDao,
    private val passwordDao: PasswordDao,
) : HomeRepository {

    override fun getCategories(): Flow<List<HomeCategory>> {
        return categoryDao.getAllCategory()
            .map { categories -> categories.map { category -> category.asDomain() } }
    }

    override fun getPasswordsByCategory(id: String): Flow<List<HomePassword>> {
        return passwordDao.getPasswordsByCategory(id).map { passwords -> passwords.map { password: PasswordEntity -> password.asHomeDomain() } }
    }
}
