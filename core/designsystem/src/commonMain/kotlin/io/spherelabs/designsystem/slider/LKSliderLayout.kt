package io.spherelabs.designsystem.slider

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize

@Composable
internal fun SliderLayout(
    modifier: Modifier = Modifier,
    slider: @Composable (Constraints) -> Unit,
) {
    SubcomposeLayout(modifier = modifier) { constraints: Constraints ->
        val sliderPlaceable: Placeable = subcompose("slider") {
            slider(constraints)
        }.map { it.measure(constraints) }.first()

        val sliderWidth = sliderPlaceable.width
        val sliderHeight = sliderPlaceable.height

        layout(sliderWidth, sliderHeight) { sliderPlaceable.placeRelative(0, 0) }
    }
}

@Composable
internal fun LKSliderLayout(
    modifier: Modifier = Modifier,
    thumb: @Composable () -> Unit,
    slider: @Composable (IntSize, Constraints) -> Unit,
) {
    SubcomposeLayout(modifier = modifier) { constraints: Constraints ->
        val thumbPlaceable: Placeable =
            subcompose("Thumb", thumb).map { it.measure(constraints) }.first()

        val thumbSize = IntSize(thumbPlaceable.width, thumbPlaceable.height)

        val sliderPlaceable: Placeable =
            subcompose("Slider") { slider(thumbSize, constraints) }
                .map { it.measure(constraints) }
                .first()

        val sliderWidth = sliderPlaceable.width
        val sliderHeight = sliderPlaceable.height

        layout(sliderWidth, sliderHeight) { sliderPlaceable.placeRelative(0, 0) }
    }
}
