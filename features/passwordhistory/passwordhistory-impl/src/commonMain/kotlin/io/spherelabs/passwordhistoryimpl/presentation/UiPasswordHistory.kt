package io.spherelabs.passwordhistoryimpl.presentation

import io.spherelabs.common.getMonth
import io.spherelabs.passwordhistoryapi.model.PasswordHistory

data class UiPasswordHistory(
    val id: String,
    val createdAt: Long,
    val password: String,
    val month: Month,
)

enum class Month(
    private val rawValue: Int,
) {
    January(1),
    February(2),
    March(3),
    April(4),
    May(6),
    June(6),
    July(7),
    August(8),
    September(9),
    October(10),
    November(11),
    December(12);

    companion object {
        fun toMonth(rawValue: Int): Month {
            return values().first { it.rawValue == rawValue }
        }
    }

}

fun PasswordHistory.toUi(): UiPasswordHistory {
    val month: Month = Month.toMonth(createdAt.getMonth())
    return UiPasswordHistory(id, createdAt, password, month)
}

fun UiPasswordHistory.toDomain(): PasswordHistory {
    return PasswordHistory(id, createdAt, password)
}
