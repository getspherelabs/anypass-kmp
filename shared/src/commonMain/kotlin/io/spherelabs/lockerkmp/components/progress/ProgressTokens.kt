package io.spherelabs.lockerkmp.components.progress

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal object ProgressTokens {
    val FullSweepAngle = 360.dp
    val CirclePadding: Dp = 65.dp
    val OuterMarkerColor: Color = Color.White.copy(0.2f)
    val InnerMarkerColor: Color = Color.Black.copy(0.5f)
}