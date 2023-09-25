package io.spherelabs.home.homepresentation

import io.spherelabs.home.homedomain.GetCategories
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest

class HomeMiddleware(private val getCategories: GetCategories) : Middleware<HomeState, HomeWish> {

  override suspend fun process(state: HomeState, wish: HomeWish, next: suspend (HomeWish) -> Unit) {
    when (wish) {
      HomeWish.GetStartedCategories -> {
        getCategories.execute().collectLatest { result ->
          next.invoke(HomeWish.GetCategories(result.map { it.asUi() }))
        }
      }
      else -> {}
    }
  }
}
