package io.spherelabs.designsystem.spinner

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

object LockerDropdownDefaults {

    @Composable
    fun dropdownStyle(
        elevation: Dp = LockerDropdownTokens.MenuElevation,
        verticalMargin: Dp = LockerDropdownTokens.MenuVerticalMargin,
        verticalPadding: Dp = LockerDropdownTokens.MenuVerticalPadding,
        menuItemHorizontalPadding: Dp = LockerDropdownTokens.MenuItemHorizontalPadding,
        menuItemDefaultMinWidth: Dp = LockerDropdownTokens.MenuItemDefaultMinWidth,
        menuItemDefaultMaxWidth: Dp = LockerDropdownTokens.MenuItemDefaultMaxWidth,
        menuItemDefaultMinHeight: Dp = LockerDropdownTokens.MenuItemDefaultMinHeight
    ): LockerDropdownStyle {
        return LockerDropdownStyle(
            elevation = elevation,
            verticalMargin = verticalMargin,
            verticalPadding = verticalPadding,
            menuItemHorizontalPadding = menuItemHorizontalPadding,
            menuItemDefaultMinWidth = menuItemDefaultMinWidth,
            menuItemDefaultMaxWidth = menuItemDefaultMaxWidth,
            menuItemDefaultMinHeight = menuItemDefaultMinHeight
        )
    }
}