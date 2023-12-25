package io.spherelabs.accountimpl.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.SnackbarHost
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.accountimp.BuildKonfig
import io.spherelabs.accountimpl.presentation.AccountEffect
import io.spherelabs.accountimpl.presentation.AccountState
import io.spherelabs.accountimpl.presentation.AccountWish
import io.spherelabs.accountimpl.ui.component.ChangePassword
import io.spherelabs.accountimpl.ui.component.FingerPrint
import io.spherelabs.accountimpl.ui.component.Logout
import io.spherelabs.accountimpl.ui.component.RestrictScreenshot
import io.spherelabs.accountimpl.ui.component.SendFeedback
import io.spherelabs.admob.GADBannerView
import io.spherelabs.designsystem.button.BackButton
import io.spherelabs.designsystem.dimension.LocalDimensions
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.image.RoundedImage
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.BlackRussian
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
    navigateToSignIn: () -> Unit,
) {

    val snackbarState = useSnackbar()
    val scope = useScope()

    val strings = LocalStrings.current
    val dimensions = LocalDimensions.current

    useEffect(true) {
        wish.invoke(AccountWish.GetAccount)
        wish.invoke(AccountWish.GetStartedFingerPrint)
        wish.invoke(AccountWish.GetStartedRestrictScreenshot)

        effect.collectLatest { newEffect ->
            when (newEffect) {
                AccountEffect.ChangePasswordRoute -> {
                    navigateToChangePassword.invoke()
                }

                is AccountEffect.Failure -> {
                    scope.launch { snackbarState.showSnackbar(newEffect.message) }
                }

                AccountEffect.Back -> {
                    navigateToBack.invoke()
                }

                AccountEffect.SignInScreen -> {
                    navigateToSignIn.invoke()
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom),
            )
        },
        containerColor = BlackRussian,
        topBar = {
            Row(
                modifier = modifier.fillMaxWidth().padding(top = dimensions.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BackButton(
                    backgroundColor = LavenderBlue.copy(0.7f),
                    modifier = modifier,
                    navigateToBack = { wish.invoke(AccountWish.NavigateToBack) },
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
                    imageSize = 100,
                    modifier =
                    modifier
                        .padding(top = dimensions.medium)
                        .border(
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
                    fontSize = 24.sp,
                    color = Color.White,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = email,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Normal,
                )
            }

            Spacer(modifier.height(12.dp))

            Row(
                modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Row {
                    Text(
                        text = "${state.sizeOfTotalPassword}",
                        fontSize = 32.sp,
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
                        fontSize = 26.sp,
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
                        fontSize = 26.sp,
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
            FingerPrint(modifier, isFingerPrintEnabled = state.isFingerPrintEnabled) { newChecked ->
                wish.invoke(
                    AccountWish.SetFingerPrint(
                        newChecked,
                    ),
                )
            }


            Spacer(modifier = modifier.height(12.dp))
            ChangePassword(modifier) {
                wish.invoke(AccountWish.NavigateToChangePassword)
            }


            Spacer(modifier.height(12.dp))
            RestrictScreenshot(modifier, isEnabled = state.isRestrictScreenshotEnabled) { newChecked ->
                wish.invoke(
                    AccountWish.SetRestrictScreenshotChanged(
                        newChecked
                    )
                )
            }

            Spacer(modifier = modifier.height(12.dp))
            SendFeedback(modifier) { newUrl ->
                wish.invoke(AccountWish.OpenUrl(newUrl))
            }


            Spacer(modifier = modifier.height(12.dp))
            Logout(modifier) {
                wish.invoke(AccountWish.Logout)
            }


            Spacer(modifier.weight(1f))
            GADBannerView(modifier = modifier.padding(bottom = 16.dp), adId = BuildKonfig.AD_ID)
        }
    }
}
