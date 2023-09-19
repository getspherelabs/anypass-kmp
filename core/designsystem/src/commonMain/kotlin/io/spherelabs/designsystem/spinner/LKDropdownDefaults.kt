package io.spherelabs.designsystem.spinner

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.Dp

object LKDropdownDefaults {

    @Composable
    fun dropdownStyle(
        elevation: Dp = LKDropdownTokens.MenuElevation,
        verticalMargin: Dp = LKDropdownTokens.MenuVerticalMargin,
        verticalPadding: Dp = LKDropdownTokens.MenuVerticalPadding,
        menuItemHorizontalPadding: Dp = LKDropdownTokens.MenuItemHorizontalPadding,
        menuItemDefaultMinWidth: Dp = LKDropdownTokens.MenuItemDefaultMinWidth,
        menuItemDefaultMaxWidth: Dp = LKDropdownTokens.MenuItemDefaultMaxWidth,
        menuItemDefaultMinHeight: Dp = LKDropdownTokens.MenuItemDefaultMinHeight,
        itemContentPadding: PaddingValues = LKDropdownTokens.DropdownMenuItemContentPadding
    ): LKDropdownStyle {
        return LKDropdownStyle(
            elevation = elevation,
            verticalMargin = verticalMargin,
            verticalPadding = verticalPadding,
            menuItemHorizontalPadding = menuItemHorizontalPadding,
            menuItemDefaultMinWidth = menuItemDefaultMinWidth,
            menuItemDefaultMaxWidth = menuItemDefaultMaxWidth,
            menuItemDefaultMinHeight = menuItemDefaultMinHeight,
            itemContentPadding = itemContentPadding
        )
    }

    @Composable
    fun TrailingIcon(expanded: Boolean) {
        Icon(
            Icons.Filled.ArrowDropDown,
            null,
            Modifier.rotate(if (expanded) 180f else 0f)
        )
    }
}