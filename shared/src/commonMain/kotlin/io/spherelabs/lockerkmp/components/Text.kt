package io.spherelabs.lockerkmp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.spherelabs.lockerkmp.MR
import kotlin.math.roundToInt

@Composable
fun Headline(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
    Text(
        modifier = modifier.padding(start = 24.dp, top = 16.dp),
        text = text,
        fontSize = 32.sp,
        fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
        color = textColor
    )
}

