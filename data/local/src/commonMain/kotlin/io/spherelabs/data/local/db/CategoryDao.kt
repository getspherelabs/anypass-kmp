package io.spherelabs.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface CategoryDao {
    fun getAllCategory(): Flow<List<Category>>
}

class DefaultCategoryDao(
    database: AnyPassDatabase
) : CategoryDao {

    private val queries = database.categoryQueries

    override fun getAllCategory(): Flow<List<Category>> {
        return queries.getAllCategories().asFlow().mapToList(Dispatchers.IO)
    }
}
