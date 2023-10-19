package io.spherelabs.data.local.db.otp

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.plus

enum class OtpDurationEntity(
    private val value: Int,
    private val unit: DateTimeUnit.TimeBased,
) {
    Fifteen(15, DateTimeUnit.SECOND),
    Thirty(30, DateTimeUnit.SECOND),
    Sixty(60, DateTimeUnit.SECOND);

    val millis: Long = Instant
        .fromEpochMilliseconds(0L)
        .plus(value, unit)
        .toEpochMilliseconds()
}
