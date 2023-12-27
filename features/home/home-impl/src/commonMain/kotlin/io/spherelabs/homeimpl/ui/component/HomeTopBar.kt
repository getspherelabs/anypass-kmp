package io.spherelabs.homeimpl.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.designsystem.button.LKNewItemButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.image.RoundedImage
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.images.MR

@Composable
fun HomeTopBar(
    modifier: Modifier,
    onOpenClick: () -> Unit,
    navigateToAddNewPassword: () -> Unit,
) {
  val strings = LocalStrings.current

  Row(
      modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 16.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    RoundedImage(
        modifier = modifier.clickable { onOpenClick.invoke() },
        painter = painterResource(MR.images.avatar),
        contentDescription = null,
    )

    LKNewItemButton(
        contentText = strings.newItem,
        backgroundColor = LavenderBlue.copy(0.7f),
        contentFontFamily = GoogleSansFontFamily,
    ) {
      navigateToAddNewPassword.invoke()
    }
  }
}
