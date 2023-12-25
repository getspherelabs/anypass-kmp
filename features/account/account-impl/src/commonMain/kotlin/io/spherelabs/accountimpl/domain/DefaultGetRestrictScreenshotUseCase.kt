package io.spherelabs.accountimpl.domain

import io.spherelabs.accountapi.domain.usecase.GetRestrictScreenshotUseCase
import io.spherelabs.data.settings.screenshot.RestrictScreenshotSettings
import kotlinx.coroutines.flow.Flow

class DefaultGetRestrictScreenshotUseCase(
    private val settings: RestrictScreenshotSettings,
) : GetRestrictScreenshotUseCase {

    override fun execute(): Flow<Boolean>  {
        return settings.getRestrictScreenshot()
    }
}
