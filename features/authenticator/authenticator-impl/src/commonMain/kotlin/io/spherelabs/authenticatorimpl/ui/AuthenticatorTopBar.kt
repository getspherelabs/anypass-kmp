package io.spherelabs.authenticatorimpl.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.spherelabs.designsystem.button.LKNewItemButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
internal fun AuthenticatorTopBar(
    modifier: Modifier = Modifier,
    navigateToNewToken: () -> Unit,
) {
    val strings = LocalStrings.current

    Row(
        modifier =
        modifier
            .fillMaxWidth().padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Headline(
            text = strings.authenticator,
            modifier = modifier,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
            textColor = Color.White,
        )
        LKNewItemButton(
            contentText = strings.newToken,
            backgroundColor = LavenderBlue.copy(0.7f),
            contentFontFamily = GoogleSansFontFamily,
        ) {
            navigateToNewToken.invoke()
        }
    }
}
