package io.spherelabs.data.settings.onboarding

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings


interface OnboardingSetting {
    suspend fun setIsFirstTime(value: Boolean)
    suspend fun isFirstTime(): Boolean
}

class DefaultOnboardingSetting(
    private val settings: FlowSettings
) : OnboardingSetting {

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun setIsFirstTime(value: Boolean) {
        println("SharedPref value: $value")
        settings.putBoolean(FIRST_TIME, value)
    }

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun isFirstTime(): Boolean {
        println("SharedPref value: ${settings.getBoolean(FIRST_TIME, false)}")
        return settings.getBoolean(FIRST_TIME, false)
    }

    companion object {
        private const val FIRST_TIME = "first_time"
    }
}