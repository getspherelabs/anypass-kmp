package io.spherelabs.anypass.ui.newtoken

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import io.spherelabs.anypass.alias.BackHandler
import io.spherelabs.anypass.di.useInject
import io.spherelabs.anypass.ui.account.BackButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.spinner.LKSpinner
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.newtokenimpl.presentation.NewTokenEffect
import io.spherelabs.newtokenimpl.presentation.NewTokenState
import io.spherelabs.newtokenimpl.presentation.NewTokenViewModel
import io.spherelabs.newtokenimpl.presentation.NewTokenWish
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun NewTokenRoute(
    viewModel: NewTokenViewModel = useInject(),
    navigateToBack: BackHandler,
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    NewTokenScreen(
        state = uiState.value,
        effect = viewModel.effect,
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        navigateToBack = navigateToBack,
    )
}

@Composable
fun NewTokenScreen(
    modifier: Modifier = Modifier,
    state: NewTokenState,
    effect: Flow<NewTokenEffect>,
    wish: (NewTokenWish) -> Unit,
    navigateToBack: BackHandler,
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
        containerColor = BlackRussian,
        topBar = {
            NewTokenTopBar {
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom),
            )
        },
    ) { newPaddingValues ->
        NewTokenContent(
            paddingValues = newPaddingValues,
            state = state,
            wish = wish,
            navigateToBack = {
                navigateToBack.invoke()
            },
        )
    }
}

@Composable
fun NewTokenContent(
    paddingValues: PaddingValues,
    state: NewTokenState,
    modifier: Modifier = Modifier,
    wish: (NewTokenWish) -> Unit,
    navigateToBack: BackHandler,
) {
    val focusManager = LocalFocusManager.current
    val strings = LocalStrings.current
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = BlackRussian)
            .verticalScroll(scrollState),
    ) {
        ServiceTextField(
            modifier = modifier,
            focusManager = focusManager,
            textValue = state.serviceName,
            onTextValueChanged = { newValue ->
                wish.invoke(NewTokenWish.OnServiceNameChanged(newValue))
            },
        )
        SecretTextField(
            modifier = modifier,
            focusManager = focusManager,
            textValue = state.secret,
            onTextValueChanged = { newValue ->
                wish.invoke(NewTokenWish.OnSecretChanged(newValue))
            },
        )

        AdditionalInfoTextField(
            modifier = modifier,
            focusManager = focusManager,
            textValue = state.info,
            onTextValueChanged = { newValue ->
                wish.invoke(NewTokenWish.OnInfoChanged(newValue))
            },
        )
        NewTokenTypeSpinner(modifier, state, wish)
        NewTokenDigitSpinner(modifier, state, wish)
        NewTokenDurationSpinner(modifier, state, wish)
        SaveButton(modifier, wish)
    }
}

@Composable
fun NewTokenTopBar(
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
            backgroundColor = LavenderBlue.copy(0.7f),
            iconColor = Color.White,
            navigateToBack = {
                navigateToBack.invoke()
            },
        )
        Headline(
            text = strings.newToken,
            modifier = modifier,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
            textColor = Color.White,
        )
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
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
            ),
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.Black,
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

@Composable
private fun SecretTextField(
    modifier: Modifier = Modifier,
    textValue: String,
    focusManager: FocusManager,
    onTextValueChanged: (String) -> Unit,
) {
    Column {
        Text(
            text = "Secret key",
            modifier = modifier.fillMaxWidth().padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            fontFamily = GoogleSansFontFamily,
        )
        TextField(
            modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            value = textValue,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
            ),
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.Black,
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

@Composable
private fun AdditionalInfoTextField(
    modifier: Modifier = Modifier,
    textValue: String,
    focusManager: FocusManager,
    onTextValueChanged: (String) -> Unit,
) {
    Column {
        Text(
            text = "Additional info",
            modifier = modifier.fillMaxWidth()
                .padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            fontFamily = GoogleSansFontFamily,
        )
        TextField(
            modifier = modifier.fillMaxWidth().padding(start = 12.dp, end = 12.dp),
            value = textValue,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
            ),
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.Black,
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

@Composable
private fun NewTokenTypeSpinner(
    modifier: Modifier = Modifier,
    state: NewTokenState,
    wish: (NewTokenWish) -> Unit,
) {
    Column {
        Spacer(modifier.height(8.dp))
        Text(
            text = "Algorithm Type",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
        )
        LKSpinner(
            expanded = state.isTypeExpanded,
            modifier = modifier,
            onExpandedChange = {
                wish.invoke(NewTokenWish.OnTypeExpandChanged(it))
            },
            current = state.type,
            options = NewTokenState.types(),
            onOptionChosen = {
                wish.invoke(NewTokenWish.OnTypeChanged(it))
            },
        )

    }
}

@Composable
private fun NewTokenDigitSpinner(
    modifier: Modifier = Modifier,
    state: NewTokenState,
    wish: (NewTokenWish) -> Unit,
) {
    Column {
        Spacer(modifier.height(8.dp))
        Text(
            text = "Digits",
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
        )
        LKSpinner(
            expanded = state.isDigitExpanded,
            modifier = modifier,
            onExpandedChange = {
                wish.invoke(NewTokenWish.OnDigitExpandChanged(it))
            },
            current = state.digit,
            options = NewTokenState.digits().map { it.toString() },
            onOptionChosen = { newOption ->
                wish.invoke(NewTokenWish.OnDigitChanged(newOption))
            },
        )

    }
}


@Composable
private fun NewTokenDurationSpinner(
    modifier: Modifier = Modifier,
    state: NewTokenState,
    wish: (NewTokenWish) -> Unit,
) {
    Column {
        Spacer(modifier.height(8.dp))
        Text(
            text = "Duration",
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
        )
        LKSpinner(
            expanded = state.isDurationExpanded,
            modifier = modifier,
            onExpandedChange = {
                wish.invoke(NewTokenWish.OnDurationExpandChanged(it))
            },
            current = state.duration.toString(),
            options = NewTokenState.duration().map { it.toString() },
            onOptionChosen = { newOption ->
                wish.invoke(NewTokenWish.OnDurationChanged(newOption.toInt()))
            },
        )

    }
}

@Composable
private fun SaveButton(
    modifier: Modifier = Modifier,
    wish: (NewTokenWish) -> Unit,
) {
    val strings = LocalStrings.current
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(start = 24.dp, end = 24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = LavenderBlue.copy(0.3f),
        ),
        shape = RoundedCornerShape(24.dp),
        onClick = {
            wish.invoke(NewTokenWish.OnSubmitClicked)
        },
    ) {
        Text(
            text = strings.save,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
        )
    }
}
