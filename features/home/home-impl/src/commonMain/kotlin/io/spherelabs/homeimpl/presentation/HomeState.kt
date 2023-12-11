package io.spherelabs.homeimpl.presentation

data class HomeState(
    val categories: List<HomeCategoryUi> = emptyList(),
    val passwords: List<HomePasswordUi> = emptyList()
) {
  companion object {
    val Empty = HomeState()
  }
}
