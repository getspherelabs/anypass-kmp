package io.spherelabs.changepasswordimpl.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
internal fun UpdateKeyPasswordButton(
    modifier: Modifier = Modifier,
    onUpdateClicked: () -> Unit,
) {
  val strings = LocalStrings.current
  Button(
      modifier = modifier.fillMaxWidth().height(65.dp).padding(start = 24.dp, end = 24.dp),
      colors =
          ButtonDefaults.buttonColors(
              backgroundColor = LavenderBlue.copy(0.7f),
          ),
      shape = RoundedCornerShape(24.dp),
      onClick = { onUpdateClicked.invoke() },
  ) {
    Text(
        text = strings.updateKeyPassword,
        color = Color.White,
        fontSize = 18.sp,
        fontFamily = GoogleSansFontFamily,
        fontWeight = FontWeight.Medium,
    )
  }
}
