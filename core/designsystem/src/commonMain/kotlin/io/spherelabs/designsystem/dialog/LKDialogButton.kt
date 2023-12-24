package io.spherelabs.designsystem.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

internal enum class LKDialogButtonTypes(val testTag: String) {
  Positive("positive"),
  Negative("negative"),
}

/**
 * Adds buttons to the bottom of the dialog
 *
 * @param content the buttons which should be displayed in the dialog. See [LKDialogButtons] for
 *   more information about the content
 */
@Composable
internal fun LKDialogScope.DialogButtonsLayout(
    modifier: Modifier = Modifier,
    content: @Composable LKDialogButtons.() -> Unit
) {
  val interButtonPadding = with(LocalDensity.current) { 12.dp.toPx().toInt() }
  val defaultBoxHeight = with(LocalDensity.current) { 52.dp.toPx().toInt() }
  val defaultButtonHeight = with(LocalDensity.current) { 36.dp.toPx().toInt() }
  val verticalPadding = with(LocalDensity.current) { 8.dp.toPx().toInt() }

  Layout(
      { content(dialogButtons) },
      modifier
          .fillMaxWidth()
          .padding(horizontal = 8.dp)
          .background(dialogState.dialogBackgroundColor!!),
      { measurables, constraints ->
        if (measurables.isEmpty()) {
          return@Layout layout(0, 0) {}
        }

        val placeables =
            measurables.map {
              (it.layoutId as LKDialogButtonTypes) to
                  it.measure(constraints.copy(minWidth = 0, maxHeight = defaultButtonHeight))
            }
        val totalWidth = placeables.sumOf { it.second.width }
        val column = totalWidth > 0.8 * constraints.maxWidth

        val height =
            if (column) {
              val buttonHeight = placeables.sumOf { it.second.height }
              val heightPadding = (placeables.size - 1) * interButtonPadding
              buttonHeight + heightPadding + 2 * verticalPadding
            } else {
              defaultBoxHeight
            }

        layout(constraints.maxWidth, height) {
          var currX = constraints.maxWidth
          var currY = verticalPadding

          val posButtons = placeables.buttons(LKDialogButtonTypes.Positive)
          val negButtons = placeables.buttons(LKDialogButtonTypes.Negative)

          val buttonInOrder = posButtons + negButtons

          buttonInOrder.forEach { button ->
            if (column) {
              button.place(currX - button.width, currY)
              currY += button.height + interButtonPadding
            } else {
              currX -= button.width
              button.place(currX, currY)
            }
          }
        }
      })
}

class LKDialogButtons(private val scope: LKDialogScope) {

  @Composable
  fun positiveButton(
      text: String,
      textStyle: TextStyle = MaterialTheme.typography.button,
      colors: ButtonColors = ButtonDefaults.textButtonColors(contentColor = Color.White),
      disableDismiss: Boolean = false,
      onClick: () -> Unit = {}
  ) {
    val buttonText = text.uppercase()
    val buttonEnabled = scope.positiveButtonEnabled.values.all { it }
    val focusManager = LocalFocusManager.current

    TextButton(
        onClick = {
          if (scope.autoDismiss && !disableDismiss) {
            scope.dialogState.hide(focusManager)
          }

          scope.callbacks.values.forEach { it() }

          onClick()
        },
        modifier =
            Modifier.layoutId(LKDialogButtonTypes.Positive)
                .testTag(LKDialogButtonTypes.Positive.testTag),
        enabled = buttonEnabled,
        colors = colors) {
          Text(text = buttonText, style = textStyle)
        }
  }

  @Composable
  fun negativeButton(
      text: String,
      textStyle: TextStyle = MaterialTheme.typography.button,
      colors: ButtonColors = ButtonDefaults.textButtonColors(contentColor = Color.White),
      onClick: () -> Unit = {}
  ) {
    val buttonText = text.uppercase()
    val focusManager = LocalFocusManager.current

    TextButton(
        onClick = {
          if (scope.autoDismiss) {
            scope.dialogState.hide(focusManager)
          }
          onClick()
        },
        modifier =
            Modifier.layoutId(LKDialogButtonTypes.Negative)
                .testTag(LKDialogButtonTypes.Negative.testTag),
        colors = colors) {
          Text(text = buttonText, style = textStyle)
        }
  }
}
