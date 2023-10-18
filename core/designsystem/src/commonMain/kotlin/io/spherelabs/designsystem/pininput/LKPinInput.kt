package io.spherelabs.designsystem.pininput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun LKPinInput(
  modifier: Modifier = Modifier,
  cellModifier: Modifier = Modifier,
  cellColor: Color = Color.White,
  style: LKPinStyle = LKPinDefaults.style(),
  value: String? = null,
  disableKeypad: Boolean = false,
  onValueChanged: (String) -> Unit
) {
  val length by style.cellCount()
  val focusRequester = remember { FocusRequester() }

  TextField(
    readOnly = disableKeypad,
    value = value ?: "",
    onValueChange = {
      if (it.length <= length) {
        if (it.all { c -> c in '0'..'9' }) {
          onValueChanged(it)
        }
      }
    },
    modifier = Modifier.size(0.dp).focusRequester(focusRequester),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
  )

  Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
    repeat(length) {
      LKCell(
        modifier =
          cellModifier.size(75.dp).clip(RoundedCornerShape(18.dp)).background(color = cellColor),
        value = value?.getOrNull(it),
        isCursorVisible = if (value == null) false else value.length == it,
        style.obscureText().value
      )
      if (it != length - 1) Spacer(modifier = Modifier.size(8.dp))
    }
  }
}
