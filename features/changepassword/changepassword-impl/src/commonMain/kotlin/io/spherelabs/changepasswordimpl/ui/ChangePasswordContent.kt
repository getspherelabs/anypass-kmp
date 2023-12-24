package io.spherelabs.changepasswordimpl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarHost
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.changepasswordimpl.presentation.ChangePasswordEffect
import io.spherelabs.changepasswordimpl.presentation.ChangePasswordState
import io.spherelabs.changepasswordimpl.presentation.ChangePasswordWish
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.textfield.KeyPasswordTextField
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordContent(
    uiState: ChangePasswordState,
    uiEffect: Flow<ChangePasswordEffect>,
    wish: (ChangePasswordWish) -> Unit,
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    val snackbarState = useSnackbar()
    val scope = useScope()

    useEffect(true) {
        wish.invoke(ChangePasswordWish.GetStartedCurrentKeyPassword)
        uiEffect.collectLatest { newEffect ->
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
                modifier = modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom),
            )
        },
        topBar = {
            ChangePasswordTopBar(
                modifier,
                navigateToBack = { wish.invoke(ChangePasswordWish.NavigateToBack) },
            )
        },
        modifier = modifier,
    ) { newPaddingValues ->
        ChangePasswordContent(
            paddingValues = newPaddingValues,
            modifier = modifier,
            state = uiState,
            wish = { newWish -> wish.invoke(newWish) },
        )
    }
}

@Composable
fun ChangePasswordContent(
    paddingValues: PaddingValues,
    state: ChangePasswordState,
    wish: (ChangePasswordWish) -> Unit,
    modifier: Modifier = Modifier,
) {
    val strings = LocalStrings.current

    Column(
        modifier = modifier.fillMaxSize().padding(paddingValues),
    ) {
        Spacer(modifier.height(16.dp))
        KeyPasswordTextField(
            title = strings.currentKeyPassword,
            textValue = state.currentKeyPassword,
            passwordVisibility = state.isCurrentKeyPasswordVisibility,
            description = strings.keyPasswordRequirement,
            fontFamily = GoogleSansFontFamily,
            onToggleChanged = { wish.invoke(ChangePasswordWish.ToggleCurrentKeyPasswordVisibility) },
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
            onToggleChanged = { wish.invoke(ChangePasswordWish.ToggleNewKeyPasswordVisibility) },
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
            onToggleChanged = { wish.invoke(ChangePasswordWish.ToggleConfirmNewKeyPasswordVisibility) },
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
            onUpdateClicked = { wish.invoke(ChangePasswordWish.OnUpdateClicked) },
        )
    }
}

@Composable
fun BackButton(
    modifier: Modifier,
    backgroundColor: Color = LavenderBlue.copy(0.7f),
    iconColor: Color = Color.White,
    navigateToBack: () -> Unit,
) {

    Box(
        modifier =
        modifier
            .padding(start = 24.dp)
            .size(42.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = backgroundColor)
            .clickable { navigateToBack.invoke() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = iconColor,
            contentDescription = "Back",
        )
    }
}
