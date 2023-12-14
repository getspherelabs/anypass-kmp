package io.spherelabs.authenticatorimpl.mapper

import io.spherelabs.authenticatorapi.model.AlgorithmTypeDomain
import io.spherelabs.authenticatorapi.model.OtpDigitDomain
import io.spherelabs.authenticatorapi.model.OtpDurationDomain
import io.spherelabs.otp.AlgorithmType
import io.spherelabs.otp.OtpDigits
import io.spherelabs.otp.OtpDuration

fun OtpDurationDomain.asManager(): OtpDuration {
    return when (this) {
        OtpDurationDomain.FIFTEEN -> OtpDuration.Fifteen
        OtpDurationDomain.THIRTY -> OtpDuration.Thirty
        OtpDurationDomain.SIXTY -> OtpDuration.Sixty
    }
}

fun OtpDigitDomain.asManager(): OtpDigits {
    return when (this) {
        OtpDigitDomain.SIX -> OtpDigits.SIX
        OtpDigitDomain.EIGHT -> OtpDigits.EIGHT
    }
}

fun AlgorithmTypeDomain.asManager(): AlgorithmType {
    return when (this) {
        AlgorithmTypeDomain.SHA1 -> AlgorithmType.SHA1
        AlgorithmTypeDomain.SHA256 -> AlgorithmType.SHA256
        AlgorithmTypeDomain.SHA512 -> AlgorithmType.SHA512
    }
}
