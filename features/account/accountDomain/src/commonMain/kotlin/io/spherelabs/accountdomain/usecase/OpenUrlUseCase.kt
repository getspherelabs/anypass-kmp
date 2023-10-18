package io.spherelabs.accountdomain.usecase

import io.spherelabs.designsystem.url.BrowserNavigator

interface OpenUrlUseCase {
  suspend fun execute(url: String)
}

class DefaultOpenUrlUseCase(
  private val navigator: BrowserNavigator,
) : OpenUrlUseCase {
  override suspend fun execute(url: String) {
    navigator.openUrl(url)
  }
}
