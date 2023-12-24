package io.spherelabs.designsystem.slider

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object LKSliderDefaults {

  @Composable
  fun sliderColors(
      thumbColor: Color = LKSliderTokens.ThumbColor,
      trackColor: Color = LKSliderTokens.TrackColor,
      tickColor: Color = LKSliderTokens.TickColor,
      disabledThumbColor: Color = LKSliderTokens.DisabledThumbColor,
      disabledTrackColor: Color = LKSliderTokens.DisabledTrackColor,
      disabledTickColor: Color = LKSliderTokens.DisabledTickColor
  ): LKSliderColors {
    return LKSliderColors(
        thumbColor = thumbColor,
        trackColor = trackColor,
        tickColor = tickColor,
        disabledThumbColor = disabledThumbColor,
        disabledTrackColor = disabledTrackColor,
        disabledTickColor = disabledTickColor)
  }
}
