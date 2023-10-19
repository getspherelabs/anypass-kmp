package io.spherelabs.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import io.spherelabs.data.local.db.DefaultUserDao
import io.spherelabs.data.local.db.UserDao
import io.spherelabs.data.local.db.adapter.OtpDigitColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpDurationColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpTypeColumnAdapter
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity
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
        database = AnyPassDatabase(sqlDriverFactory,
            OtpEntity.Adapter(
            digitAdapter = OtpDigitColumnAdapter(),
            durationAdapter = OtpDurationColumnAdapter(),
            typeAdapter = OtpTypeColumnAdapter()
        ))
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
    @Test
    fun `check update user and get user`() = runTest {
        val user = User(
            id = "1",
            name = "test",
            email = "test",
            password = "",
        )

        dao.insertUser(user)
        val newUser = User("1","oybek","","")


        dao.updateUser(newUser)
        val result = dao.getUser()

        result.test {
            assertEquals("oybek", awaitItem().name)
        }
    }
}

