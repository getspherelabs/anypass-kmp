package io.spherelabs.home.homepresentation

import io.spherelabs.home.homedomain.GetCategories
import io.spherelabs.home.homedomain.GetPasswordsByCategory
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest

class HomeMiddleware(
  private val getCategories: GetCategories,
  private val getPasswordsByCategory: GetPasswordsByCategory,
) : Middleware<HomeState, HomeWish> {

  override suspend fun process(
    state: HomeState,
    wish: HomeWish,
    next: suspend (HomeWish) -> Unit,
  ) {
    when (wish) {
      HomeWish.GetStartedCategories -> {
        handleGetCategories(next)
      }
      is HomeWish.GetStartedPasswordByCategory -> {
        handleGetStartedPasswordByCategories(wish.categoryId, next)
      }
      else -> {}
    }
  }

  private suspend inline fun handleGetCategories(noinline next: suspend (HomeWish) -> Unit) {
    getCategories.execute().collectLatest { result ->
      val newCategory = result.map { currentCategory -> currentCategory.asUi() }
      next.invoke(HomeWish.GetCategories(newCategory))
    }
  }

  private suspend inline fun handleGetStartedPasswordByCategories(
    categoryId: String,
    noinline next: suspend (HomeWish) -> Unit,
  ) {
    getPasswordsByCategory.execute(categoryId).collectLatest { result ->
      val newPassword = result.map { currentPassword -> currentPassword.asUi() }
      next.invoke(
        HomeWish.GetPasswordByCategory(newPassword),
      )
    }
  }
}
