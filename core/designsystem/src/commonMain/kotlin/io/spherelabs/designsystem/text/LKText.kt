package io.spherelabs.designsystem.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Headline(
    text: String,
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    fontSize: TextUnit = 24.sp,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
) {
  Text(
      modifier = modifier.padding(start = 16.dp),
      text = text,
      fontSize = fontSize,
      fontFamily = fontFamily,
      color = textColor,
      fontWeight = fontWeight,
      textAlign = TextAlign.Center,
  )
}
