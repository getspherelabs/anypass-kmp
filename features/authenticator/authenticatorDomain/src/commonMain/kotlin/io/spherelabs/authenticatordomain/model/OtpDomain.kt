package io.spherelabs.authenticatordomain.model

import io.spherelabs.otp.AlgorithmType
import io.spherelabs.otp.OtpDigits
import io.spherelabs.otp.OtpDuration


data class OtpDomain(
    val id: String,
    val secret: String,
    val type: AlgorithmTypeDomain,
    val issuer: String?,
    val info: String?,
    val duration: OtpDurationDomain,
    val digit: OtpDigitDomain,
    val createdTimestamp: Long,
    val serviceName: String?,
)


enum class AlgorithmTypeDomain {
    SHA1,
    SHA256,
    SHA512;
}

fun AlgorithmTypeDomain.asManager(): AlgorithmType {
    return when (this) {
        AlgorithmTypeDomain.SHA1 -> AlgorithmType.SHA1
        AlgorithmTypeDomain.SHA256 -> AlgorithmType.SHA256
        AlgorithmTypeDomain.SHA512 -> AlgorithmType.SHA512
    }
}

enum class OtpDurationDomain(
    val value: Long,
) {
    FIFTEEN(15),
    THIRTY(30),
    SIXTY(60);
}

fun OtpDurationDomain.asManager(): OtpDuration {
    return when (this) {
        OtpDurationDomain.FIFTEEN -> OtpDuration.Fifteen
        OtpDurationDomain.THIRTY -> OtpDuration.Thirty
        OtpDurationDomain.SIXTY -> OtpDuration.Sixty
    }
}

enum class OtpDigitDomain(val number: Long) {
    SIX(6),
    EIGHT(8);
}

fun OtpDigitDomain.asManager(): OtpDigits {
    return when (this) {
        OtpDigitDomain.SIX -> OtpDigits.SIX
        OtpDigitDomain.EIGHT -> OtpDigits.EIGHT
    }
}
