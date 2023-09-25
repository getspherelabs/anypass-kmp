package io.spherelabs.designsystem.picker

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import io.spherelabs.designsystem.dialog.BasicLKDialog
import io.spherelabs.designsystem.dialog.LKDialogProperties
import io.spherelabs.designsystem.dialog.LKDialogScope
import io.spherelabs.designsystem.dialog.useDialogState

@Composable
fun LKSocialMediaPicker(
  modifier: Modifier = Modifier,
  content: @Composable LKDialogScope.() -> Unit
) {

  val dialogState = useDialogState()

  BasicLKDialog(
    dialogState = dialogState,
    buttons = {
      negativeButton("Cancel")
      positiveButton("Ok")
    },
    properties = LKDialogProperties()
  ) {
    content()
  }

  Box(
    modifier =
      modifier
        .size(56.dp)
        .clip(RoundedCornerShape(8.dp))
        .border(
          width = 2.dp,
          color = Color.Black.copy(alpha = 0.2f),
          shape = RoundedCornerShape(8.dp)
        )
        .clickable { dialogState.show() },
    contentAlignment = Alignment.Center
  ) {
    Icon(imageVector = Icons.Default.ImageSearch, contentDescription = null)
  }
}

@ExperimentalMaterialApi
@Composable
fun LKDialogScope.socialIconsPicker(
  socialIcons: List<Painter>,
  initialSelection: Int = 0,
  waitForPositiveButton: Boolean = true,
  onSocialIconSelected: (Painter) -> Unit = {}
) {
  BoxWithConstraints {
    val selectedSocialIcon = remember { mutableStateOf(socialIcons[initialSelection]) }
    val swipeState = rememberSwipeableState("")

    if (waitForPositiveButton) {
      DialogCallback { onSocialIconSelected(selectedSocialIcon.value) }
    } else {
      DisposableEffect(selectedSocialIcon.value) {
        onSocialIconSelected(selectedSocialIcon.value)
        onDispose {}
      }
    }

    Column(Modifier.padding(bottom = 8.dp)) {
      Layout(
        content = {
          LKSocialIconGridLayout(
            Modifier.width(this@BoxWithConstraints.maxWidth),
            socialIcons = socialIcons,
            selectedSocialIcon = selectedSocialIcon,
            initialSelection = initialSelection
          )
        },
      ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val height = placeables.maxByOrNull { it.height }?.height ?: 0

        layout(constraints.maxWidth, height) {
          placeables.forEachIndexed { index, placeable ->
            placeable.place(
              x = -swipeState.offset.value.toInt() + index * constraints.maxWidth,
              y = 0
            )
          }
        }
      }
    }
  }
}
