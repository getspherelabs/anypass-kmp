package io.spherelabs.accountdomain.repository

import io.spherelabs.designsystem.url.BrowserNavigator

interface OpenUrl {
    suspend fun execute(url: String)
}

class DefaultOpenUrl(
    private val navigator: BrowserNavigator,
) : OpenUrl {
    override suspend fun execute(url: String) {
        navigator.openUrl(url)
    }
}
