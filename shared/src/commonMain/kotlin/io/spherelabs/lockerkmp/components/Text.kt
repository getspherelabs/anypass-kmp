package io.spherelabs.lockerkmp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.spherelabs.lockerkmp.MR

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
