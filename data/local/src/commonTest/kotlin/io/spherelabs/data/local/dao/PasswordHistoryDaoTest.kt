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
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock

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
        dao.insertPasswordHistory("wewepo23a", Clock.System.now().toEpochMilliseconds())

        val result = dao.getAllPasswordHistory()

        result.test {
            val newPasswordHistory = awaitItem()

            assertThat(newPasswordHistory.size).isEqualTo(1)
        }
    }

    @Test
    fun `GIVEN new passwords WHEN delete password THEN equals not existing`() = runTest {
        val id = dao.insertPasswordHistory(
            password = "wewepo23a",
            createdAt = Clock.System.now().toEpochMilliseconds(),
        )

        dao.deletePasswordHistory(id)
        val result = dao.getAllPasswordHistory()

        result.test {
            val newPasswordHistory = awaitItem()

            assertThat(newPasswordHistory.size).isEqualTo(0)
        }
    }

    @Test
    fun `GIVEN new history WHEN clear all passwords THEN not existing`() = runTest {
        dao.insertPasswordHistory("wewepo23a", Clock.System.now().toEpochMilliseconds())
        dao.clearAllPasswordHistory()

        val result = dao.getAllPasswordHistory()

        result.test {
            val newPasswordHistory = awaitItem()

            assertThat(newPasswordHistory.size).isEqualTo(0)
        }
    }

}
