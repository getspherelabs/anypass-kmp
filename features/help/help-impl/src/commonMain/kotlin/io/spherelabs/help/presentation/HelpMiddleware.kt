package io.spherelabs.help.presentation

import io.spherelabs.domain.usecase.GetFAQsUseCase
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest

class HelpMiddleware(
    private val getFAQsUseCase: GetFAQsUseCase,
) : Middleware<HelpState, HelpWish> {

  override suspend fun process(
      state: HelpState,
      wish: HelpWish,
      next: suspend (HelpWish) -> Unit,
  ) {
    when (wish) {
      HelpWish.StartLoadingGetFaqs -> {
        handleLoadedGetFaqs(next)
      }
      else -> {}
    }
  }

  private suspend fun handleLoadedGetFaqs(next: suspend (HelpWish) -> Unit) {
    getFAQsUseCase.execute().collectLatest { faqs ->
      println("FAQs: Loaded faqs are $faqs")
      next.invoke(HelpWish.LoadedGetFaqs(faqs))
    }
  }
}
