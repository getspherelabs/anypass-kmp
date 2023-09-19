package io.spherelabs.designsystem.utils


import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntSize

public class ScreenConfiguration(val screenWidthDp: Int, val screenHeightDp: Int,  val size: IntSize)


@Composable
internal expect fun rememberScreenConfiguration(): ScreenConfiguration


