package io.spherelabs.designsystem.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Headline(
  text: String,
  fontFamily: FontFamily,
  modifier: Modifier = Modifier,
  textColor: Color = Color.White
) {
  Text(
    modifier = modifier.padding(start = 24.dp, top = 16.dp),
    text = text,
    fontSize = 32.sp,
    fontFamily = fontFamily,
    color = textColor
  )
}
