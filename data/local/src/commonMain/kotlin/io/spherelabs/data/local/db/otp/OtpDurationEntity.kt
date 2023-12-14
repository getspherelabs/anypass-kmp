package io.spherelabs.data.local.db.otp

import io.spherelabs.authenticatorapi.model.OtpDurationDomain
import io.spherelabs.newtokenapi.model.NewTokenDuration
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.plus

enum class OtpDurationEntity(
    val value: Long,
) {
    FIFTEEN(15),
    THIRTY(30),
    SIXTY(60);

    val millis: Long = Instant
        .fromEpochMilliseconds(0L)
        .plus(value, DateTimeUnit.SECOND)
        .toEpochMilliseconds()

    companion object {
        operator fun invoke(value: Long): OtpDurationEntity? {
            return fromRaw(value)
        }

        private fun fromRaw(value: Long): OtpDurationEntity? {
            return values().find { it.value == value }
        }
    }
}

fun OtpDurationEntity.asDomain(): OtpDurationDomain {
    return when (this) {
        OtpDurationEntity.FIFTEEN -> OtpDurationDomain.FIFTEEN
        OtpDurationEntity.THIRTY -> OtpDurationDomain.THIRTY
        OtpDurationEntity.SIXTY -> OtpDurationDomain.SIXTY
    }
}

fun OtpDurationDomain.asEntity(): OtpDurationEntity {
    return when (this) {
        OtpDurationDomain.FIFTEEN -> OtpDurationEntity.FIFTEEN
        OtpDurationDomain.THIRTY -> OtpDurationEntity.THIRTY
        OtpDurationDomain.SIXTY -> OtpDurationEntity.SIXTY
    }
}

fun NewTokenDuration.asEntity(): OtpDurationEntity {
    return when(this) {
        NewTokenDuration.FIFTEEN -> OtpDurationEntity.FIFTEEN
        NewTokenDuration.THIRTY -> OtpDurationEntity.THIRTY
        NewTokenDuration.SIXTY -> OtpDurationEntity.SIXTY
    }
}
