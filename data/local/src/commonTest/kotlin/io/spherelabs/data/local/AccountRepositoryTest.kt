package io.spherelabs.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import io.spherelabs.accountdomain.repository.AccountRepository
import io.spherelabs.data.local.db.DefaultPasswordDao
import io.spherelabs.data.local.db.DefaultUserDao
import io.spherelabs.data.local.db.PasswordDao
import io.spherelabs.data.local.db.UserDao
import io.spherelabs.data.local.db.adapter.OtpDigitColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpDurationColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpTypeColumnAdapter
import io.spherelabs.data.local.repository.DefaultAccountRepository
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity
import io.spherelabs.local.db.Password
import io.spherelabs.local.db.User
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
        val password1 = Password(
            id = "1",
            category_id = "1",
            email = "test@gmail.com",
            image = "test_2_image",
            title = "Behance",
            notes = "test_notes",
            password = "12345",
            username = "Test1",
            websiteAddress = "Behance.com",
        )
        val password2 = Password(
            id = "2",
            category_id = "1",
            email = "test@gmail.com",
            image = "test_2_image",
            title = "Linkedin",
            notes = "test_notes",
            password = "Strong@2023",
            username = "Test2",
            websiteAddress = "Linkedin.com",
        )
        val password3 = Password(
            id = "3",
            category_id = "1",
            email = "test@gmail.com",
            image = "test_2_image",
            title = "Quora",
            notes = "test_notes",
            password = "AbCdEfG1",
            username = "Test3",
            websiteAddress = "Quora.com",
        )
        dao.insertPassword(password1)
        dao.insertPassword(password2)
        dao.insertPassword(password3)

        val result = repository.getSizeOfWeakPasswords()

        result.test {
            assertThat(awaitItem()).isEqualTo(2)
        }
    }

    @Test
    fun `check the fetching total passwords`() = runTest {
        val password1 = Password(
            id = "1",
            category_id = "1",
            email = "test@gmail.com",
            image = "test_2_image",
            title = "Behance",
            notes = "test_notes",
            password = "12345",
            username = "Test1",
            websiteAddress = "Behance.com",
        )
        val password2 = Password(
            id = "2",
            category_id = "1",
            email = "test@gmail.com",
            image = "test_2_image",
            title = "Linkedin",
            notes = "test_notes",
            password = "Strong@2023",
            username = "Test2",
            websiteAddress = "Linkedin.com",
        )
        val password3 = Password(
            id = "3",
            category_id = "1",
            email = "test@gmail.com",
            image = "test_2_image",
            title = "Quora",
            notes = "test_notes",
            password = "AbCdEfG1",
            username = "Test3",
            websiteAddress = "Quora.com",
        )
        dao.insertPassword(password1)
        dao.insertPassword(password2)
        dao.insertPassword(password3)

        val result = repository.getTotalPasswords()

        result.test {
            assertThat(awaitItem()).isEqualTo(3)
        }
    }

    @Test
    fun `check insert user and get user`() = runTest {
        val user = User(
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
