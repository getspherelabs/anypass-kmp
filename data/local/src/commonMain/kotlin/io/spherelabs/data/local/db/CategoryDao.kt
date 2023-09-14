package io.spherelabs.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.spherelabs.local.db.Category
import io.spherelabs.local.db.LockerDatabase
import io.spherelabs.local.db.Password
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface CategoryDao {
    fun getAllCategory(): Flow<List<io.spherelabs.local.db.Category>>
}

class DefaultCategoryDao(
    database: LockerDatabase
) : CategoryDao {

    private val queries = database.categoryQueries

    override fun getAllCategory(): Flow<List<Category>> {
        return queries.getAllCategories().asFlow().mapToList(Dispatchers.IO)
    }
}