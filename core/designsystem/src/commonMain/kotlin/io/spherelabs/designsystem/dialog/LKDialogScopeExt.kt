package io.spherelabs.designsystem.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun LKDialogScope.title(
    text: String,
    color: Color = Color.White,
    style: TextStyle = MaterialTheme.typography.h6,
    center: Boolean = false
) {
  var modifier =
      Modifier.fillMaxWidth()
          .padding(start = 24.dp, end = 24.dp)
          .height(64.dp)
          .wrapContentHeight(Alignment.CenterVertically)

  modifier =
      modifier.then(
          Modifier.wrapContentWidth(
              if (center) {
                Alignment.CenterHorizontally
              } else {
                Alignment.Start
              }))

  Text(text = text, color = color, style = style, modifier = modifier)
}
