package io.spherelabs.data.settings.onboarding

import com.russhwolf.settings.coroutines.FlowSettings

interface OnboardingSetting {
  suspend fun setIsFirstTime(value: Boolean)

  suspend fun isFirstTime(): Boolean
}

class DefaultOnboardingSetting(private val settings: FlowSettings) : OnboardingSetting {

  override suspend fun setIsFirstTime(value: Boolean) {
    settings.putBoolean(FIRST_TIME, value)
  }

  override suspend fun isFirstTime(): Boolean {
    return settings.getBoolean(FIRST_TIME, false)
  }

  companion object {
    private const val FIRST_TIME = "first_time"
  }
}
