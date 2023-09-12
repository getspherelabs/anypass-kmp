package io.spherelabs.lockerkmp.components.cell

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Immutable
class CellStyle(
    private val elevation: Dp = CellTokens.Elevation,
    private val borderColor: Color = CellTokens.BorderColor,
    private val cursorColor: Color = CellTokens.CursorColor,
    private val cellModifier: Modifier = CellTokens.CellModifier,
    private val placeHolder: String = CellTokens.PlaceHolder,
    private val obscureText: String = "",
    private val cellCount: Int = 4
) {
    @Composable
    internal fun borderColor(): State<Color> {
        return rememberUpdatedState(borderColor)
    }

    @Composable
    internal fun cursorColor(): State<Color> {
        return rememberUpdatedState(cursorColor)
    }

    @Composable
    internal fun placeHolder(): State<String> {
        return rememberUpdatedState(placeHolder)
    }

    @Composable
    internal fun obscureText(): State<String> {
        return rememberUpdatedState(obscureText)
    }
}

