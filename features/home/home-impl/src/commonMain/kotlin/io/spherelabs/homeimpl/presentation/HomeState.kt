package io.spherelabs.homeimpl.presentation

import androidx.compose.runtime.Stable

@Stable
data class HomeState(
    val categories: List<HomeCategoryUi> = emptyList(),
    val passwords: List<HomePasswordUi> = emptyList(),
    val isVisible: Boolean = false
) {
  companion object {
    val Empty = HomeState()
  }
}
