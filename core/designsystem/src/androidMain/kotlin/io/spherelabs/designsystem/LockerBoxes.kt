package io.spherelabs.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun Boxes(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val text1 = measurables[0]
        val box1 = measurables[1]
        val box2 = measurables[2]
        val text2 = measurables[3]
        val text3 = measurables[4]
        val box3 = measurables[5]
        val box4 = measurables[6]
        val text4 = measurables[7]

        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )
        val text1Placeable = text1.measure(looseConstraints)
        val box1Placeable = box1.measure(looseConstraints)
        val box2Placeable = box2.measure(looseConstraints)
        val text2Placeable = text2.measure(looseConstraints)
        val text3Placeable = text3.measure(looseConstraints)
        val box3Placeable = box3.measure(looseConstraints)
        val box4Placeable = box4.measure(looseConstraints)
        val text4Placeable = text4.measure(looseConstraints)


        layout(
            width = constraints.maxWidth, height = constraints.maxHeight
        ) {
            text1Placeable.placeRelative(0, box1Placeable.height / 4)
            box1Placeable.placeRelative(text1Placeable.width + 16, 0)
            box2Placeable.placeRelative(
                text2Placeable.width + 16, box1Placeable.height - box2Placeable.height / 4
            )
            text2Placeable.placeRelative(
                text1Placeable.width + box1Placeable.width + 32, box2Placeable.height
            )
            text3Placeable.placeRelative(
                0, ((box1Placeable.height / 3) + (box2Placeable.height / 2) + box3Placeable.height)
            )
            box3Placeable.placeRelative(
                text3Placeable.width + 16, box1Placeable.height + box2Placeable.height / 2
            )
            box4Placeable.placeRelative(
                text1Placeable.width + 16,
                box1Placeable.height + box2Placeable.height + box3Placeable.height / 4
            )
            text4Placeable.placeRelative(
                text1Placeable.width + box1Placeable.width + 32,
                box3Placeable.height + box2Placeable.height + (box3Placeable.height / 2)
            )
        }
    }
}