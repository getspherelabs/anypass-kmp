package io.spherelabs.anypass.ui.keypassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        backgroundColor = color,
        topBar = {
            Row(
                modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = strings.confirmKeyPassword,
                    fontSize =  28.sp,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom),
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
                    cellColor = color2,
                    value = state.password,
                    disableKeypad = true,
                ) {
                    wish.invoke(MasterPasswordWish.OnPasswordCellChanged(it))
                }
            }

            keypads.forEach { keypad ->
                LKGridLayout(
                    modifier = modifier.background(color = color),
                    items = keypad,
                    fontFamily = GoogleSansFontFamily,
                ) { newPin ->

                    if (newPin == "c") {
                        wish.invoke(MasterPasswordWish.ClearPassword)
                    } else {
                        wish.invoke(MasterPasswordWish.OnMasterPasswordChanged(newPin))
                    }
                }
            }
//            Column(
//                verticalArrangement = Arrangement.SpaceEvenly,
//                modifier = modifier
//                    .fillMaxSize()
//                    .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
//                    .background(color = colorResource(resource = MR.colors.lavender)),
//            ) {

//                LKGridLayout(
//                    items = MasterPasswordState.row1(),
//                    fontFamily = GoogleSansFontFamily,
//                ) { newPin ->
//                    wish.invoke(MasterPasswordWish.OnMasterPasswordChanged(newPin))
//                }
//                LKGridLayout(
//                    items = MasterPasswordState.row2(),
//                    fontFamily = GoogleSansFontFamily,
//                ) { newPin ->
//                    wish.invoke(MasterPasswordWish.OnMasterPasswordChanged(newPin))
//                }
//                LKGridLayout(
//                    items = MasterPasswordState.row3(),
//                    fontFamily = GoogleSansFontFamily,
//                ) { newPin ->
//                    wish.invoke(MasterPasswordWish.OnMasterPasswordChanged(newPin))
//                }
//                LKGridLayout(
//                    items = MasterPasswordState.row4(),
//                    fontFamily = GoogleSansFontFamily,
//                ) { newPin ->
//
//
//                }

            Spacer(modifier = modifier.height(32.dp))

            Button(
                modifier = modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(start = 24.dp, end = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff9C98F6).copy(0.7f),
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

            //}
        }
    }
}


val keypads: Array<List<String>> = arrayOf(
    listOf("1", "2", "3"),
    listOf("4", "5", "6"),
    listOf("7", "8", "9"),
    listOf("<", "0", "c"),
)

val color: Color = Color(0xff141419)
val color2: Color = Color(0xff292933)
