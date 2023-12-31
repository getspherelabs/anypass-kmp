package io.spherelabs.newtokenimpl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.newtokenimpl.presentation.NewTokenEffect
import io.spherelabs.newtokenimpl.presentation.NewTokenState
import io.spherelabs.newtokenimpl.presentation.NewTokenWish
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun NewTokenContent(
    modifier: Modifier = Modifier,
    state: NewTokenState,
    effect: Flow<NewTokenEffect>,
    wish: (NewTokenWish) -> Unit,
    navigateToBack: () -> Unit,
) {
    val snackbarHostState = useSnackbar()
    val scope = useScope()

    useEffect(true) {
        effect.collectLatest { newEffect ->
            when (newEffect) {
                is NewTokenEffect.Failure -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            newEffect.message,
                        )
                    }
                }

                is NewTokenEffect.Info -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            newEffect.message,
                        )
                    }
                }
            }
        }
    }
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        containerColor = BlackRussian,
        topBar = {
            NewTokenTopBar {
                navigateToBack.invoke()
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom),
            )
        },
    ) { newPaddingValues ->
        BasicNewTokenContent(
            paddingValues = newPaddingValues,
            state = state,
            wish = wish,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicNewTokenContent(
    paddingValues: PaddingValues,
    state: NewTokenState,
    modifier: Modifier = Modifier,
    wish: (NewTokenWish) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier.fillMaxSize().padding(paddingValues).consumeWindowInsets(paddingValues),
    ) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
        ) {
            item {
                ServiceTextField(
                    modifier = modifier,
                    focusManager = focusManager,
                    textValue = state.serviceName,
                    onTextValueChanged = { newValue ->
                        wish.invoke(NewTokenWish.OnServiceNameChanged(newValue))
                    },
                )
            }
            item {
                SecretTextField(
                    modifier = modifier,
                    focusManager = focusManager,
                    textValue = state.secret,
                    onTextValueChanged = { newValue ->
                        wish.invoke(NewTokenWish.OnSecretChanged(newValue))
                    },
                )
            }

            item {
                AdditionalInfoTextField(
                    modifier = modifier,
                    focusManager = focusManager,
                    textValue = state.info,
                    onTextValueChanged = { newValue ->
                        wish.invoke(
                            NewTokenWish.OnInfoChanged(
                                newValue,
                            ),
                        )
                    },
                )

            }

            item {
                NewTokenTypeSpinner(modifier, state, wish)
            }

            item {
                NewTokenDigitSpinner(modifier, state, wish)
            }

            item {
                NewTokenDurationSpinner(modifier, state, wish)
            }

            item {
                SaveButton(modifier, wish)
            }
        }
    }
}

@Composable
private fun ServiceTextField(
    modifier: Modifier = Modifier,
    textValue: String,
    focusManager: FocusManager,
    onTextValueChanged: (String) -> Unit,
) {
    Column {
        Text(
            text = "Service name",
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            fontFamily = GoogleSansFontFamily,
        )
        TextField(
            modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            keyboardOptions =
            KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
            keyboardActions =
            KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.White.copy(0.7f),
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            onValueChange = { newValue -> onTextValueChanged.invoke(newValue) },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
        )
    }
}
