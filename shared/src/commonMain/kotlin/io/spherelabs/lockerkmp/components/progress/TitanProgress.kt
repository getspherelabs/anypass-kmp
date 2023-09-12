package io.spherelabs.lockerkmp.components.progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.spherelabs.lockerkmp.MR
import io.spherelabs.lockerkmp.ui.createpassword.purpleDark

@Composable
internal fun TissotProgress(
    value: Int,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(105.dp)
            .background(color = colorResource(MR.colors.dynamic_yellow), shape = CircleShape),
    ) {

        Text(
            text = "$value",
            fontFamily = fontFamilyResource(MR.fonts.googlesans.bold),
            fontSize = 42.sp,
            color = purpleDark,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}