package io.spherelabs.data.local.repository

import io.spherelabs.addnewpassworddomain.model.AddNewCategoryDomain
import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository
import io.spherelabs.data.local.db.CategoryDao
import io.spherelabs.data.local.db.PasswordDao
import io.spherelabs.data.local.mapper.asEntity
import io.spherelabs.data.local.mapper.asNewDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultAddNewPasswordRepository(
    private val dao: PasswordDao,
    private val categoryDao: CategoryDao
) : AddNewPasswordRepository {

    override suspend fun insertPassword(password: AddNewPasswordDomain) {
        dao.insertPassword(password = password.asEntity())
    }

    override fun getCategories(): Flow<List<AddNewCategoryDomain>> {
        return categoryDao.getAllCategory()
            .map { categories -> categories.map { category -> category.asNewDomain() } }
    }
}