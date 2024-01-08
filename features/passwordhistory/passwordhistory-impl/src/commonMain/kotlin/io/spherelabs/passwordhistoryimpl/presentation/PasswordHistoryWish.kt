package io.spherelabs.passwordhistoryimpl.presentation

sealed interface PasswordHistoryWish {
    object StartLoadingPasswordHistory : PasswordHistoryWish
    data class LoadedPasswordHistory(val data: List<UiPasswordHistory>) : PasswordHistoryWish
    object OnClearPasswordHistory : PasswordHistoryWish
    object OnToggleVisibility : PasswordHistoryWish
    data class Failure(val message: String) : PasswordHistoryWish
}
