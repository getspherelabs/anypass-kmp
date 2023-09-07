package io.spherelabs.lockerkmp.components.slider

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import dev.icerock.moko.resources.compose.colorResource
import io.spherelabs.lockerkmp.MR


@Immutable
class SliderColors(
    private val thumbColor: Color,
    private val disabledThumbColor: Color,
    private val trackColor: Color,
    private val disabledTrackColor: Color,
    private val tickColor: Color,
    private val disabledTickColor: Color
) {
    @Composable
    internal fun thumbColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) thumbColor else disabledThumbColor)
    }

    @Composable
    internal fun trackColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) trackColor else disabledTrackColor)
    }

    @Composable
    internal fun tickColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) tickColor else disabledTickColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is SliderColors) return false

        if (thumbColor != other.thumbColor) return false
        if (trackColor != other.trackColor) return false
        if (tickColor != other.tickColor) return false
        if (disabledThumbColor != other.disabledThumbColor) return false
        if (disabledTrackColor != other.disabledTrackColor) return false
        if (disabledTickColor != other.disabledTickColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = thumbColor.hashCode()
        result = 31 * result + trackColor.hashCode()
        result = 31 * result + tickColor.hashCode()
        result = 31 * result + disabledThumbColor.hashCode()
        result = 31 * result + disabledTrackColor.hashCode()
        result = 31 * result + disabledTickColor.hashCode()

        return result
    }
}