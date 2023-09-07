package io.spherelabs.lockerkmp.components.progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun Tissot(
    value: Int,
    modifier: Modifier = Modifier
) {
    val nbMarker = 180

    val progressAngle by animateFloatAsState(
        targetValue = 180f / 30f * value,
        animationSpec = tween(500)
    )

    Box(
        modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        OuterProgress(modifier = modifier)
        CircleProgress(modifier = modifier, angle = progressAngle)
        TissotProgress(modifier = modifier)
        InnerProgress(modifier = modifier)
    }
}