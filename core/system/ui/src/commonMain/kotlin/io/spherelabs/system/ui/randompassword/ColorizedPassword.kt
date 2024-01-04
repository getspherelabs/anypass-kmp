package io.spherelabs.system.ui.randompassword

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
fun AnimatedColorizedRandomPassword(
    modifier: Modifier = Modifier,
    password: String,
    fontSize: TextUnit = 24.sp,
    colors: RandomPasswordColors = RandomPasswordDefaults.randomPasswordColor(),
) {
    val digitColor: Color by colors.digitColor()
    val symbolColor: Color by colors.symbolColor()

    val annotatedPassword = buildAnnotatedString {
        password.forEach { character ->

            when {
                character.isSurrogate() || character.isLetter() -> append(character)
                character.isDigit() -> {
                    withStyle(
                        style = SpanStyle(
                            color = digitColor,
                        ),
                    ) {
                        append(character)
                    }
                }
                else -> {
                    withStyle(
                        style = SpanStyle(
                            color = symbolColor,
                        ),
                    ) {
                        append(character)
                    }
                }
            }
        }
    }
    AnimatedContent(
        targetState = annotatedPassword,
        transitionSpec = {
            fadeIn(
                animationSpec = tween(
                    500,
                ),
            ) togetherWith fadeOut(
                animationSpec = tween(500, easing = FastOutLinearInEasing),
            )
        },
        modifier = modifier,
    ) { newPassword ->
        Text(
            text = newPassword,
            fontSize = fontSize,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = modifier,
        )
    }

}

@Composable
fun ColorizedRandomPassword(
    modifier: Modifier = Modifier,
    password: String,
    fontSize: TextUnit = 24.sp,
    colors: RandomPasswordColors = RandomPasswordDefaults.randomPasswordColor(),
) {
    val digitColor: Color by colors.digitColor()
    val symbolColor: Color by colors.symbolColor()

    val annotatedPassword = buildAnnotatedString {
        password.forEach { character ->
            when {
                character.isSurrogate() || character.isLetter() -> append(character)
                character.isDigit() -> {
                    withStyle(
                        style = SpanStyle(
                            color = digitColor,
                        ),
                    ) {
                        append(character)
                    }
                }
                else -> {
                    withStyle(
                        style = SpanStyle(
                            color = symbolColor,
                        ),
                    ) {
                        append(character)
                    }
                }
            }
        }
    }

    Text(
        text = annotatedPassword,
        fontSize = fontSize,
        fontFamily = GoogleSansFontFamily,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        modifier = modifier,
    )
}

object RandomPasswordDefaults {
    @Composable
    fun randomPasswordColor(
        digitColor: Color = Color.White,
        symbolColor: Color = Color.White,
    ): RandomPasswordColors {
        return RandomPasswordColors(digitColor, symbolColor)
    }
}

@Immutable
class RandomPasswordColors(
    private val digitColor: Color,
    private val symbolColor: Color,
) {
    @Composable
    fun digitColor(): State<Color> {
        val luminanceThreshold = 0.5f
        val (saturation, lightness) = if (digitColor.luminance() > luminanceThreshold) {
            0.52f to 0.56f
        } else {
            0.72f to 0.62f
        }

        val hue = 210f

        return rememberUpdatedState(
            Color.hsl(
                hue = hue,
                saturation = saturation,
                lightness = lightness,
            ),
        )
    }

    @Composable
    fun symbolColor(): State<Color> {
        val luminanceThreshold = 0.5f
        val (saturation, lightness) = if (symbolColor.luminance() > luminanceThreshold) {
            0.52f to 0.56f
        } else {
            0.72f to 0.62f
        }

        return rememberUpdatedState(
            Color.hsl(
                hue = 0f,
                saturation = saturation,
                lightness = lightness,
            ),
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is RandomPasswordColors) return false

        if (digitColor != other.digitColor) return false
        if (symbolColor != other.symbolColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = digitColor.hashCode()
        result = 31 * result + symbolColor.hashCode()

        return result
    }
}
