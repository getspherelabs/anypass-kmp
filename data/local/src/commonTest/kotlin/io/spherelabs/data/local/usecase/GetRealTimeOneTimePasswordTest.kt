package io.spherelabs.data.local.usecase

import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import io.spherelabs.authenticatordomain.model.AlgorithmTypeDomain
import io.spherelabs.authenticatordomain.model.OtpDigitDomain
import io.spherelabs.authenticatordomain.model.OtpDomain
import io.spherelabs.authenticatordomain.model.OtpDurationDomain
import io.spherelabs.authenticatordomain.repository.AuthenticatorRepository
import io.spherelabs.authenticatordomain.usecase.DefaultGetRealTimeOneTimePassword
import io.spherelabs.authenticatordomain.usecase.GetRealTimeOneTimePassword
import io.spherelabs.data.local.TestSqlDriverFactory
import io.spherelabs.data.local.db.adapter.OtpDigitColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpDurationColumnAdapter
import io.spherelabs.data.local.db.adapter.OtpTypeColumnAdapter
import io.spherelabs.data.local.db.otp.dao.CounterDao
import io.spherelabs.data.local.db.otp.dao.DefaultCounterDao
import io.spherelabs.data.local.db.otp.dao.DefaultOtpDao
import io.spherelabs.data.local.db.otp.dao.OtpDao
import io.spherelabs.data.local.repository.DefaultAuthenticatorRepository
import io.spherelabs.local.db.AnyPassDatabase
import io.spherelabs.local.db.OtpEntity
import io.spherelabs.otp.DefaultOtpManager
import io.spherelabs.otp.OtpManager
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock

class GetRealTimeOneTimePasswordTest {

    private lateinit var sqlDriverFactory: SqlDriver
    private lateinit var database: AnyPassDatabase
    private lateinit var counterDao: CounterDao
    private lateinit var otpDao: OtpDao
    private lateinit var repository: AuthenticatorRepository
    private lateinit var useCase: GetRealTimeOneTimePassword
    private lateinit var otpManager: OtpManager

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
        otpManager = DefaultOtpManager()
        useCase = DefaultGetRealTimeOneTimePassword(repository, otpManager)
    }

    @Test
    fun `check the insert otp data and fetch real time one time password`() = runTest {
        val timestamp = 1697096801L
        val expectedOtp = "646759"

        val otp = OtpDomain(
            id = "2",
            duration = OtpDurationDomain.THIRTY,
            type = AlgorithmTypeDomain.SHA1,
            digit = OtpDigitDomain.SIX,
            secret = "JBSWY3DPEHPK3PXP",
            info = "Behance",
            issuer = "Behance",
            createdTimestamp = Clock.System.now().toEpochMilliseconds(),
            serviceName = "Behance"
        )

        repository.insertOtpWithCount(
            otpDomain = otp,
            timestamp
        )

        val result = useCase.execute()

        result.test {
            val newResult = awaitItem()

            assertThat(newResult["2"]?.code).isEqualTo(expectedOtp)
            assertThat(newResult).isNotNull()
            cancelAndIgnoreRemainingEvents()
        }
    }

}
