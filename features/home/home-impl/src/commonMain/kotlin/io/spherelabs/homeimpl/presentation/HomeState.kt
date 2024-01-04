package io.spherelabs.homeimpl.presentation

import androidx.compose.runtime.Immutable

@Immutable
data class HomeState(
    val categories: List<UIHomeCategory> = emptyList(),
    val passwords: List<UIHomePassword> = emptyList(),
    val isVisible: Boolean = false,
    val isHidden: Boolean = true,
) {
    companion object {
        val Empty = HomeState()
    }
}
