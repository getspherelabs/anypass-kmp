package io.spherelabs.authenticatorimpl.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
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
import io.spherelabs.authenticatorimpl.presentation.AuthenticatorEffect
import io.spherelabs.authenticatorimpl.presentation.AuthenticatorState
import io.spherelabs.authenticatorimpl.presentation.AuthenticatorViewModel
import io.spherelabs.authenticatorimpl.presentation.AuthenticatorWish
import io.spherelabs.designsystem.dimension.LocalDimensions
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.slider.LKSlider
import io.spherelabs.designsystem.slider.LKSliderDefaults
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.navigationapi.NewTokenDestination
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthenticatorScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AuthenticatorViewModel = useInject()
        val uiState = viewModel.state.collectAsStateWithLifecycle()

        val newToken = rememberScreen(NewTokenDestination.NewToken)

        AuthenticatorContent(
            wish = { newWish -> viewModel.wish(newWish) },
            uiState = uiState.value,
            uiEffect = viewModel.effect,
            navigateToBack = { navigator.pop() },
            navigateToNewToken = { navigator.push(newToken) },
        )
    }
}

@Composable
fun AuthenticatorContent(
    modifier: Modifier = Modifier,
    wish: (AuthenticatorWish) -> Unit,
    uiState: AuthenticatorState,
    uiEffect: Flow<AuthenticatorEffect>,
    navigateToNewToken: () -> Unit,
    navigateToBack: () -> Unit,
) {
    val scope = useScope()
    val snackbarState = useSnackbar()

    useEffect(true) {
        wish.invoke(AuthenticatorWish.GetStartedRealTimeOtp)
        wish.invoke(AuthenticatorWish.GetStartedAccounts)

        uiEffect.collectLatest { newEffect ->
            when (newEffect) {
                is AuthenticatorEffect.Failure -> {
                    scope.launch {
                        snackbarState.showSnackbar(newEffect.message)
                    }
                }
            }
        }
    }

    DisposableEffect(true) { onDispose { wish.invoke(AuthenticatorWish.OnCancellationOtp) } }

    Scaffold(
        containerColor = BlackRussian,
        topBar = { AuthenticatorTopBar { navigateToBack.invoke() } },
        floatingActionButton = {
            AuthenticatorNewItemButton {
                navigateToNewToken.invoke()
            }
        },
    ) { newPaddingValues ->
        BasicAuthenticatorContent(
            newPaddingValues,
            otpData = uiState.accounts,
            realTimeOtpDomain = uiState.realTimeOtp,
            wish = { newWish -> wish.invoke(newWish) },
        )
    }
}

@Composable
fun BasicAuthenticatorContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    otpData: List<OtpDomain>,
    realTimeOtpDomain: Map<String, RealTimeOtpDomain?>,
    wish: (AuthenticatorWish) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(paddingValues),
    ) {
        Authenticators(
            modifier = modifier,
            otpData,
            realTimeOtpDomain,
            wish = { newWish -> wish.invoke(newWish) },
        )
    }
}

@Composable
fun Authenticators(
    modifier: Modifier = Modifier,
    otpData: List<OtpDomain>,
    realTimeOtpDomain: Map<String, RealTimeOtpDomain?>,
    wish: (AuthenticatorWish) -> Unit,
) {
    Spacer(modifier.height(16.dp))
    LazyColumn {
        items(
            items = otpData,
            key = {
                it.id
            },
        ) {
            if (otpData.isNotEmpty()) {
                if (realTimeOtpDomain[it.id] != null) {
                    AuthenticatorCard(
                        modifier = modifier,
                        token = it.secret,
                        serviceName = it.serviceName ?: "Unknown",
                        info = it.info ?: "Unknown",
                        duration = it.duration.value,
                        realTimeOtpDomain = requireNotNull(realTimeOtpDomain[it.id]),
                        wish = { newWish -> wish.invoke(newWish) },
                    )
                }
            }
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
    duration: Long,
    wish: (AuthenticatorWish) -> Unit,
) {
    useEffect(true) {
        if (realTimeOtpDomain.countdown == 1) {
            wish.invoke(AuthenticatorWish.OnRunningChanged(false))
        }
    }

    val dimension = LocalDimensions.current

    val progress by
    animateFloatAsState(
        targetValue = realTimeOtpDomain.countdown.toFloat(),
        animationSpec = tween(500),
    )

    Column(
        modifier = modifier.fillMaxWidth().height(200.dp).background(color = BlackRussian),
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
            text = realTimeOtpDomain.code,
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
                text = "${realTimeOtpDomain.countdown}s",
                color = Color.White,
                fontFamily = GoogleSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
            )

            LKSlider(
                modifier = modifier.padding(start = 8.dp, end = 12.dp),
                value = realTimeOtpDomain.countdown.toFloat(),
                onValueChange = { _, _ -> },
                colors =
                LKSliderDefaults.sliderColors(
                    thumbColor = Color.Red,
                    trackColor = LavenderBlue.copy(0.7f),
                    disabledTrackColor = Color.White,
                    disabledTickColor = Color.White,
                ),
                valueRange = 0f..duration.toFloat(),
            ) {
                Box(
                    modifier =
                    Modifier.size(8.dp)
                        .background(
                            color = Color.Transparent,
                        ),
                )
            }
        }
        Spacer(modifier.fillMaxWidth().height(2.dp).background(Jaguar))
    }
}
