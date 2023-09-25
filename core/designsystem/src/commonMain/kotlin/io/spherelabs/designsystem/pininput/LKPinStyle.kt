package io.spherelabs.designsystem.pininput

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import io.spherelabs.designsystem.hooks.useUpdatedState

@Immutable
class LKPinStyle(
  private val elevation: Dp,
  private val borderColor: Color,
  private val cursorColor: Color,
  private val cellModifier: Modifier,
  private val placeHolder: String,
  private val obscureText: String,
  private val cellCount: Int
) {
  @Composable
  internal fun borderColor(): State<Color> {
    return useUpdatedState(borderColor)
  }

  @Composable
  internal fun cursorColor(): State<Color> {
    return useUpdatedState(cursorColor)
  }

  @Composable
  internal fun placeHolder(): State<String> {
    return useUpdatedState(placeHolder)
  }

  @Composable
  internal fun obscureText(): State<String> {
    return useUpdatedState(obscureText)
  }

  @Composable
  fun cellCount(): State<Int> {
    return useUpdatedState(cellCount)
  }
}
