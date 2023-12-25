package io.spherelabs.data.settings.screenshot

import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow

interface RestrictScreenshotSettings {
    suspend fun setRestrictScreenshot(newValue: Boolean)

    fun getRestrictScreenshot(): Flow<Boolean>
}

class DefaultRestrictScreenshotSettings(
    private val settings: FlowSettings,
) : RestrictScreenshotSettings {
    override suspend fun setRestrictScreenshot(newValue: Boolean) {
        settings.putBoolean(PREFS_RESTRICT_SCREENSHOT, newValue)
    }

    override fun getRestrictScreenshot(): Flow<Boolean> {
        return settings.getBooleanFlow(PREFS_RESTRICT_SCREENSHOT, false)
    }

    companion object {
        private const val PREFS_RESTRICT_SCREENSHOT = "restrict_screenshot"
    }
}
