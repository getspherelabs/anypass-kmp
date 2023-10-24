package io.spherelabs.anypass.ui.authenticator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.anypass.MR
import io.spherelabs.authenticatordomain.repository.AuthenticatorRepository
import io.spherelabs.designsystem.button.LKNewItemButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.image.RoundedImage
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.home.homepresentation.HomeWish
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.launch

@Composable
fun AuthenticatorRoute(
    navigateToNewToken: () -> Unit
) {
    AuthenticatorScreen {
        navigateToNewToken.invoke()
    }
}

@Composable
fun AuthenticatorScreen(
    modifier: Modifier = Modifier,
    navigateToNewToken: () -> Unit,
) {

    Scaffold(
        containerColor = BlackRussian,
        topBar = {
            AuthenticatorTopBar {
                navigateToNewToken.invoke()
            }
        },
    ) { newPaddingValues ->
        AuthenticatorContent(paddingValues = newPaddingValues)
    }
}

@Composable
fun AuthenticatorContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {

    }
}

@Composable
private fun AuthenticatorTopBar(
    modifier: Modifier = Modifier,
    navigateToNewToken: () -> Unit,
) {
    val scope = useScope()
    val strings = LocalStrings.current

    Row(
        modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 16.dp),
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
