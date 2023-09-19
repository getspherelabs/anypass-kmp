package io.spherelabs.designsystem.passwordcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import io.spherelabs.designsystem.hooks.useUpdatedState

@Immutable
class PasswordCardColor(
    private val backgroundColor: Color,
    private val copyBackgroundColor: Color,
    private val titleColor: Color,
    private val emailColor: Color,
    private val passwordColor: Color,
    private val copyColor: Color,
) {
    @Composable
    fun backgroundColor(): State<Color> {
        return useUpdatedState(backgroundColor)
    }

    @Composable
    fun copyBackgroundColor(): State<Color> {
        return useUpdatedState(copyBackgroundColor)
    }

    @Composable
    fun titleColor(): State<Color> {
        return useUpdatedState(titleColor)
    }

    @Composable
    fun emailColor(): State<Color> {
        return useUpdatedState(emailColor)
    }

    @Composable
    fun passwordColor(): State<Color> {
        return useUpdatedState(passwordColor)
    }

    @Composable
    fun copyColor(): State<Color> {
        return useUpdatedState(copyColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is PasswordCardColor) return false

        if (backgroundColor != other.backgroundColor) return false
        if (copyBackgroundColor != other.copyBackgroundColor) return false
        if (titleColor != other.titleColor) return false
        if (emailColor != other.emailColor) return false
        if (passwordColor != other.passwordColor) return false
        if (copyColor != other.copyColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + copyBackgroundColor.hashCode()
        result = 31 * result + titleColor.hashCode()
        result = 31 * result + emailColor.hashCode()
        result = 31 * result + passwordColor.hashCode()
        result = 31 * result + copyColor.hashCode()

        return result
    }
}