package io.spherelabs.data.local.db.otp

import io.spherelabs.authenticatorapi.model.OtpDigitDomain
import io.spherelabs.newtokenapi.model.NewTokenDigit

enum class OtpDigitEntity(val number: Long) {
    SIX(6),
    EIGHT(8);

    companion object {
        operator fun invoke(number: Long): OtpDigitEntity? {
            return fromRaw(number)
        }

        private fun fromRaw(number: Long): OtpDigitEntity? {
            return values().find { it.number == number }
        }


    }
}

fun OtpDigitEntity.asDomain(): OtpDigitDomain {
    return when (this) {
        OtpDigitEntity.SIX -> OtpDigitDomain.SIX
        OtpDigitEntity.EIGHT -> OtpDigitDomain.EIGHT
    }
}

fun OtpDigitDomain.asEntity(): OtpDigitEntity {
    return when (this) {
        OtpDigitDomain.SIX -> OtpDigitEntity.SIX
        OtpDigitDomain.EIGHT -> OtpDigitEntity.EIGHT
    }
}


fun NewTokenDigit.asEntity(): OtpDigitEntity {
    return when (this) {
        NewTokenDigit.SIX -> OtpDigitEntity.SIX
        NewTokenDigit.EIGHT -> OtpDigitEntity.EIGHT
    }
}
