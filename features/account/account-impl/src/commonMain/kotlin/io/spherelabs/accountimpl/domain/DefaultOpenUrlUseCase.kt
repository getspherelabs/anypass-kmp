package io.spherelabs.accountimpl.domain

import io.spherelabs.accountapi.domain.usecase.OpenUrlUseCase
import io.spherelabs.designsystem.url.BrowserNavigator


class DefaultOpenUrlUseCase(
  private val navigator: BrowserNavigator,
) : OpenUrlUseCase {
  override suspend fun execute(url: String) {
    navigator.openUrl(url)
  }
}
