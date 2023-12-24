package passwordhealthimpl.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object SemiProgressBarDefaults {
  @Composable
  fun style(
      foregroundColor: Color = SemiProgressBarTokens.foregroundColor,
      backgroundColor: Color = SemiProgressBarTokens.backgroundColor,
  ): SemiProgressBarStyle {
    return SemiProgressBarStyle(foregroundColor, backgroundColor)
  }
}
