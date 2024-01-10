package io.spherelabs.passwordhistoryimpl.presentation

data class PasswordHistoryState(
    val history: List<UiPasswordHistory> = emptyList(),
) {
    companion object {
        val Empty = PasswordHistoryState()
    }
}
