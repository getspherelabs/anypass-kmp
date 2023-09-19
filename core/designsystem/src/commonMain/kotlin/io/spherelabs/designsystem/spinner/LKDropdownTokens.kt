package io.spherelabs.designsystem.spinner

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

object LKDropdownTokens {
    val MenuElevation = 8.dp
    val MenuVerticalMargin = 48.dp
    val MenuItemHorizontalPadding = 16.dp
    private val MenuItemVerticalPadding = 0.dp
    val MenuVerticalPadding = 8.dp
    val MenuItemDefaultMinWidth = 112.dp
    val MenuItemDefaultMaxWidth = 280.dp
    val MenuItemDefaultMinHeight = 48.dp
    val DropdownMenuItemContentPadding = PaddingValues(
        horizontal = MenuItemHorizontalPadding,
        vertical = MenuItemVerticalPadding
    )
    const val InTransitionDuration = 120
    const val OutTransitionDuration = 75
    const val InTransitioningDuration = 30
}