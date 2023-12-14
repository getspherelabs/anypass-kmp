package io.spherelabs.homeimpl.presentation

import io.spherelabs.homeapi.domain.usecase.GetCategoriesUseCase
import io.spherelabs.homeapi.domain.usecase.GetPasswordsByCategoryUseCase
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest

class HomeMiddleware(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getPasswordsByCategoryUseCase: GetPasswordsByCategoryUseCase,
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
    getCategoriesUseCase.execute().collectLatest { result ->
      val newCategory = result.map { currentCategory -> currentCategory.asUi() }
      next.invoke(HomeWish.GetCategories(newCategory))
    }
  }

  private suspend inline fun handleGetStartedPasswordByCategories(
      categoryId: String,
      noinline next: suspend (HomeWish) -> Unit,
  ) {
    getPasswordsByCategoryUseCase.execute(categoryId).collectLatest { result ->
      val newPassword = result.map { currentPassword -> currentPassword.asUi() }
      next.invoke(
          HomeWish.GetPasswordByCategory(newPassword),
      )
    }
  }
}
