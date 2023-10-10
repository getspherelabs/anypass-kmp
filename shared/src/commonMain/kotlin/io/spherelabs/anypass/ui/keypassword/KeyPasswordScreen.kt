package io.spherelabs.anypass.ui.keypassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import io.spherelabs.designsystem.grid.LKGridLayout
import io.spherelabs.designsystem.pininput.LKPinInput
import io.spherelabs.anypass.MR
import io.spherelabs.anypass.di.useInject
import io.spherelabs.biometry.BiometryAuthenticatorFactory
import io.spherelabs.biometry.rememberBiometricManager
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.masterpasswordpresentation.MasterPasswordEffect
import io.spherelabs.masterpasswordpresentation.MasterPasswordState
import io.spherelabs.masterpasswordpresentation.MasterPasswordViewModel
import io.spherelabs.masterpasswordpresentation.MasterPasswordWish
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun KeyPasswordRoute(
    viewModel: MasterPasswordViewModel = useInject(),
    navigateToHome: () -> Unit,
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    KeyPasswordScreen(
        state = uiState.value,
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        navigateToHome = {
            navigateToHome.invoke()
        },
        effect = viewModel.effect,
    )
}

@Composable
fun KeyPasswordScreen(
    modifier: Modifier = Modifier,
    wish: (MasterPasswordWish) -> Unit,
    state: MasterPasswordState,
    effect: Flow<MasterPasswordEffect>,
    navigateToHome: () -> Unit,
) {

    val snackbarState = useSnackbar()
    val coroutineScope = useScope()
    val strings = LocalStrings.current
    val biometryAuthenticatorFactory: BiometryAuthenticatorFactory =
        rememberBiometricManager()

    useEffect(true) {
        effect.collectLatest { newEffect ->
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
            }
        }
    }

    useEffect(true) {
        if (state.isFingerprintEnabled) {
            biometryAuthenticatorFactory.createBiometryAuthenticator().biometricAuthentication(
                title = "Fingerprint",
                description = "Test",
                failureContext = "Failed test",
            ) { result ->
                if (result.isSuccess) {
                    navigateToHome.invoke()
                }
            }
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = colorResource(MR.colors.grey),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom),
            )
        },
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = modifier.padding(start = 32.dp, top = 16.dp),
                text = strings.confirmPassphrase,
                fontSize = 32.sp,
                fontFamily = GoogleSansFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )

            Spacer(modifier = modifier.height(35.dp))

            LKPinInput(
                value = state.password,
                disableKeypad = true,
            ) {
                wish.invoke(MasterPasswordWish.OnPasswordCellChanged(it))
            }

            Spacer(modifier = modifier.height(25.dp))

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
                    .background(color = colorResource(resource = MR.colors.lavender)),
            ) {
                LKGridLayout(
                    items = MasterPasswordState.row1(),
                    fontFamily = GoogleSansFontFamily,
                ) { newPin ->
                    wish.invoke(MasterPasswordWish.OnMasterPasswordChanged(newPin))
                }
                LKGridLayout(
                    items = MasterPasswordState.row2(),
                    fontFamily = GoogleSansFontFamily,
                ) { newPin ->
                    wish.invoke(MasterPasswordWish.OnMasterPasswordChanged(newPin))
                }
                LKGridLayout(
                    items = MasterPasswordState.row3(),
                    fontFamily = GoogleSansFontFamily,
                ) { newPin ->
                    wish.invoke(MasterPasswordWish.OnMasterPasswordChanged(newPin))
                }
                LKGridLayout(
                    items = MasterPasswordState.row4(),
                    fontFamily = GoogleSansFontFamily,
                ) { newPin ->
                    if (newPin == "c") {
                        wish.invoke(MasterPasswordWish.ClearPassword)
                    } else {
                        wish.invoke(MasterPasswordWish.OnMasterPasswordChanged(newPin))
                    }

                }
                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(start = 24.dp, end = 24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(MR.colors.grey),
                    ),
                    shape = RoundedCornerShape(24.dp),
                    onClick = {
                        wish.invoke(MasterPasswordWish.SubmitClicked)
                    },
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
}


