package io.spherelabs.designsystem.pininput

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

object LKPinDefaults {

  @Composable
  fun style(
    elevation: Dp = LKPinTokens.Elevation,
    cursorColor: Color = LKPinTokens.CursorColor,
    cellsCount: Int = 4,
    obscureText: String = "*",
    cellModifier: Modifier = LKPinTokens.CellModifier,
    placeHolder: String = LKPinTokens.PlaceHolder,
    borderColor: Color = LKPinTokens.BorderColor,
  ): LKPinStyle {
    return LKPinStyle(
      elevation = elevation,
      cursorColor = cursorColor,
      cellCount = cellsCount,
      obscureText = obscureText,
      placeHolder = placeHolder,
      borderColor = borderColor,
      cellModifier = cellModifier
    )
  }
}
