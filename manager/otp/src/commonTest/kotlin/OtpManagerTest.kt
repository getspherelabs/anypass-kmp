import assertk.assertThat
import assertk.assertions.hasLength
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import io.spherelabs.otp.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class OtpManagerTest {

    @Test
    fun `check the otp manager works properly`() = runTest {
        val secret = "JBSWY3DPEHPK3PXP"

        val timestamp = 1697096801L

        val config = OtpConfiguration(
            period = OtpDuration.Thirty,
            algorithmType = AlgorithmType.SHA1,
            digits = OtpDigits.SIX
        )
        val expectedOtp = "646759"

        val otpManager = DefaultOtpManager(secret, config)

        val result = otpManager.generate(timestamp)

        assertThat(expectedOtp).isEqualTo(result)
        assertThat(result).hasLength(6)
    }

    @Test
    fun `check the otp manager throws not valid secrets`() = runTest {
        val secret = "AnyPassSecret"

        val timestamp = 1697096801L

        val config = OtpConfiguration(
            period = OtpDuration.Thirty,
            algorithmType = AlgorithmType.SHA1,
            digits = OtpDigits.SIX
        )
        val expectedOtp = "646759"

        val otpManager = DefaultOtpManager(secret, config)

        val result = otpManager.generate(timestamp)

        assertThat(result).isNotEqualTo(expectedOtp)
    }
}
