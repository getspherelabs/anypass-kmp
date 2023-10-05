package io.spherelabs.anypass.ui.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.accountpresentation.AccountEffect
import io.spherelabs.accountpresentation.AccountState
import io.spherelabs.accountpresentation.AccountViewModel
import io.spherelabs.accountpresentation.AccountWish
import io.spherelabs.admob.GADBannerView
import io.spherelabs.anypass.BuildKonfig
import io.spherelabs.designsystem.image.RoundedImage
import io.spherelabs.designsystem.switch.CupertinoSwitch
import io.spherelabs.anypass.MR
import io.spherelabs.anypass.di.useInject
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow

@Composable
fun AccountRoute(
    viewModel: AccountViewModel = useInject(),
    navigateToBack: () -> Unit,
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    AccountScreen(
        state = uiState.value,
        effect = viewModel.effect,
        navigateToBack = { navigateToBack.invoke() },
        wish = { newWish ->
            viewModel.wish(newWish)
        },
    )
}

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    state: AccountState,
    effect: Flow<AccountEffect>,
    wish: (AccountWish) -> Unit,
    navigateToBack: () -> Unit,
) {

    useEffect(true) {
        wish.invoke(AccountWish.GetStartedSizeOfStrongPassword)
        wish.invoke(AccountWish.GetStartedSizeOfWeakPassword)
        wish.invoke(AccountWish.GetStartedTotalPassword)
        wish.invoke(AccountWish.GetStartedFingerPrint)
    }

    Scaffold(
        containerColor = colorResource(MR.colors.dynamic_yellow),
        topBar = {
            Row(
                modifier = modifier.fillMaxWidth().padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BackButton(
                    modifier,
                    navigateToBack = {
                        navigateToBack.invoke()
                    },
                )
                Headline(
                    text = "Account",
                    modifier = modifier,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    textColor = Color.Black,
                )
            }
        },
    ) { padding ->
        Column(
            modifier = modifier.fillMaxSize().padding(padding),
        ) {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                RoundedImage(
                    imageSize = 150,
                    modifier = modifier.padding(top = 16.dp).border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = CircleShape,
                    ),
                    painter = painterResource(MR.images.avatar),
                    contentDescription = null,
                )
            }

            Spacer(modifier = modifier.height(16.dp))
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    "Behzod Halil",
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    color = Color.Black,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = "behzoddev@gmail.com",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.Black.copy(0.5f),
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Normal,
                )
            }

            Spacer(modifier.height(24.dp))

            Row(
                modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Row {
                    Text(
                        text = "${state.sizeOfTotalPassword}",
                        fontSize = 45.sp,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )

                    Text(
                        text = "passwords",
                        fontSize = 12.sp,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )
                }

                Column {
                    Text(
                        text = "${state.sizeOfStrongPassword}",
                        fontSize = 32.sp,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )

                    Text(
                        text = "Strong",
                        fontSize = 12.sp,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )
                }
                Column {
                    Text(
                        text = "${state.sizeOfWeakPassword}",
                        fontSize = 32.sp,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )

                    Text(
                        text = "Weak",
                        fontSize = 12.sp,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }

            Spacer(modifier = modifier.height(36.dp))

            Row(
                modifier = modifier.padding(horizontal = 24.dp).fillMaxWidth().height(48.dp)
                    .clip(
                        RoundedCornerShape(16.dp),
                    ).background(color = Color.Black),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = modifier.padding(start = 24.dp),
                    text = "Fingerprint",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                )
                CupertinoSwitch(
                    state.isFingerPrintEnabled,
                    onCheckedChange = { newChecked ->
                        wish.invoke(AccountWish.SetFingerPrint(newChecked))
                    },
                    modifier = modifier.padding(end = 24.dp),
                )
            }

            Spacer(modifier = modifier.height(12.dp))

            Row(
                modifier = modifier.padding(horizontal = 24.dp).fillMaxWidth().height(48.dp)
                    .clip(
                        RoundedCornerShape(16.dp),
                    ).background(color = Color.Black),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    modifier = modifier.padding(start = 24.dp),
                    text = "Change password",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                )
                Image(
                    modifier = modifier.padding(end = 24.dp).size(20.dp),
                    painter = painterResource(MR.images.change_password),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = Color.White),
                )

            }

            Spacer(modifier = modifier.height(12.dp))

            Row(
                modifier = modifier.padding(horizontal = 24.dp).fillMaxWidth().height(48.dp)
                    .clip(
                        RoundedCornerShape(16.dp),
                    ).background(color = Color.Black)
                    .clickable {
                        wish.invoke(AccountWish.OpenUrl("https://github.com/getspherelabs/anypass-kmp/issues/new/choose"))
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    modifier = modifier.padding(start = 24.dp),
                    text = "Send feedback",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                )
                Image(
                    modifier = modifier.padding(end = 24.dp).size(20.dp),
                    painter = painterResource(MR.images.message_square),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = Color.White),
                )

            }
            Spacer(modifier.weight(1f))
            GADBannerView(modifier = modifier.padding(bottom = 16.dp), adId = BuildKonfig.AD_ID)
        }
    }

}

@Composable
fun RowScope.BackButton(
    modifier: Modifier,
    backgroundColor: Color = Color.Black,
    iconColor: Color = Color.White,
    navigateToBack: () -> Unit,
) {
    Box(
        modifier = modifier.padding(start = 24.dp).size(42.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = backgroundColor)
            .clickable { navigateToBack.invoke() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = iconColor,
            contentDescription = "Back",
        )
    }
}
