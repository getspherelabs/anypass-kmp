package io.spherelabs.data.local.dao

import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import io.spherelabs.data.local.TestSqlDriverFactory
import io.spherelabs.data.local.db.adapter.OtpDigitColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpDurationColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpTypeColumnAdapter
import io.spherelabs.data.local.db.otp.dao.CounterDao
import io.spherelabs.data.local.db.otp.dao.DefaultCounterDao
import io.spherelabs.data.local.db.otp.dao.DefaultOtpDao
import io.spherelabs.data.local.db.otp.dao.OtpDao
import io.spherelabs.data.local.faker.Faker
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.CounterEntity
import io.spherelabs.local.db.OtpEntity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock

class CounterDaoTest {

    private lateinit var sqlDriverFactory: SqlDriver
    private lateinit var database: AnyPassDatabase
    private lateinit var otpDao: OtpDao
    private lateinit var counterDao: CounterDao

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
        otpDao = DefaultOtpDao(database)
        counterDao = DefaultCounterDao(database)
    }

    @Test
    fun `check the counter exists`() = runTest {
        val otp = Faker.otp

        otpDao.insertOtp(otp)
        counterDao.insertCounter(
            CounterEntity(
                otpId = otp.id,
                counter = 1,
            ),
        )

        val counterResult = counterDao.getCounters()

        counterResult.test {
            val newCounter = awaitItem()[0]

            assertThat("1").isEqualTo(newCounter.otpId)
        }
    }
}
