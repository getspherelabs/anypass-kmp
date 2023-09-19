package io.spherelabs.designsystem.passwordcard

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

object PasswordCardDefaults {

    @Composable
    fun passwordCardColor(
        backgroundColor: Color = PasswordCardTokens.backgroundColor,
        copyBackgroundColor: Color = PasswordCardTokens.copyBackgroundColor,
        copyColor: Color = PasswordCardTokens.copyColor,
        emailColor: Color = PasswordCardTokens.emailColor,
        passwordColor: Color = PasswordCardTokens.passwordColor,
        titleColor: Color = PasswordCardTokens.titleColor
    ): PasswordCardColor {
        return PasswordCardColor(
            backgroundColor = backgroundColor,
            copyBackgroundColor = copyBackgroundColor,
            copyColor = copyColor,
            emailColor = emailColor,
            passwordColor = passwordColor,
            titleColor = titleColor
        )
    }

    @Composable
    fun passwordCardStyle(
        titleFontFamily: FontFamily = PasswordCardTokens.titleFontFamily,
        emailFontFamily: FontFamily = PasswordCardTokens.emailFontFamily,
        passwordFontFamily: FontFamily = PasswordCardTokens.passwordFontFamily,
        copyFontFamily: FontFamily = PasswordCardTokens.copyFontFamily,
        emailFontSize: TextUnit = PasswordCardTokens.emailFontSize,
        passwordFontSize: TextUnit = PasswordCardTokens.passwordFontSize,
        copyFontSize: TextUnit = PasswordCardTokens.copyFontSize,
        titleFontSize: TextUnit = PasswordCardTokens.titleFontSize,
        cardCornerShape: Dp = PasswordCardTokens.cardCornerShape,
    ): PasswordCardStyle {
        return PasswordCardStyle(
            titleFontFamily = titleFontFamily,
            emailFontFamily = emailFontFamily,
            passwordFontFamily = passwordFontFamily,
            copyFontFamily = copyFontFamily,
            emailFontSize = emailFontSize,
            passwordFontSize = passwordFontSize,
            copyFontSize = copyFontSize,
            titleFontSize = titleFontSize,
            cardCornerShape = cardCornerShape
        )
    }
}