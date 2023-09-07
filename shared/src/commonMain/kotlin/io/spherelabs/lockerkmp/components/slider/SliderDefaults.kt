package io.spherelabs.lockerkmp.components.slider

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object SliderDefaults {

    @Composable
    fun sliderColors(
        thumbColor: Color = SliderTokens.ThumbColor,
        trackColor: Color = SliderTokens.TrackColor,
        tickColor: Color = SliderTokens.TickColor,
        disabledThumbColor: Color = SliderTokens.DisabledThumbColor,
        disabledTrackColor: Color = SliderTokens.DisabledTrackColor,
        disabledTickColor: Color = SliderTokens.DisabledTickColor
    ): SliderColors {
        return SliderColors(
            thumbColor = thumbColor,
            trackColor = trackColor,
            tickColor = tickColor,
            disabledThumbColor = disabledThumbColor,
            disabledTrackColor = disabledTrackColor,
            disabledTickColor = disabledTickColor
        )
    }

}