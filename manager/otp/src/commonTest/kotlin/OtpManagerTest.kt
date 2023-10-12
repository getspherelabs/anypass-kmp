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

        assertEquals(expectedOtp, result)
    }
}
