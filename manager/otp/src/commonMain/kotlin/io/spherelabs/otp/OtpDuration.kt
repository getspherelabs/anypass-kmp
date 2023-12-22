package io.spherelabs.otp

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.plus

enum class OtpDuration(
    val value: Int,
) {
    Fifteen(15),
    Thirty(30),
    Sixty(60);

    val millis: Long = Instant
        .fromEpochMilliseconds(0L)
        .plus(value, DateTimeUnit.SECOND)
        .toEpochMilliseconds()
}
