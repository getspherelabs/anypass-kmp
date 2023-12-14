package io.spherelabs.authenticatorimpl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.authenticatorapi.model.OtpDomain
import io.spherelabs.authenticatorapi.model.RealTimeOtpDomain
import io.spherelabs.designsystem.button.LKNewItemButton
import io.spherelabs.designsystem.dimension.LocalDimensions
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.slider.LKSlider
import io.spherelabs.designsystem.slider.LKSliderDefaults
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.newtokennavigation.NewTokenDestination
import io.spherelabs.resource.fonts.GoogleSansFontFamily

class AuthenticatorScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val newToken = rememberScreen(NewTokenDestination.NewToken)
        AuthenticatorContent {
            navigator.push(newToken)
        }
    }
}

@Composable
fun AuthenticatorContent(
    modifier: Modifier = Modifier,
    navigateToNewToken: () -> Unit,
) {
    val scope = useScope()

    Scaffold(
        containerColor = BlackRussian,
        topBar = {
            AuthenticatorTopBar {
                navigateToNewToken.invoke()
            }
        },
    ) { newPaddingValues ->

    }
}

@Composable
fun BasicAuthenticatorContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    otpData: List<OtpDomain>,
    realTimeOtpDomain: RealTimeOtpDomain,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Authenticators(
            modifier = modifier,
            otpData, realTimeOtpDomain,
        )
    }
}


@Composable
fun Authenticators(
    modifier: Modifier = Modifier,
    otpData: List<OtpDomain>,
    realTimeOtpDomain: RealTimeOtpDomain,
) {
    LazyColumn {
        items(items = otpData) {
            AuthenticatorCard(
                modifier = modifier,
                token = it.secret,
                serviceName = it.serviceName ?: "Unknown",
                info = it.info ?: "Unknown",
                time = it.duration.name,
                realTimeOtpDomain = realTimeOtpDomain,
            )
        }
    }
}

@Composable
fun AuthenticatorCard(
    modifier: Modifier = Modifier,
    realTimeOtpDomain: RealTimeOtpDomain,
    token: String,
    serviceName: String,
    info: String,
    time: String,
) {
    val dimension = LocalDimensions.current

    Column(
        modifier = modifier.fillMaxWidth().height(200.dp).background(color = Jaguar),
    ) {
        Spacer(modifier = modifier.height(8.dp))
        Text(
            modifier = modifier.padding(start = dimension.large),
            text = serviceName,
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            modifier = modifier.padding(start = dimension.large),
            text = info,
            color = Color.White.copy(alpha = 0.5f),
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            modifier = modifier.fillMaxWidth(),
            text = token,
            color = Color.White,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
        )

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                modifier = modifier.padding(start = 12.dp),
                text = time,
                color = Color.White,
                fontFamily = GoogleSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
            )

            LKSlider(
                modifier = modifier.padding(start = 8.dp, end = 12.dp),
                value = realTimeOtpDomain.count.toFloat(),
                onValueChange = { value, _ ->
//                wish.invoke(
//                    GeneratePasswordWish.OnUppercaseLengthChanged(
//                        value,
//                    ),
//                )

                },
                colors = LKSliderDefaults.sliderColors(
                    thumbColor = Color.Red,
                    trackColor = LavenderBlue.copy(0.7f),
                    disabledTrackColor = Color.White,
                    disabledTickColor = Color.White,
                ),
                valueRange = 0f..10f,
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = Color.Transparent,
                        ),
                )
            }
        }

    }
}
