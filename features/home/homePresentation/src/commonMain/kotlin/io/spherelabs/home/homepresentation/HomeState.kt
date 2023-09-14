package io.spherelabs.home.homepresentation

data class HomeState(
    val categories: List<HomeCategoryUi> = emptyList()
) {
    companion object {
        val Empty = HomeState()
    }
}
