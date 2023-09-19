package io.spherelabs.designsystem.dialog

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Dialog
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.min

internal actual fun getLayoutHeight(maxHeightPx: Int, layoutHeight: Int): Int {
    return min(maxHeightPx, layoutHeight)
}

@Composable
internal actual fun LKDialog(
    onDismissRequest: () -> Unit,
    properties: LKDialogProperties,
    content: @Composable () -> Unit,
) = Dialog(
    onDismissRequest = onDismissRequest,
    properties = DialogProperties(
        dismissOnBackPress = properties.dismissOnBackPress,
        dismissOnClickOutside = properties.dismissOnClickOutside,
        usePlatformDefaultWidth = properties.usePlatformDefaultWidth,
        decorFitsSystemWindows = properties.decorFitsSystemWindows
    ),
    content = content
)

@Composable
internal actual fun getDialogShape(shape: Shape): Shape = shape


internal actual fun Modifier.dialogHeight(): Modifier = wrapContentHeight()

internal actual fun Modifier.dialogMaxSize(maxHeight: Dp): Modifier = sizeIn(maxHeight = maxHeight, maxWidth = 560.dp)


actual typealias AtomicInt = AtomicInteger