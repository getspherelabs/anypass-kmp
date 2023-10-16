package io.spherelabs.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import io.spherelabs.data.local.db.DefaultPasswordDao
import io.spherelabs.data.local.db.PasswordDao
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.Password
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class PasswordDaoTest {

    private lateinit var sqlDriverFactory: SqlDriver
    private lateinit var database: AnyPassDatabase
    private lateinit var dao: PasswordDao

    @BeforeTest
    fun setup() {
        sqlDriverFactory = TestSqlDriverFactory().createDriver()
        database = AnyPassDatabase.invoke(sqlDriverFactory)
        dao = DefaultPasswordDao(database)
    }

    @Test
    fun `check insert password and get password by categories`() = runTest {
        val password = Password(
            id = "1",
            username = "test",
            category_id = "1",
            email = "test",
            image = "test",
            notes = "test",
            title = "test",
            password = "test",
            websiteAddress = "test",
        )
        val password2 = Password(
            id = "1",
            username = "test",
            category_id = "2",
            email = "test",
            image = "test",
            notes = "test",
            title = "test",
            password = "test",
            websiteAddress = "test",
        )

        dao.insertPassword(password)
        dao.insertPassword(password2)

        val result = dao.getPasswordsByCategory("2")

        result.test {
            assertEquals(1, awaitItem().size)
        }
    }


}
