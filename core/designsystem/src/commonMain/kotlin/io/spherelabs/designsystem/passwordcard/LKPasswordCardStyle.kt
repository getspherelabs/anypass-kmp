package io.spherelabs.designsystem.passwordcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import io.spherelabs.designsystem.hooks.useUpdatedState

@Immutable
class LKPasswordCardStyle(
  private val titleFontFamily: FontFamily,
  private val emailFontFamily: FontFamily,
  private val passwordFontFamily: FontFamily,
  private val copyFontFamily: FontFamily,
  private val titleFontSize: TextUnit,
  private val emailFontSize: TextUnit,
  private val passwordFontSize: TextUnit,
  private val copyFontSize: TextUnit,
  private val cardCornerShape: Dp
) {
  @Composable
  fun titleFontFamily(): State<FontFamily> {
    return useUpdatedState(titleFontFamily)
  }

  @Composable
  fun emailFontFamily(): State<FontFamily> {
    return useUpdatedState(emailFontFamily)
  }

  @Composable
  fun passwordFontFamily(): State<FontFamily> {
    return useUpdatedState(passwordFontFamily)
  }

  @Composable
  fun copyFontFamily(): State<FontFamily> {
    return useUpdatedState(copyFontFamily)
  }

  @Composable
  fun titleFontSize(): State<TextUnit> {
    return useUpdatedState(titleFontSize)
  }

  @Composable
  fun emailFontSize(): State<TextUnit> {
    return useUpdatedState(emailFontSize)
  }

  @Composable
  fun passwordFontSize(): State<TextUnit> {
    return useUpdatedState(passwordFontSize)
  }

  @Composable
  fun copyFontSize(): State<TextUnit> {
    return useUpdatedState(copyFontSize)
  }

  @Composable
  fun cardCornerShape(): State<Dp> {
    return useUpdatedState(cardCornerShape)
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || other !is LKPasswordCardStyle) return false

    if (titleFontFamily != other.titleFontFamily) return false
    if (emailFontFamily != other.emailFontFamily) return false
    if (passwordFontFamily != other.passwordFontFamily) return false
    if (copyFontFamily != other.copyFontFamily) return false
    if (titleFontSize != other.titleFontSize) return false
    if (emailFontSize != other.emailFontSize) return false
    if (passwordFontSize != other.passwordFontSize) return false
    if (copyFontSize != other.copyFontSize) return false
    if (cardCornerShape != other.cardCornerShape) return false

    return true
  }

  override fun hashCode(): Int {
    var result = titleFontFamily.hashCode()
    result = 31 * result + emailFontFamily.hashCode()
    result = 31 * result + passwordFontFamily.hashCode()
    result = 31 * result + copyFontFamily.hashCode()
    result = 31 * result + titleFontSize.hashCode()
    result = 31 * result + emailFontSize.hashCode()
    result = 31 * result + passwordFontSize.hashCode()
    result = 31 * result + copyFontSize.hashCode()
    result = 31 * result + cardCornerShape.hashCode()

    return result
  }
}
