package io.spherelabs.data.local.dao

import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import io.spherelabs.data.local.TestSqlDriverFactory
import io.spherelabs.data.local.db.adapter.OtpDigitColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpDurationColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpTypeColumnAdapter
import io.spherelabs.data.local.db.otp.AlgorithmTypeEntity
import io.spherelabs.data.local.db.otp.OtpDigitEntity
import io.spherelabs.data.local.db.otp.OtpDurationEntity
import io.spherelabs.data.local.db.otp.dao.DefaultOtpDao
import io.spherelabs.data.local.db.otp.dao.OtpDao
import io.spherelabs.data.local.faker.Faker
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock

class OtpDaoTest {

    private lateinit var sqlDriverFactory: SqlDriver
    private lateinit var database: AnyPassDatabase
    private lateinit var otpDao: OtpDao

    @BeforeTest
    fun setup() {
        sqlDriverFactory = TestSqlDriverFactory().createDriver()
        database = AnyPassDatabase(sqlDriverFactory,
            OtpEntity.Adapter(
                digitAdapter = OtpDigitColumnAdapter(),
                durationAdapter = OtpDurationColumnAdapter(),
                typeAdapter = OtpTypeColumnAdapter()
            ))
       otpDao = DefaultOtpDao(database)
    }

    @Test
    fun `check insert otp and get otp`() = runTest {
        val otp = Faker.otp

        otpDao.insertOtp(otp)

        val result = otpDao.getAllOtp()

        result.test {
            val newOtp = awaitItem()

            assertThat(1).isEqualTo(newOtp.size)
            assertThat(OtpDigitEntity.SIX).isEqualTo(newOtp[0].digit)
            assertThat(OtpDurationEntity.Thirty).isNotEqualTo(newOtp[0].duration)
        }
    }
}
