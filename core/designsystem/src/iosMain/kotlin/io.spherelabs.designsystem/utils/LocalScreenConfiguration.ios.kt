package io.spherelabs.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.IntSize
import kotlinx.cinterop.useContents
import platform.UIKit.UIScreen

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
@Composable
internal actual fun rememberScreenConfiguration(): ScreenConfiguration {
  val size =
      UIScreen.mainScreen.bounds.useContents { IntSize(size.width.toInt(), size.height.toInt()) }

  return remember { ScreenConfiguration(size.width, size.height, size = size) }
}

internal val LocalScreenConfiguration =
    compositionLocalOf<ScreenConfiguration> { throw IllegalStateException("Unused") }
