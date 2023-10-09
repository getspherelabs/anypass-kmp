package io.spherelabs.anypass.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.SnackbarHost
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.anypass.MR
import dev.icerock.moko.resources.compose.colorResource
import io.spherelabs.authpresentation.signup.SignUpEffect
import io.spherelabs.authpresentation.signup.SignUpState
import io.spherelabs.authpresentation.signup.SignUpViewModel
import io.spherelabs.authpresentation.signup.SignUpWish
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.textfield.LKEmailTextField
import io.spherelabs.designsystem.textfield.LKPasswordTextField
import io.spherelabs.anypass.di.useInject
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.designsystem.textfield.APSNameTextField
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel = useInject(),
    navigateToBack: () -> Unit,
    navigateToAddPrivatePassword: () -> Unit,
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    SignUpScreen(
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        state = uiState.value,
        effect = viewModel.effect,
        navigateToBack = {
            navigateToBack.invoke()
        },
        navigateToAddPrivatePassword = {
            navigateToAddPrivatePassword.invoke()
        },
    )
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    wish: (SignUpWish) -> Unit,
    state: SignUpState,
    effect: Flow<SignUpEffect>,
    navigateToBack: () -> Unit,
    navigateToAddPrivatePassword: () -> Unit,
) {
    val snackbarHostState = useSnackbar()
    val coroutineScope = useScope()

    val strings = LocalStrings.current

    useEffect(true) {
        effect.collectLatest { newEffect ->
            when (newEffect) {
                SignUpEffect.AddPrivatePassword -> {
                    navigateToAddPrivatePassword.invoke()
                }

                is SignUpEffect.Failure -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = newEffect.message,
                        )
                    }
                }

                SignUpEffect.Back -> {
                    navigateToBack.invoke()
                }
            }
        }
    }

    Scaffold(
        containerColor = colorResource(MR.colors.lavender),
        topBar = {
            Row(
                modifier = modifier.fillMaxWidth().padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = modifier.padding(start = 24.dp).size(56.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(color = Color.Black)
                        .clickable {
                            wish.invoke(SignUpWish.Back)
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(color = Color.White),
                    )
                }

            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom),
            )
        },
    ) { newPaddingValues ->
        Column(
            modifier = modifier.fillMaxSize().padding(newPaddingValues),
        ) {
            Text(
                text = strings.createNewAccount,
                fontFamily = GoogleSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 48.sp,
                modifier = modifier.padding(start = 24.dp),
                color = Color.White,
            )
//            Row(
//                modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
//                verticalAlignment = Alignment.CenterVertically,
//            ) {
//                Text(
//                    text = strings.createNewAccount,
//                    fontFamily = GoogleSansFontFamily,
//                    fontWeight = FontWeight.Medium,
//                    fontSize = 32.sp,
//                    color = Color.Black,
//                )
//
//                Image(
//                    modifier = modifier.size(125.dp),
//                    painter = painterResource(MR.images.signup),
//                    contentDescription = null,
//                )
//            }
            APSNameTextField(
                state.name,
                fontFamily = GoogleSansFontFamily,
            ) { newValue ->
                wish.invoke(SignUpWish.OnNameChanged(newValue))
            }
            LKEmailTextField(
                state.email,
                fontFamily = GoogleSansFontFamily,
            ) { newValue ->
                wish.invoke(SignUpWish.OnEmailChanged(newValue))
            }
            LKPasswordTextField(
                state.password,
                fontFamily = GoogleSansFontFamily,
                onValueChanged = { newValue ->
                    wish.invoke(SignUpWish.OnPasswordChanged(newValue))
                }
            )

            Spacer(modifier.height(24.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(start = 24.dp, end = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(MR.colors.grey),
                ),
                shape = RoundedCornerShape(24.dp),
                onClick = {
                    wish.invoke(SignUpWish.OnSignUpClick)
                },
            ) {
                Text(
                    text = strings.signUp,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                )
            }

        }
    }
}
