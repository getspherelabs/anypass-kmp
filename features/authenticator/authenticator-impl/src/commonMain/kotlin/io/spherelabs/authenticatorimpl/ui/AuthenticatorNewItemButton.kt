package io.spherelabs.authenticatorimpl.ui

import androidx.compose.runtime.Composable
import io.spherelabs.designsystem.button.LKNewItemButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
internal fun AuthenticatorNewItemButton(
    onNewItemClick: () -> Unit
) {
    val strings = LocalStrings.current

    LKNewItemButton(
        contentText = strings.newToken,
        backgroundColor = LavenderBlue.copy(0.7f),
        contentFontFamily = GoogleSansFontFamily,
    ) {
        onNewItemClick.invoke()
    }
}
