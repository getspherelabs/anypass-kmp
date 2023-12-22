package io.spherelabs.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import io.spherelabs.data.local.db.DefaultPasswordDao
import io.spherelabs.data.local.db.PasswordDao
import io.spherelabs.data.local.db.adapter.OtpDigitColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpDurationColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpTypeColumnAdapter
import io.spherelabs.data.local.extension.isStrongPassword
import io.spherelabs.data.local.extension.reusedPasswords
import io.spherelabs.data.local.faker.Faker
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity
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
        database = AnyPassDatabase(
            sqlDriverFactory,
            OtpEntity.Adapter(
                digitAdapter = OtpDigitColumnAdapter(),
                durationAdapter = OtpDurationColumnAdapter(),
                typeAdapter = OtpTypeColumnAdapter(),
            ),
        )
        dao = DefaultPasswordDao(database)
    }

    @Test
    fun `check insert password and get password by categories`() = runTest {
        val passwords = Faker.password

        dao.insertPasswords(passwords)

        val result = dao.getPasswordsByCategory("2")

        result.test {
            val newPasswords = awaitItem()
            assertEquals(1, newPasswords.size)
            assertEquals("2", newPasswords[0].category_id)
        }
    }


    @Test
    fun `check the returns reused password properly`() = runTest {
        // Given
        val passwords = Faker.password

        // When
        dao.insertPasswords(passwords)
        val result = dao.getReusedPasswords()
        val expectedPasswords = passwords.reusedPasswords()

        // Then
        result.test {
            val newPasswords = awaitItem()
            assertThat(newPasswords).isEqualTo(expectedPasswords)
        }
    }

    @Test
    fun `check the returns strong passwords properly`() = runTest {
        // Given
        val passwords = Faker.password

        // When
        dao.insertPasswords(passwords)
        val result = dao.getStrongPasswords()
        val expectedPasswords = passwords.filter { it.isStrongPassword() }

        // Then
        result.test {
            val newPasswords = awaitItem()
            assertThat(newPasswords).isEqualTo(expectedPasswords)
        }
    }

}
