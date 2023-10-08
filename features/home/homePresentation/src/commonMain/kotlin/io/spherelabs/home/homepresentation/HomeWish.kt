package io.spherelabs.home.homepresentation

sealed interface HomeWish {
  object GetStartedCategories : HomeWish

  data class GetCategories(val categories: List<HomeCategoryUi>) : HomeWish

  data class GetStartedPasswordByCategory(val categoryId: String) : HomeWish

  data class GetPasswordByCategory(val passwords: List<HomePasswordUi>) : HomeWish

  data class OnPasswordsChanged(val passwords: List<HomePasswordUi>) : HomeWish

  data class OnCopyClipboardChanged(val password: String) : HomeWish
}
