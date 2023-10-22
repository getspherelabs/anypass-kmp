package io.spherelabs.anypass.ui.changepassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.SnackbarHost
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import io.spherelabs.anypass.MR
import io.spherelabs.anypass.di.useInject
import io.spherelabs.anypass.ui.account.BackButton
import io.spherelabs.changepasswordpresentation.ChangePasswordEffect
import io.spherelabs.changepasswordpresentation.ChangePasswordState
import io.spherelabs.changepasswordpresentation.ChangePasswordViewModel
import io.spherelabs.changepasswordpresentation.ChangePasswordWish
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.designsystem.textfield.KeyPasswordTextField
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordRoute(
    viewModel: ChangePasswordViewModel = useInject(),
    navigateToBack: () -> Unit,
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    ChangePasswordScreen(
        state = uiState.value,
        effect = viewModel.effect,
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        navigateToBack = {
            navigateToBack.invoke()
        },
    )
}

@Composable
fun ChangePasswordScreen(
    state: ChangePasswordState,
    effect: Flow<ChangePasswordEffect>,
    wish: (ChangePasswordWish) -> Unit,
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    val snackbarState = useSnackbar()
    val scope = useScope()

    useEffect(true) {
        wish.invoke(ChangePasswordWish.GetStartedCurrentKeyPassword)
        effect.collectLatest { newEffect ->
            when (newEffect) {
                is ChangePasswordEffect.Failure -> {
                    scope.launch {
                        snackbarState.showSnackbar(
                            newEffect.message,
                        )
                    }
                }

                is ChangePasswordEffect.Info -> {
                    scope.launch {
                        snackbarState.showSnackbar(
                            newEffect.message,
                        )
                    }
                }

                ChangePasswordEffect.Back -> {
                    navigateToBack.invoke()
                }
            }
        }
    }

    Scaffold(
        containerColor = BlackRussian,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom),
            )
        },
        topBar = {
            ChangePasswordTopBar(
                modifier,
                navigateToBack = {
                    wish.invoke(ChangePasswordWish.NavigateToBack)
                },
            )
        },
        modifier = modifier,
    ) { newPaddingValues ->
        ChangePasswordContent(
            state = state,
            wish = { newWish -> wish.invoke(newWish) },
            paddingValues = newPaddingValues,
        )
    }
}

@Composable
fun ChangePasswordTopBar(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    val strings = LocalStrings.current

    Row(
        modifier = modifier.fillMaxWidth().padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BackButton(
            modifier,
            navigateToBack = {
                navigateToBack.invoke()
            },
        )
        Headline(
            text = strings.changePassword,
            modifier = modifier,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
            textColor = Color.White,
        )

    }
}

@Composable
fun ChangePasswordContent(
    state: ChangePasswordState,
    wish: (ChangePasswordWish) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val strings = LocalStrings.current

    Column(
        modifier = modifier.fillMaxSize().padding(paddingValues = paddingValues),
    ) {
        KeyPasswordTextField(
            title = strings.currentKeyPassword,
            textValue = state.currentKeyPassword,
            passwordVisibility = state.isCurrentKeyPasswordVisibility,
            description = strings.keyPasswordRequirement,
            fontFamily = GoogleSansFontFamily,
            onToggleChanged = {
                wish.invoke(ChangePasswordWish.ToggleCurrentKeyPasswordVisibility)
            },
            onValueChanged = { newValue ->
                wish.invoke(ChangePasswordWish.OnCurrentKeyPasswordChanged(newValue))
            },
        )

        if (state.isCurrentKeyPasswordFailed) {
            Text(
                modifier = modifier.padding(start = 24.dp, top = 4.dp),
                text = strings.passwordSameFailure,
                color = Color.Red.copy(0.7f),
                fontFamily = GoogleSansFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
            )
        }
        KeyPasswordTextField(
            title = strings.newKeyPassword,
            textValue = state.newKeyPassword,
            passwordVisibility = state.isNewKeyPasswordVisibility,
            description = strings.keyPasswordRequirement,
            fontFamily = GoogleSansFontFamily,
            onToggleChanged = {
                wish.invoke(ChangePasswordWish.ToggleNewKeyPasswordVisibility)
            },
            onValueChanged = { newValue ->
                wish.invoke(ChangePasswordWish.OnNewKeyPasswordChanged(newValue))
            },
        )

        KeyPasswordTextField(
            title = strings.confirmNewKeyPassword,
            textValue = state.confirmNewKeyPassword,
            passwordVisibility = state.isConfirmNewKeyPasswordVisibility,
            description = strings.keyPasswordRequirement,
            fontFamily = GoogleSansFontFamily,
            onToggleChanged = {
                wish.invoke(ChangePasswordWish.ToggleConfirmNewKeyPasswordVisibility)
            },
            onValueChanged = { newValue ->
                wish.invoke(ChangePasswordWish.OnConfirmNewKeyPasswordChanged(newValue))
            },
        )

        if (state.isKeyPasswordNotSame) {
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

        UpdateKeyPasswordButton(
            modifier = modifier,
            onUpdateClicked = {
                wish.invoke(ChangePasswordWish.OnUpdateClicked)
            },
        )
    }
}

@Composable
private fun UpdateKeyPasswordButton(
    modifier: Modifier = Modifier,
    onUpdateClicked: () -> Unit,
) {
    val strings = LocalStrings.current
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(start = 24.dp, end = 24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = LavenderBlue.copy(0.7f),
        ),
        shape = RoundedCornerShape(24.dp),
        onClick = {
            onUpdateClicked.invoke()
        },
    ) {
        Text(
            text = strings.updateKeyPassword,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
        )
    }
}
