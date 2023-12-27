package io.spherelabs.designsystem.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import io.spherelabs.designsystem.utils.ScreenConfiguration

@Composable
internal expect fun LKDialog(
    onDismissRequest: () -> Unit,
    properties: LKDialogProperties,
    content: @Composable () -> Unit,
)

@Immutable
data class LKDialogProperties(
    val dismissOnBackPress: Boolean = true,
    val dismissOnClickOutside: Boolean = true,
    val decorFitsSystemWindows: Boolean = true,
    val usePlatformDefaultWidth: Boolean = false,
    val size: DpSize = DpSize(400.dp, 300.dp),
    val title: String = "Untitled",
    val icon: Painter? = null,
    val resizable: Boolean = true
)

internal fun List<Pair<LKDialogButtonTypes, Placeable>>.buttons(type: LKDialogButtonTypes) =
    this.filter { it.first == type }.map { it.second }

internal expect fun Modifier.dialogMaxSize(maxHeight: Dp): Modifier

@Composable internal expect fun getDialogShape(shape: Shape): Shape

internal expect fun Modifier.dialogHeight(): Modifier

expect class AtomicInt() : Number {
  constructor(initialValue: Int)

  fun set(newValue: Int)

  fun getAndIncrement(): Int
}

@Composable internal expect fun ScreenConfiguration.getMaxHeight(): Dp

@Composable
internal actual fun ScreenConfiguration.getMaxHeight(): Dp {
  return 560.dp
}

internal expect fun getLayoutHeight(maxHeightPx: Int, layoutHeight: Int): Int
