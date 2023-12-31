package io.spherelabs.addnewpasswordimpl.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.spherelabs.designsystem.button.BackButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
internal fun AddNewPasswordTopBar(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    val strings = LocalStrings.current

    Row(
        modifier = modifier.padding(top = 16.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BackButton(
            modifier = modifier,
            backgroundColor = LavenderBlue.copy(0.7f),
            iconColor = Color.White,
            navigateToBack = { navigateToBack.invoke() },
        )
        Headline(
            text = strings.addNewPassword,
            modifier = modifier,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
            textColor = Color.White,
        )
    }
}
