package io.spherelabs.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import io.spherelabs.accountapi.domain.repository.AccountRepository
import io.spherelabs.data.local.db.DefaultPasswordDao
import io.spherelabs.data.local.db.DefaultUserDao
import io.spherelabs.data.local.db.PasswordDao
import io.spherelabs.data.local.db.UserDao
import io.spherelabs.data.local.db.adapter.OtpDigitColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpDurationColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpTypeColumnAdapter
import io.spherelabs.data.local.faker.Faker
import io.spherelabs.data.local.repository.DefaultAccountRepository
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity
import io.spherelabs.local.db.UserEntity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class AccountRepositoryTest {

    private lateinit var sqlDriverFactory: SqlDriver
    private lateinit var database: AnyPassDatabase
    private lateinit var dao: PasswordDao
    private lateinit var userDao: UserDao
    private lateinit var repository: AccountRepository


    @BeforeTest
    fun setup() {
        sqlDriverFactory = TestSqlDriverFactory().createDriver()
        database = AnyPassDatabase(sqlDriverFactory,OtpEntity.Adapter(
            digitAdapter = OtpDigitColumnAdapter(),
            durationAdapter = OtpDurationColumnAdapter(),
            typeAdapter = OtpTypeColumnAdapter()
        ))
        dao = DefaultPasswordDao(database)
        userDao = DefaultUserDao(database)
        repository = DefaultAccountRepository(dao, userDao)
    }

    @Test
    fun `check fetching the total password size`() = runTest {
        val passwords = Faker.password

        dao.insertPasswords(passwords)

        val result = repository.getSizeOfWeakPasswords()

        result.test {
            assertThat(awaitItem()).isEqualTo(2)
        }
    }

    @Test
    fun `check the fetching total passwords`() = runTest {
        val passwords = Faker.password

        dao.insertPasswords(passwords)

        val result = repository.getTotalPasswords()

        result.test {
            assertThat(awaitItem()).isEqualTo(5)
        }
    }

    @Test
    fun `check insert user and get user`() = runTest {
        val user = UserEntity(
            id = "1",
            name = "test",
            email = "test",
            password = "",
        )

        userDao.insertUser(user)
        val result = repository.getUser()

        result.test {
            assertEquals("1", awaitItem().id)
        }
    }
}
