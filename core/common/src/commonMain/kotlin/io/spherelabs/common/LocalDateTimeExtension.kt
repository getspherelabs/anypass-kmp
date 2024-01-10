package io.spherelabs.common

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val Instant.timestamp: String
    get() {
        val timezone = TimeZone.currentSystemDefault()
        val datetime = this.toLocalDateTime(timezone)
        val year = datetime.year
        val month = datetime.monthNumber
        val dayOfMonth = datetime.dayOfMonth

        val hour = datetime.hour
        val minutes = datetime.minute
        val seconds = datetime.second

        return "$year/$month/$dayOfMonth $hour:$minutes:$seconds"
    }

val Instant.month: Int
    get() {
        val timezone = TimeZone.currentSystemDefault()
        val datetime = this.toLocalDateTime(timezone)
        return datetime.monthNumber
    }

fun Long.getMonth(): Int {
    return Instant.fromEpochMilliseconds(this).month
}

fun Long.formatLongTime(): String {
    return Instant.fromEpochMilliseconds(this).timestamp
}

