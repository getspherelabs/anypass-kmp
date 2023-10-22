package io.spherelabs.anypass.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import io.spherelabs.anypass.MR
import io.spherelabs.authpresentation.signin.SignInViewModel
import io.spherelabs.designsystem.textfield.LKEmailTextField
import io.spherelabs.designsystem.textfield.LKPasswordTextField
import io.spherelabs.anypass.di.useInject
import io.spherelabs.authpresentation.signin.SignInEffect
import io.spherelabs.authpresentation.signin.SignInState
import io.spherelabs.authpresentation.signin.SignInWish
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun SignInRoute(
    viewModel: SignInViewModel = useInject(),
    navigateToSignUp: () -> Unit,
    navigateToKeyPassword: () -> Unit,
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    SignInScreen(
        state = uiState.value,
        effect = viewModel.effect,
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        navigateToKeyPassword = {
            navigateToKeyPassword.invoke()
        },
        navigateToCreateNew = {
            navigateToSignUp.invoke()
        },
    )
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    wish: (SignInWish) -> Unit,
    state: SignInState,
    effect: Flow<SignInEffect>,
    navigateToKeyPassword: () -> Unit,
    navigateToCreateNew: () -> Unit,
) {
    val snackbarState = useSnackbar()
    val scope = useScope()

    useEffect(true) {
        effect.collectLatest { newEffect ->
            when (newEffect) {
                is SignInEffect.Failure -> {
                    scope.launch {
                        snackbarState.showSnackbar(
                            newEffect.message,
                        )
                    }
                }

                SignInEffect.CreateNew -> {
                    navigateToCreateNew.invoke()
                }

                SignInEffect.KeyPassword -> {
                    navigateToKeyPassword.invoke()
                }
            }
        }
    }

    Scaffold(
        containerColor = colorResource(MR.colors.lavender),
        topBar = {
            SignInTopBar(modifier = modifier) { newWish ->
                wish.invoke(newWish)
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom),
            )
        },
    ) { newPaddingValues ->
        SignInContent(
            state = state, modifier = modifier, paddingValues = newPaddingValues,
            wish = { newWish ->
                wish.invoke(newWish)
            },
        )

    }
}

@Composable
fun SignInTopBar(
    modifier: Modifier = Modifier,
    wish: (SignInWish) -> Unit,
) {
    val strings = LocalStrings.current

    Row(
        modifier = modifier.fillMaxWidth().padding(top = 16.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        Box(
            modifier = modifier.padding(start = 24.dp).height(56.dp).width(150.dp)
                .padding(start = 8.dp, end = 8.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(color = Color.Black)
                .clickable {
                    wish.invoke(SignInWish.CreateNewClicked)
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                strings.createNewAccount,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = GoogleSansFontFamily,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }

    }

}

@Composable
fun SignInContent(
    state: SignInState,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    wish: (SignInWish) -> Unit,
) {
    val strings = LocalStrings.current

    Box(modifier = modifier.fillMaxSize().padding(paddingValues)) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center),
                color = Color.Black.copy(alpha = 0.5f),
            )
        }

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier.height(32.dp))
            Text(
                modifier = modifier.padding(start = 24.dp),
                text = strings.loginNow,
                fontFamily = GoogleSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 48.sp,
                color = Color.White,
            )
            Spacer(modifier.height(32.dp))
            LKEmailTextField(
                state.email,
                fontFamily = GoogleSansFontFamily,
            ) { newEmail ->
                wish.invoke(SignInWish.OnEmailChanged(newEmail))
            }
            if (state.emailFailed) {
                Text(
                    modifier = modifier.padding(start = 24.dp, top = 4.dp),
                    text = strings.emailFailure,
                    color = Color.White.copy(alpha = 0.7f),
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                )
            }
            LKPasswordTextField(
                modifier = modifier,
                textValue = state.password,
                passwordVisibility = state.isPasswordVisibility,
                fontFamily = GoogleSansFontFamily,
                onToggleChanged = {
                    wish.invoke(SignInWish.TogglePasswordVisibility)
                },
                onValueChanged = { newPassword ->
                    wish.invoke(SignInWish.OnPasswordChanged(newPassword))
                },
            )
            if (state.passwordFailed) {
                Text(
                    modifier = modifier.padding(start = 24.dp, top = 4.dp),
                    text = strings.passwordFailure,
                    color = Color.White.copy(alpha = 0.7f),
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                )
            }
            Spacer(modifier.height(24.dp))
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
                    wish.invoke(SignInWish.OnLoginClicked)
                },
            ) {
                Text(
                    text = strings.login,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier.height(24.dp))
        }
    }
}



