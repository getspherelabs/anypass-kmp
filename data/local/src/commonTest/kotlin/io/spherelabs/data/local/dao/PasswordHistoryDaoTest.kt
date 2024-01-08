package io.spherelabs.data.local.dao

import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import io.spherelabs.data.local.TestSqlDriverFactory
import io.spherelabs.data.local.db.DefaultPasswordHistoryDao
import io.spherelabs.data.local.db.PasswordHistoryDao
import io.spherelabs.data.local.db.adapter.OtpDigitColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpDurationColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpTypeColumnAdapter
import io.spherelabs.data.local.faker.Faker
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest

class PasswordHistoryDaoTest {

    private lateinit var sqlDriverFactory: SqlDriver
    private lateinit var database: AnyPassDatabase
    private lateinit var dao: PasswordHistoryDao

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
        dao = DefaultPasswordHistoryDao(database)
    }

    @Test
    fun `GIVEN new passwords WHEN getting password THEN equals existing`() = runTest {
        val passwordHistory = Faker.passwordHistory
        dao.insertPasswordHistory(passwordHistory)

        val result = dao.getAllPasswordHistory()

        result.test {
            val newPasswordHistory = awaitItem()

            assertThat(newPasswordHistory.size).isEqualTo(1)
        }
    }

    @Test
    fun `GIVEN new passwords WHEN delete password THEN equals not existing`() = runTest {
        val passwordHistory = Faker.passwordHistory
        dao.insertPasswordHistory(passwordHistory)

        dao.deletePasswordHistory(passwordHistory.id)
        val result = dao.getAllPasswordHistory()

        result.test {
            val newPasswordHistory = awaitItem()

            assertThat(newPasswordHistory.size).isEqualTo(0)
        }
    }

    @Test
    fun `GIVEN new history WHEN clear all passwords THEN not existing`() = runTest {
        val passwordHistory = Faker.passwordHistory
        dao.insertPasswordHistory(passwordHistory)

        dao.clearAllPasswordHistory()

        val result = dao.getAllPasswordHistory()

        result.test {
            val newPasswordHistory = awaitItem()

            assertThat(newPasswordHistory.size).isEqualTo(0)
        }
    }

}
