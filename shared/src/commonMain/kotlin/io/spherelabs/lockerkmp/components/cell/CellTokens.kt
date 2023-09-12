package io.spherelabs.lockerkmp.components.cell

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object CellTokens {
    val CellModifier: Modifier = Modifier.padding(horizontal = 2.dp).size(48.dp)
    val CellShape: Shape = RoundedCornerShape(16.dp)
    val BackgroundColor: Color = Color.White
    val BorderColor: Color = Color.Black
    val BorderWidth: Dp = 1.dp
    val TextStyle: TextStyle = androidx.compose.ui.text.TextStyle.Default
    val PlaceHolderTextStyle: TextStyle = androidx.compose.ui.text.TextStyle.Default
    val Elevation: Dp = 0.dp
    val CursorColor: Color = Color.Transparent
    const val PlaceHolder: String = ""
}