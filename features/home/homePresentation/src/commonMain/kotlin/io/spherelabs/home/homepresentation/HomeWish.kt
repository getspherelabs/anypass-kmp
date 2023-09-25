package io.spherelabs.home.homepresentation

sealed interface HomeWish {
  object GetStartedCategories : HomeWish

  data class GetCategories(val categories: List<HomeCategoryUi>) : HomeWish
}
