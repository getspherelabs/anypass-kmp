package io.spherelabs.homeimpl.presentation

import androidx.compose.runtime.Immutable

@Immutable
data class HomeState(
    val categories: List<HomeCategoryUi> = emptyList(),
    val passwords: List<HomePasswordUi> = emptyList(),
    val isVisible: Boolean = false
) {
  companion object {
    val Empty = HomeState()
  }
}
