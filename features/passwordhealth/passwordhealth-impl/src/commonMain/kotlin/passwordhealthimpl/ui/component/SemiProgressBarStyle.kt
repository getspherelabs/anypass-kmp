package passwordhealthimpl.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

@Immutable
class SemiProgressBarStyle(
    private val foregroundColor: Color = SemiProgressBarTokens.foregroundColor,
    private val backgroundColor: Color = SemiProgressBarTokens.backgroundColor,
) {
  @Composable
  fun foregroundColor(): State<Color> {
    return rememberUpdatedState(foregroundColor)
  }

  @Composable
  fun backgroundColor(): State<Color> {
    return rememberUpdatedState(backgroundColor)
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || other !is SemiProgressBarStyle) return false

    if (this.foregroundColor != other.foregroundColor) return false
    if (this.backgroundColor != other.backgroundColor) return false

    return true
  }

  override fun hashCode(): Int {
    var result = foregroundColor.hashCode()

    result = 31 * result + backgroundColor.hashCode()
    return result
  }
}
