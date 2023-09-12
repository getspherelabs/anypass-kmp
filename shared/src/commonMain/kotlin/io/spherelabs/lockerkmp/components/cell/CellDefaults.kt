package io.spherelabs.lockerkmp.components.cell

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

object CellDefaults {

    @Composable
    fun cellStyle(
        elevation: Dp,
        cursorColor: Color,
        cellsCount: Int,
        obscureText: String,
        placeHolder: String,
        borderColor: Color,
    ): CellStyle {
        return CellStyle(
            elevation = elevation,
            cursorColor = cursorColor,
            cellCount = cellsCount,
            obscureText = obscureText,
            placeHolder = placeHolder,
            borderColor = borderColor
        )
    }
}