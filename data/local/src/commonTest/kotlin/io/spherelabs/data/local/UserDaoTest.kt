package io.spherelabs.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import io.spherelabs.data.local.db.DefaultUserDao
import io.spherelabs.data.local.db.UserDao
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.User
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.coroutines.test.runTest

class UserDaoTest {

    private lateinit var sqlDriverFactory: SqlDriver
    private lateinit var database: AnyPassDatabase
    private lateinit var dao: UserDao

    @BeforeTest
    fun setup() {
        sqlDriverFactory = TestSqlDriverFactory().createDriver()
        database = AnyPassDatabase.invoke(sqlDriverFactory)
        dao = DefaultUserDao(database)
    }

    @Test
    fun `check insert user and get user`() = runTest {
        val user = User(
            id = "1",
            name = "test",
            email = "test",
            password = "",
        )

        dao.insertUser(user)
        val result = dao.getUser()

        result.test {
            assertEquals("1", awaitItem().id)
        }


    }



















    @Test
    fun `check insert user and delete user`() = runTest {
        val user = User(
            id = "1",
            name = "test",
            email = "test",
            password = "",
        )
        dao.insertUser(user)


        dao.deleteUserById(user.id)
        val result = dao.getUser()

        result.test {
            assertEquals("ResultSet returned null for User.sq:getUser", awaitError().message)
        }


    }


}

