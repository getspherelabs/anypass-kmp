package io.spherelabs.designsystem.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.spherelabs.designsystem.utils.rememberScreenConfiguration
import io.spherelabs.foundation.color.Jaguar

@Composable
fun BasicLKDialog(
  dialogState: LKDialogState = useDialogState(),
  properties: LKDialogProperties = LKDialogProperties(),
  backgroundColor: Color = Jaguar,
  shape: Shape = MaterialTheme.shapes.medium,
  border: BorderStroke? = null,
  elevation: Dp = 24.dp,
  autoDismiss: Boolean = true,
  onCloseRequest: (LKDialogState) -> Unit = { it.hide() },
  buttons: @Composable LKDialogButtons.() -> Unit = {},
  content: @Composable LKDialogScope.() -> Unit
) {
  val dialogScope = remember { LKDialogScopeImpl(dialogState, autoDismiss) }

  DisposableEffect(dialogState.showing) {
    if (!dialogState.showing) dialogScope.reset()
    onDispose {}
  }

  if (dialogState.showing) {
    dialogState.dialogBackgroundColor =
      LocalElevationOverlay.current?.apply(color = backgroundColor, elevation = elevation)
        ?: MaterialTheme.colors.surface

    BoxWithConstraints {
      LKDialog(properties = properties, onDismissRequest = { onCloseRequest(dialogState) }) {
        val configuration = rememberScreenConfiguration()

        val maxHeight = configuration.getMaxHeight()

        val maxHeightPx = with(LocalDensity.current) { maxHeight.toPx().toInt() }
        Surface(
          modifier =
            Modifier.fillMaxWidth()
              .padding(horizontal = 24.dp)
              .clipToBounds()
              .dialogHeight()
              .testTag("dialog"),
          shape = getDialogShape(shape),
          color = backgroundColor,
          border = border,
          elevation = elevation
        ) {
          Layout(
            content = {
              dialogScope.DialogButtonsLayout(
                modifier = Modifier.layoutId("buttons"),
                content = buttons
              )
              Column(Modifier.layoutId("content")) { content(dialogScope) }
            }
          ) { measurables, constraints ->
            val buttonsHeight = measurables[0].minIntrinsicHeight(constraints.maxWidth)
            val buttonsPlaceable =
              measurables[0].measure(constraints.copy(maxHeight = buttonsHeight, minHeight = 0))

            val contentPlaceable =
              measurables[1].measure(
                constraints.copy(
                  maxHeight = maxHeightPx - buttonsPlaceable.height,
                  minHeight = 0,
                )
              )

            val height =
              getLayoutHeight(maxHeightPx, buttonsPlaceable.height + contentPlaceable.height)

            return@Layout layout(constraints.maxWidth, height) {
              contentPlaceable.place(0, 0)
              buttonsPlaceable.place(0, height - buttonsPlaceable.height)
            }
          }
        }
      }
    }
  }
}
