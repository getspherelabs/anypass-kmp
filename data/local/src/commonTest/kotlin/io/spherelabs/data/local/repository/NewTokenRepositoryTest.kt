package io.spherelabs.data.local.repository

import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isNotEqualTo
import assertk.assertions.isNotNull
import io.spherelabs.authenticatordomain.model.OtpDigitDomain
import io.spherelabs.authenticatordomain.repository.AuthenticatorRepository
import io.spherelabs.data.local.TestSqlDriverFactory
import io.spherelabs.data.local.db.adapter.OtpDigitColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpDurationColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpTypeColumnAdapter
import io.spherelabs.data.local.db.otp.dao.CounterDao
import io.spherelabs.data.local.db.otp.dao.DefaultCounterDao
import io.spherelabs.data.local.db.otp.dao.DefaultOtpDao
import io.spherelabs.data.local.db.otp.dao.OtpDao
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity
import io.spherelabs.newtokendomain.model.NewTokenDigit
import io.spherelabs.newtokendomain.model.NewTokenDomain
import io.spherelabs.newtokendomain.model.NewTokenDuration
import io.spherelabs.newtokendomain.model.NewTokenType
import io.spherelabs.newtokendomain.repository.NewTokenRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock

class NewTokenRepositoryTest {

    private lateinit var sqlDriverFactory: SqlDriver
    private lateinit var database: AnyPassDatabase
    private lateinit var counterDao: CounterDao
    private lateinit var otpDao: OtpDao
    private lateinit var repository: AuthenticatorRepository
    private lateinit var newTokenRepository: NewTokenRepository

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
        counterDao = DefaultCounterDao(database)
        otpDao = DefaultOtpDao(database)
        repository = DefaultAuthenticatorRepository(otpDao = otpDao, counterDao = counterDao)
        newTokenRepository = DefaultNewTokenRepository(otpDao = otpDao, counterDao = counterDao)
    }

    @Test
    fun `check the insert otp and counter get otp result`() = runTest {
        val data = NewTokenDomain(
            id = "1",
            createdTimestamp = Clock.System.now().toEpochMilliseconds(),
            digit = NewTokenDigit.SIX,
            info = "Behance",
            duration = NewTokenDuration.FIFTEEN,
            issuer = "Behance",
            secret = "XSKSAdkShrhruHDHJDS",
            serviceName = "Behance",
            type = NewTokenType.SHA1,
        )
        val timestamp = 1697096801L


        newTokenRepository.insertOtpWithCount(data, timestamp)

        val otpResult = repository.getAllOtp()

        otpResult.test {
            val otp = awaitItem()

            assertThat(otp[0]).isNotNull()
            assertThat(otp[0]).isNotEqualTo(OtpDigitDomain.EIGHT)
        }
    }

    @Test
    fun `check the insert otp and counter get counter result`() = runTest {
        val data = NewTokenDomain(
            id = "1",
            createdTimestamp = Clock.System.now().toEpochMilliseconds(),
            digit = NewTokenDigit.SIX,
            info = "Behance",
            duration = NewTokenDuration.FIFTEEN,
            issuer = "Behance",
            secret = "XSKSAdkShrhruHDHJDS",
            serviceName = "Behance",
            type = NewTokenType.SHA1,
        )
        val timestamp = 1697096801L


        newTokenRepository.insertOtpWithCount(data, timestamp)

        val counterResult = repository.getCounters()

        counterResult.test {
            val counter = awaitItem()


            assertThat(counter[0]).isNotNull()
            assertThat(counter[0]).isNotEqualTo(OtpDigitDomain.EIGHT)
        }
    }

}
