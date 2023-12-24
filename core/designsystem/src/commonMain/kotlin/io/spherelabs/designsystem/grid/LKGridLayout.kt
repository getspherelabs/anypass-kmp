package io.spherelabs.designsystem.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import io.spherelabs.designsystem.button.LKNumberButton

@Composable
fun LKGridLayout(
    items: List<String>,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
  LazyRow(
      horizontalArrangement = Arrangement.SpaceEvenly,
      modifier = Modifier.fillMaxWidth().padding(top = 18.dp),
      content = {
        itemsIndexed(items) { _, item ->
          LKNumberButton(
              value = item,
              fontFamily = fontFamily,
          ) {
            onValueChanged.invoke(item)
          }
        }
      },
  )
}

@Composable
fun CreateBoxes(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
  Layout(
      modifier = modifier,
      content = content,
  ) { measurables, constraints ->
    val box1 = measurables[0]
    val box2 = measurables[1]
    val box3 = measurables[2]

    val looseConstraints =
        constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )

    val box1Placeable = box1.measure(looseConstraints)
    val box2Placeable = box2.measure(looseConstraints)
    val box3Placeable = box3.measure(looseConstraints)

    layout(width = constraints.maxWidth, height = constraints.maxHeight) {
      box1Placeable.placeRelative(constraints.maxWidth / 4, box2Placeable.height / 4)
      box2Placeable.placeRelative(constraints.maxWidth / 4 + box1Placeable.width - 12, 0)
      box3Placeable.placeRelative(
          (constraints.maxWidth / 4) + (box2Placeable.width + box1Placeable.width) - 22,
          box2Placeable.height / 4,
      )
    }
  }
}
