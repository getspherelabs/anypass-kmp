package io.spherelabs.passwordhistoryimpl.presentation

data class PasswordHistoryState(
    val history: List<UiPasswordHistory> = emptyList(),
    val isPasswordHidden: Boolean = false,
) {
    companion object {
        val Empty = PasswordHistoryState()
    }
}
