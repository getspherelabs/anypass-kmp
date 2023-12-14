package io.spherelabs.authenticatorimpl.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.spherelabs.designsystem.button.LKNewItemButton
import io.spherelabs.designsystem.dimension.LocalDimensions
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily


@Composable
internal fun AuthenticatorTopBar(
    modifier: Modifier = Modifier,
    navigateToNewToken: () -> Unit,
) {
    val strings = LocalStrings.current
    val dimensions = LocalDimensions.current

    Row(
        modifier = modifier.fillMaxWidth()
            .padding(start = dimensions.large, end = dimensions.large, top = dimensions.medium),
        horizontalArrangement = Arrangement.End,
    ) {

        LKNewItemButton(
            contentText = strings.newToken,
            backgroundColor = LavenderBlue.copy(0.7f),
            contentFontFamily = GoogleSansFontFamily,
        ) {
            navigateToNewToken.invoke()
        }

    }

}
