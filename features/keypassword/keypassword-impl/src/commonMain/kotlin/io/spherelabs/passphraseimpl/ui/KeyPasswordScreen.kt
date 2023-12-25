package io.spherelabs.passphraseimpl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import io.spherelabs.biometry.BiometryAuthenticatorFactory
import io.spherelabs.biometry.rememberBiometricManager
import io.spherelabs.designsystem.button.KeypadType
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.grid.LKGridLayout
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.pininput.LKPinInput
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.navigationapi.HomeDestination
import io.spherelabs.passphraseimpl.presentation.MasterPasswordEffect
import io.spherelabs.passphraseimpl.presentation.MasterPasswordState
import io.spherelabs.passphraseimpl.presentation.MasterPasswordViewModel
import io.spherelabs.passphraseimpl.presentation.MasterPasswordWish
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class KeyPasswordScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: MasterPasswordViewModel = useInject()
        val homeScreen = rememberScreen(HomeDestination.HomeScreen)
        val uiState = viewModel.state.collectAsStateWithLifecycle()

        KeyPasswordContent(
            wish = { newWish -> viewModel.wish(newWish) },
            state = uiState.value,
            effect = viewModel.effect,
            navigateToHome = { navigator.replace(homeScreen) },
        )
    }
}

@Composable
fun KeyPasswordContent(
    modifier: Modifier = Modifier,
    wish: (MasterPasswordWish) -> Unit,
    state: MasterPasswordState,
    effect: Flow<MasterPasswordEffect>,
    navigateToHome: () -> Unit,
) {
    val snackbarState = useSnackbar()
    val coroutineScope = useScope()

    val strings = LocalStrings.current

    val biometryAuthenticatorFactory: BiometryAuthenticatorFactory = rememberBiometricManager()

    useEffect(effect) {
        effect.collect { newEffect ->
            when (newEffect) {
                is MasterPasswordEffect.Failure -> {
                    coroutineScope.launch {
                        snackbarState.showSnackbar(
                            message = newEffect.message,
                        )
                    }
                }

                MasterPasswordEffect.Home -> {
                    navigateToHome.invoke()
                }

                MasterPasswordEffect.ShowFingerPrint -> {
                    if (state.isFingerprintEnabled) {
                        biometryAuthenticatorFactory.createBiometryAuthenticator()
                            .biometricAuthentication(
                                title = "Sign in with your fingerprint?",
                                description = "Use your fingerprint to sign in with AnyPass",
                                failureContext = "Accessing fingerprint is failed",
                            ) { result ->
                                result.onSuccess { isAccepted ->
                                    if (isAccepted) {
                                        navigateToHome.invoke()
                                    }
                                }.onFailure {
                                    val failureMessage = it.message ?: "Error is occurred."
                                    wish.invoke(MasterPasswordWish.FingerPrintFailure(failureMessage))
                                }
                            }
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = BlackRussian,
        topBar = {
            Row(
                modifier = modifier.fillMaxWidth().padding(24.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = strings.confirmKeyPassword,
                    fontSize = 24.sp,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom),
            )
        },
    ) { newPaddingValues ->
        Column(
            modifier = modifier.fillMaxSize().padding(newPaddingValues),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = modifier.weight(1f).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                LKPinInput(
                    cellColor = Jaguar,
                    value = state.password,
                    disableKeypad = true,
                ) {
                    wish.invoke(MasterPasswordWish.OnPasswordCellChanged(it))
                }
            }

            keypads.forEach { keypad ->
                LKGridLayout(
                    modifier = modifier.background(color = BlackRussian),
                    items = keypad,
                    fontFamily = GoogleSansFontFamily,
                ) { (type, value) ->
                    when (type) {
                        KeypadType.Number -> {
                            wish.invoke(MasterPasswordWish.OnMasterPasswordChanged(value))
                        }

                        KeypadType.FingerPrint -> {
                            wish.invoke(MasterPasswordWish.ShowFingerPrint)
                        }

                        KeypadType.Clear -> {
                            wish.invoke(MasterPasswordWish.ClearPassword)
                        }
                    }
                }
            }

            Spacer(modifier = modifier.height(32.dp))

            Button(
                modifier =
                modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(start = 24.dp, end = 24.dp),
                colors =
                ButtonDefaults.buttonColors(
                    backgroundColor = LavenderBlue.copy(0.7f),
                ),
                shape = RoundedCornerShape(24.dp),
                onClick = { wish.invoke(MasterPasswordWish.SubmitClicked) },
            ) {
                Text(
                    text = strings.submit,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                )
            }
            Spacer(modifier = modifier.height(16.dp))
        }
    }
}
