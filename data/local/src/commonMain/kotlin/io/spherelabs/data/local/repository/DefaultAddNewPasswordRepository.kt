package io.spherelabs.data.local.repository

import io.spherelabs.addnewpasswordapi.domain.repository.AddNewPasswordRepository
import io.spherelabs.addnewpasswordapi.model.AddNewCategory
import io.spherelabs.addnewpasswordapi.model.AddNewPassword
import io.spherelabs.addnewpasswordapi.model.Websites
import io.spherelabs.data.local.db.CategoryDao
import io.spherelabs.data.local.db.PasswordDao
import io.spherelabs.data.local.mapper.asEntity
import io.spherelabs.data.local.mapper.asNewDomain
import io.spherelabs.data.local.website.WebsiteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DefaultAddNewPasswordRepository(
    private val dao: PasswordDao,
    private val categoryDao: CategoryDao,
    private val websiteService: WebsiteService,
) : AddNewPasswordRepository {

    override suspend fun insertPassword(password: AddNewPassword) {
        dao.insertPassword(password = password.asEntity())
    }

    override fun getCategories(): Flow<List<AddNewCategory>> {
        return categoryDao.getAllCategory()
            .map { categories -> categories.map { category -> category.asNewDomain() } }
    }

    override fun getWebsites(): Flow<Websites> {
        return flow {
            val result = websiteService.get()

            result.onSuccess {

            }
        }
    }
}
