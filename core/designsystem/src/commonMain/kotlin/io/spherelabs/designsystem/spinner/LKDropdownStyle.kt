package io.spherelabs.designsystem.spinner

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.Dp
import io.spherelabs.designsystem.hooks.useUpdatedState


@Immutable
class LKDropdownStyle(
    private val elevation: Dp,
    private val verticalMargin: Dp,
    private val verticalPadding: Dp,
    private val menuItemHorizontalPadding: Dp,
    private val menuItemDefaultMinWidth: Dp,
    private val menuItemDefaultMaxWidth: Dp,
    private val menuItemDefaultMinHeight: Dp,
    private val itemContentPadding: PaddingValues
) {
    @Composable
    internal fun elevation(): State<Dp> {
        return useUpdatedState(elevation)
    }

    @Composable
    internal fun verticalMargin(): State<Dp> {
        return useUpdatedState(verticalMargin)
    }

    @Composable
    internal fun verticalPadding(): State<Dp> {
        return useUpdatedState(verticalPadding)
    }

    @Composable
    internal fun menuItemHorizontalPadding(): State<Dp> {
        return useUpdatedState(menuItemHorizontalPadding)
    }

    @Composable
    internal fun menuItemDefaultMinWidth(): State<Dp> {
        return useUpdatedState(menuItemDefaultMinWidth)
    }

    @Composable
    internal fun menuItemDefaultMaxWidth(): State<Dp> {
        return useUpdatedState(menuItemDefaultMaxWidth)
    }

    @Composable
    internal fun menuItemDefaultMinHeight(): State<Dp> {
        return useUpdatedState(menuItemDefaultMinHeight)
    }

    @Composable
    internal fun itemContentPadding(): State<PaddingValues> {
        return useUpdatedState(itemContentPadding)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is LKDropdownStyle) return false

        if (elevation != other.elevation) return false
        if (verticalMargin != other.verticalMargin) return false
        if (verticalPadding != other.verticalPadding) return false
        if (menuItemDefaultMinWidth != other.menuItemDefaultMinWidth) return false
        if (menuItemDefaultMaxWidth != other.menuItemDefaultMaxWidth) return false
        if (menuItemHorizontalPadding != other.menuItemHorizontalPadding) return false
        if (menuItemDefaultMinHeight != other.menuItemDefaultMinHeight) return false
        if (itemContentPadding != other.itemContentPadding) return false

        return true
    }

    override fun hashCode(): Int {
        var result = elevation.hashCode()
        result = 31 * result + verticalMargin.hashCode()
        result = 31 * result + verticalPadding.hashCode()
        result = 31 * result + menuItemDefaultMinWidth.hashCode()
        result = 31 * result + menuItemDefaultMaxWidth.hashCode()
        result = 31 * result + menuItemHorizontalPadding.hashCode()
        result = 31 * result + menuItemDefaultMinWidth.hashCode()
        result = 31 * result + itemContentPadding.hashCode()

        return result
    }
}