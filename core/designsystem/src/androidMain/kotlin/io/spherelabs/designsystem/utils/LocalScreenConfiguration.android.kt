package io.spherelabs.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntSize

@Composable
internal actual fun rememberScreenConfiguration(): ScreenConfiguration {
  val config = LocalConfiguration.current

  return remember(config.screenWidthDp, config.screenHeightDp) {
    ScreenConfiguration(
        screenWidthDp = config.screenWidthDp,
        screenHeightDp = config.screenHeightDp,
        size = IntSize(config.screenWidthDp, config.screenHeightDp))
  }
}
