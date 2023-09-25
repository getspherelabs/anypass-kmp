package io.spherelabs.designsystem.passwordcard

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

object LKPasswordCardDefaults {

  @Composable
  fun passwordCardColor(
    backgroundColor: Color = LKPasswordCardTokens.backgroundColor,
    copyBackgroundColor: Color = LKPasswordCardTokens.copyBackgroundColor,
    copyColor: Color = LKPasswordCardTokens.copyColor,
    emailColor: Color = LKPasswordCardTokens.emailColor,
    passwordColor: Color = LKPasswordCardTokens.passwordColor,
    titleColor: Color = LKPasswordCardTokens.titleColor
  ): LKPasswordCardColor {
    return LKPasswordCardColor(
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
    titleFontFamily: FontFamily = LKPasswordCardTokens.titleFontFamily,
    emailFontFamily: FontFamily = LKPasswordCardTokens.emailFontFamily,
    passwordFontFamily: FontFamily = LKPasswordCardTokens.passwordFontFamily,
    copyFontFamily: FontFamily = LKPasswordCardTokens.copyFontFamily,
    emailFontSize: TextUnit = LKPasswordCardTokens.emailFontSize,
    passwordFontSize: TextUnit = LKPasswordCardTokens.passwordFontSize,
    copyFontSize: TextUnit = LKPasswordCardTokens.copyFontSize,
    titleFontSize: TextUnit = LKPasswordCardTokens.titleFontSize,
    cardCornerShape: Dp = LKPasswordCardTokens.cardCornerShape,
  ): LKPasswordCardStyle {
    return LKPasswordCardStyle(
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
