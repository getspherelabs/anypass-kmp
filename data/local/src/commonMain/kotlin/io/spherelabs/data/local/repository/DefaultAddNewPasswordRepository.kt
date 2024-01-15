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
import io.spherelabs.data.local.website.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    override fun getWebsites(): Flow<Result<Websites>> {
        return flow<Result<Websites>> {
            val result = websiteService.get()

            result.onSuccess { data ->
                emit(Result.success(data.toDomain()))
            }.onFailure {
                emit(Result.failure(it))
            }
        }.flowOn(Dispatchers.IO)
    }
}
