package io.spherelabs.home.homepresentation

data class HomeState(
  val categories: List<HomeCategoryUi> = emptyList(),
  val passwords: List<HomePasswordUi> = emptyList()
) {
  companion object {
    val Empty = HomeState()
  }
}
