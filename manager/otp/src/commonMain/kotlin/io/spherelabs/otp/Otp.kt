package io.spherelabs.otp


data class Otp(
    val serviceName: String?,
    val serviceKey: String,
    val configuration: OtpConfiguration,
    val uuid: String,
)

enum class OtpDigits(val number: Int) {
    SIX(6),
    EIGHT(8)
}

class OtpConfiguration(
    val period: OtpDuration,
    val digits: OtpDigits,
    val algorithmType: AlgorithmType,
) {
    companion object {
        val Default: OtpConfiguration
            get() {
                return OtpConfiguration(
                    period = OtpDuration.Fifteen,
                    digits = OtpDigits.SIX,
                    algorithmType = AlgorithmType.SHA256,
                )
            }
    }
}

enum class AlgorithmType {
    SHA1,
    SHA256,
    SHA512
}
