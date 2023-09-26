package io.spherelabs.anypass.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import io.spherelabs.anypass.MR
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.authpresentation.signin.SignInViewModel
import io.spherelabs.designsystem.textfield.LKEmailTextField
import io.spherelabs.designsystem.textfield.LKPasswordTextField
import io.spherelabs.anypass.di.useInject
import io.spherelabs.authpresentation.signin.SignInEffect
import io.spherelabs.authpresentation.signin.SignInState
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SignInRoute(
    viewModel: SignInViewModel = useInject(),
    navigateToSignUp: () -> Unit,
    navigateToMasterPassword: () -> Unit
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    SignInScreen(
        state = uiState.value,
        effect = viewModel.effect,
        navigateToMasterPassword = {
            navigateToMasterPassword.invoke()
        },
        navigateToSignUp = {
            navigateToSignUp.invoke()
        }
    )
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    state: SignInState,
    effect: Flow<SignInEffect>,
    navigateToMasterPassword: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    val snackbarState = useSnackbar()
    val scope = useScope()

    useEffect(true) {
        effect.collectLatest { newEffect ->
            when (newEffect) {
                is SignInEffect.Failure -> {}
                else -> {}
            }
        }
    }

    Scaffold {
        when {
            state.isCurrentUserExist -> {
                navigateToMasterPassword.invoke()
            }

            else -> {
                Column(
                    modifier = modifier.fillMaxSize().background(color = Color.White),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = " Hey,\n Login Now!",
                            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                            fontSize = 32.sp,
                            color = Color.Black
                        )

                        Image(
                            modifier = modifier.size(125.dp),
                            painter = painterResource(MR.images.signin),
                            contentDescription = null
                        )
                    }

                    LKEmailTextField(
                        "",
                        fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
                    ) {}
                    LKPasswordTextField(
                        "",
                        fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
                    ) {}
                    Spacer(modifier.height(24.dp))
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(start = 24.dp, end = 24.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(MR.colors.grey)
                        ),
                        shape = RoundedCornerShape(24.dp),
                        onClick = {

                        }) {
                        Text(
                            text = "Login",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontFamily = fontFamilyResource(
                                fontResource = MR.fonts.googlesans.medium
                            )
                        )
                    }

                    Spacer(modifier.height(24.dp))

                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Don't have account?",
                            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                            fontSize = 16.sp,
                            color = Color.Black.copy(0.5f)
                        )
                        Spacer(modifier = modifier.width(12.dp))
                        Text(
                            modifier = modifier.clickable {
                                navigateToSignUp.invoke()
                            },
                            text = "Create new",
                            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                            fontSize = 20.sp,
                            color = colorResource(MR.colors.lavender)
                        )
                    }

                }
            }
        }
    }

}
