package io.spherelabs.data.local.db.otp

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.plus

enum class OtpDurationEntity(
    val value: Long,
) {
    Fifteen(15),
    Thirty(30),
    Sixty(60);

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
