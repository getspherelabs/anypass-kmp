package io.spherelabs.authimpl.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.authimpl.presentation.signup.SignUpEffect
import io.spherelabs.authimpl.presentation.signup.SignUpState
import io.spherelabs.authimpl.presentation.signup.SignUpViewModel
import io.spherelabs.authimpl.presentation.signup.SignUpWish
import io.spherelabs.designsystem.button.BackButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.designsystem.textfield.APSNameTextField
import io.spherelabs.designsystem.textfield.KeyPasswordTextField
import io.spherelabs.designsystem.textfield.LKEmailTextField
import io.spherelabs.designsystem.textfield.LKPasswordTextField
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.navigationapi.KeyPasswordDestination
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignUpScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: SignUpViewModel = useInject()
        val uiState = viewModel.state.collectAsStateWithLifecycle()

        val keyPasswordScreen = rememberScreen(KeyPasswordDestination.KeyPassword)

        BasicSignUpScreen(
            modifier = Modifier,
            wish = { newWish -> viewModel.wish(newWish) },
            state = uiState.value,
            effect = viewModel.effect,
            navigateToBack = { navigator.pop() },
            navigateToAddPrivatePassword = { navigator.push(keyPasswordScreen) },
        )
    }
}

@Composable
fun BasicSignUpScreen(
    modifier: Modifier = Modifier,
    wish: (SignUpWish) -> Unit,
    state: SignUpState,
    effect: Flow<SignUpEffect>,
    navigateToBack: () -> Unit,
    navigateToAddPrivatePassword: () -> Unit,
) {
    val snackbarState = useSnackbar()
    val coroutineScope = useScope()

    useEffect(true) {
        effect.collectLatest { newEffect ->
            when (newEffect) {
                SignUpEffect.AddPrivatePassword -> {
                    navigateToAddPrivatePassword.invoke()
                }
                is SignUpEffect.Failure -> {
                    coroutineScope.launch {
                        snackbarState.showSnackbar(
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
        containerColor = BlackRussian,
        topBar = { SignUpTopBar(modifier) { wish.invoke(SignUpWish.Back) } },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom),
            )
        },
    ) { newPaddingValues ->
        SignUpContent(
            state = state,
            paddingValues = newPaddingValues,
            modifier = modifier,
            wish = { newWish -> wish.invoke(newWish) },
        )
    }
}

@Composable
private fun SignUpTopBar(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    val strings = LocalStrings.current

    Row(
        modifier = modifier.padding(top = 16.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BackButton(
            modifier = modifier,
            backgroundColor = LavenderBlue.copy(0.3f),
            iconColor = Color.White,
            navigateToBack = { navigateToBack.invoke() },
        )
        Headline(
            text = strings.createAccount,
            modifier = modifier,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
            textColor = Color.White,
        )
    }
}

@Composable
fun SignUpContent(
    state: SignUpState,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    wish: (SignUpWish) -> Unit,
) {
    val strings = LocalStrings.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier.fillMaxSize().padding(paddingValues).consumeWindowInsets(paddingValues),
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center),
                color = Color.Black.copy(alpha = 0.7f),
            )
        }
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
        ) {
            item {
                APSNameTextField(
                    state.name,
                    fontFamily = GoogleSansFontFamily,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    keyboardActions =
                    KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) },
                        onDone = { focusManager.clearFocus() },
                    ),
                ) { newValue ->
                    wish.invoke(SignUpWish.OnNameChanged(newValue))
                }
                if (state.nameFailed) {
                    Text(
                        modifier = modifier.padding(start = 24.dp, top = 4.dp),
                        text = strings.nameFailure,
                        color = Color.White.copy(alpha = 0.7f),
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    )
                }
            }
            item {
                LKEmailTextField(
                    state.email,
                    fontFamily = GoogleSansFontFamily,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    keyboardActions =
                    KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) },
                        onDone = { focusManager.clearFocus() },
                    ),
                ) { newValue ->
                    wish.invoke(SignUpWish.OnEmailChanged(newValue))
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
            }
            item {
                LKPasswordTextField(
                    textValue = state.password,
                    passwordVisibility = state.isPasswordVisibility,
                    onToggleChanged = { wish.invoke(SignUpWish.TogglePasswordVisibility) },
                    fontFamily = GoogleSansFontFamily,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    keyboardActions =
                    KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) },
                        onDone = { focusManager.clearFocus() },
                    ),
                    onValueChanged = { newValue -> wish.invoke(SignUpWish.OnPasswordChanged(newValue)) },
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
            }
            item {
                KeyPasswordTextField(
                    textValue = state.keyPassword,
                    passwordVisibility = state.isKeyPasswordVisibility,
                    description = strings.keyPasswordRequirement,
                    fontFamily = GoogleSansFontFamily,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next,
                    ),
                    keyboardActions =
                    KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) },
                    ),
                    onToggleChanged = { wish.invoke(SignUpWish.ToggleKeyPasswordVisibility) },
                    onValueChanged = { newValue ->
                        wish.invoke(
                            SignUpWish.OnKeyPasswordChanged(
                                newValue,
                            ),
                        )
                    },
                )

                KeyPasswordTextField(
                    title = "Confirm a key password",
                    description = strings.keyPasswordRequirement,
                    textValue = state.confirmKeyPassword,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions =
                    KeyboardActions(
                        onDone = { focusManager.clearFocus() },
                    ),
                    passwordVisibility = state.isConfirmKeyPasswordVisibility,
                    fontFamily = GoogleSansFontFamily,
                    onToggleChanged = { wish.invoke(SignUpWish.ToggleConfirmKeyPasswordVisibility) },
                    onValueChanged = { newValue ->
                        wish.invoke(SignUpWish.OnConfirmKeyPasswordChanged(newValue))
                    },
                )

                if (state.isKeyPasswordSame) {
                    Text(
                        modifier = modifier.padding(start = 24.dp, top = 4.dp),
                        text = strings.passwordSameFailure,
                        color = Color.White.copy(alpha = 0.7f),
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    )
                }
                Spacer(modifier.height(24.dp))
            }
            item {
                Button(
                    modifier = Modifier.fillMaxWidth().height(65.dp)
                        .padding(start = 24.dp, end = 24.dp),
                    colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = LavenderBlue.copy(0.3f),
                    ),
                    shape = RoundedCornerShape(24.dp),
                    onClick = {
                        wish.invoke(SignUpWish.OnLoadingChanged(true))
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

                Spacer(modifier.height(16.dp))
            }
        }
    }
}
