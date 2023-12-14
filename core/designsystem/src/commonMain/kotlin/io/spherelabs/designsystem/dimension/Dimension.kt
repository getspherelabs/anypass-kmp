package io.spherelabs.designsystem.dimension

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val tiny: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val xLarge: Dp = 32.dp,
    val xxLarge: Dp = 40.dp,
    val xxxLarge: Dp = 48.dp,
    val divider: Dp = 1.dp,
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }
