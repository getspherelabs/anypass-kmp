package io.spherelabs.designsystem.passwordcard

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object PasswordCardTokens {
    val backgroundColor: Color = Color.Blue
    val copyBackgroundColor: Color = Color.White
    val titleColor: Color = Color.White
    val emailColor: Color = Color.White
    val passwordColor: Color = Color.White
    val copyColor: Color = Color.Black

    val titleFontFamily: FontFamily = FontFamily.Serif
    val emailFontFamily: FontFamily = FontFamily.Serif
    val passwordFontFamily: FontFamily = FontFamily.Serif
    val copyFontFamily: FontFamily = FontFamily.Serif

    val titleFontSize: TextUnit = 32.sp
    val emailFontSize: TextUnit = 16.sp
    val passwordFontSize: TextUnit = 65.sp
    val copyFontSize: TextUnit = 14.sp

    val cardCornerShape: Dp = 16.dp
}