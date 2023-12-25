package io.spherelabs.accountimpl.domain

import io.spherelabs.accountapi.domain.usecase.SetRestrictScreenshotUseCase
import io.spherelabs.data.settings.screenshot.RestrictScreenshotSettings

class DefaultSetRestrictScreenshotUseCase(
    private val settings: RestrictScreenshotSettings,
) : SetRestrictScreenshotUseCase {

    override suspend fun execute(newValue: Boolean) {
        settings.setRestrictScreenshot(newValue)
    }
}
