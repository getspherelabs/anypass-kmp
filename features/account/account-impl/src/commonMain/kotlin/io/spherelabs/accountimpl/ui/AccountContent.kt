package io.spherelabs.accountimpl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarHost
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
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.accountimp.BuildKonfig
import io.spherelabs.accountimpl.presentation.AccountEffect
import io.spherelabs.accountimpl.presentation.AccountState
import io.spherelabs.accountimpl.presentation.AccountWish
import io.spherelabs.admob.GADBannerView
import io.spherelabs.designsystem.dimension.LocalDimensions
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.image.RoundedImage
import io.spherelabs.designsystem.switch.CupertinoSwitch
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.images.MR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun AccountContent(
    modifier: Modifier = Modifier,
    state: AccountState,
    effect: Flow<AccountEffect>,
    wish: (AccountWish) -> Unit,
    navigateToBack: () -> Unit,
    navigateToChangePassword: () -> Unit,
) {

    val snackbarState = useSnackbar()
    val scope = useScope()

    val strings = LocalStrings.current
    val dimensions = LocalDimensions.current

    useEffect(true) {
        wish.invoke(AccountWish.GetAccount)
        wish.invoke(AccountWish.GetStartedFingerPrint)

        effect.collectLatest { newEffect ->
            when (newEffect) {
                AccountEffect.ChangePasswordRoute -> {
                    navigateToChangePassword.invoke()
                }

                is AccountEffect.Failure -> {
                    scope.launch {
                        snackbarState
                            .showSnackbar(newEffect.message)
                    }
                }

                AccountEffect.Back -> {
                    navigateToBack.invoke()
                }
            }
        }

    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom),
            )
        },
        containerColor = Color(0xff141419),
        topBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dimensions.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BackButton(
                    backgroundColor = Color(0xff9C98F6).copy(0.7f),
                    modifier = modifier,
                    navigateToBack = {
                        wish.invoke(AccountWish.NavigateToBack)
                    },
                )
                Headline(
                    text = strings.account,
                    modifier = modifier,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    textColor = Color.White,
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
                    modifier = modifier.padding(top = dimensions.medium).border(
                        width = dimensions.divider,
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
                val name = state.user?.name ?: "Unknown"
                val email = state.user?.email ?: "Unknown"

                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    color = Color.White,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = email,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White,
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
                        color = Color.White,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )

                    Text(
                        text = "passwords",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )
                }

                Column {
                    Text(
                        text = "${state.sizeOfStrongPassword}",
                        fontSize = 32.sp,
                        color = Color.White,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )

                    Text(
                        text = strings.strong,
                        fontSize = 12.sp,
                        color = Color.White,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )
                }
                Column {
                    Text(
                        text = "${state.sizeOfWeakPassword}",
                        fontSize = 32.sp,
                        color = Color.White,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )

                    Text(
                        text = strings.weak,
                        fontSize = 12.sp,
                        color = Color.White,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }

            Spacer(modifier = modifier.height(36.dp))

            Row(
                modifier = modifier.padding(horizontal = dimensions.large).fillMaxWidth().height(48.dp)
                    .clip(
                        RoundedCornerShape(16.dp),
                    ).background(color = Color(0xff292933)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = modifier.padding(start = dimensions.large),
                    text = strings.fingerPrint,
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
                    ).background(color = Color(0xff292933))
                    .clickable {
                        wish.invoke(AccountWish.NavigateToChangePassword)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    modifier = modifier.padding(start = 24.dp),
                    text = strings.changePassword,
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
                    ).background(color = Color(0xff292933))
                    .clickable {
                        wish.invoke(AccountWish.OpenUrl("https://github.com/getspherelabs/anypass-kmp/issues/new/choose"))
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    modifier = modifier.padding(start = 24.dp),
                    text = strings.sendFeedback,
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
    backgroundColor: Color = LavenderBlue.copy(0.7f),
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
