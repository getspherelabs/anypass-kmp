package io.spherelabs.addnewpasswordimpl.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.addnewpasswordimpl.presentation.AddNewPasswordEffect
import io.spherelabs.addnewpasswordimpl.presentation.AddNewPasswordState
import io.spherelabs.addnewpasswordimpl.presentation.AddNewPasswordWish
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.*
import io.spherelabs.designsystem.textfield.*
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.icons.SocialMediaResourceProvider
import io.spherelabs.resource.icons.anypassicons.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddNewPasswordContent(
    usePassword: String? = null,
    website: String? = null,
    websiteUrl: String? = null,
    resourceProvider: SocialMediaResourceProvider,
    modifier: Modifier = Modifier,
    wish: (AddNewPasswordWish) -> Unit,
    state: AddNewPasswordState,
    flow: Flow<AddNewPasswordEffect>,
    navigateToGeneratePassword: () -> Unit,
    navigateToBack: () -> Unit,
) {
    val snackbarHostState = useSnackbar()
    val scope = useScope()

    useEffectOrNull(website) {
        wish.invoke(AddNewPasswordWish.OnWebsiteAddressChanged(websiteUrl))
        wish.invoke(AddNewPasswordWish.OnTitleChanged(website))
    }

    useEffect(true) {
        wish.invoke(AddNewPasswordWish.GetCategoriesStarted)

        wish.invoke(AddNewPasswordWish.OnPasswordChanged(usePassword))


        flow.collectLatest { effect ->
            when (effect) {
                is AddNewPasswordEffect.Failure -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = effect.message,
                        )
                    }
                }
                AddNewPasswordEffect.GeneratePassword -> {
                    navigateToGeneratePassword.invoke()
                }
                is AddNewPasswordEffect.Success -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = effect.message,
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
            AddNewPasswordTopBar(
                modifier = modifier,
                navigateToBack = { navigateToBack.invoke() },
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom),
            )
        },
    ) { paddingValues ->
        BasicAddNewPasswordContent(
            modifier = modifier,
            paddingValues = paddingValues,
            resourceProvider = resourceProvider,
            state = state,
            wish = { newWish -> wish.invoke(newWish) },
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicAddNewPasswordContent(
    state: AddNewPasswordState,
    resourceProvider: SocialMediaResourceProvider,
    paddingValues: PaddingValues,
    wish: (AddNewPasswordWish) -> Unit,
    modifier: Modifier = Modifier,
) {
    val strings = LocalStrings.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier.fillMaxSize().padding(paddingValues).consumeWindowInsets(paddingValues),
    ) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
        ) {
            item {
                Text(
                    text = strings.newPasswordRecord,
                    fontSize = 16.sp,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = modifier.padding(start = 24.dp, top = 8.dp),
                )

                Row(
                    modifier = modifier.fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 8.dp),
                ) {
                    val img = resourceProvider.loadMedia(state.title)

                    Box(
                        modifier =
                        modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                width = 2.dp,
                                color = Jaguar,
                                shape = RoundedCornerShape(8.dp),
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            imageVector = img,
                            contentDescription = null,
                            modifier = Modifier
                                .requiredSize(28.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop,
                        )

                    }

                    LKTitleTextField(
                        state.title,
                        modifier,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                        ),
                        keyboardActions =
                        KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) },
                        ),
                        onValueChanged = { newValue ->
                            wish.invoke(
                                AddNewPasswordWish.OnTitleChanged(
                                    newValue,
                                ),
                            )
                        },
                    )

                if (state.isTitleFailed) {
                    Text(
                        modifier = modifier.padding(start = 24.dp, top = 4.dp),
                        text = strings.passwordFailure,
                        color = Color.White.copy(alpha = 0.7f),
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    )
                }
            } }

            item {
                LKUserNameTextField(
                    modifier = modifier,
                    textValue = state.username,
                    fontFamily = GoogleSansFontFamily,
                    onValueChanged = { newValue ->
                        wish.invoke(AddNewPasswordWish.OnUserNameChanged(newValue))
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                    ),
                    keyboardActions =
                    KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) },
                    ),
                    textLength = state.username.length,
                )
                if (state.isUserNameFailed) {
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

//            item {
//                Column {
//                    Text(
//                        text = strings.category,
//                        modifier = Modifier.fillMaxWidth().padding(start = 24.dp, bottom = 4.dp),
//                        textAlign = TextAlign.Start,
//                        color = Color.White,
//                        fontSize = 18.sp,
//                        fontFamily = GoogleSansFontFamily,
//                        fontWeight = FontWeight.Medium,
//                    )
//                    LKSpinner(
//                        expanded = state.isExpanded,
//                        modifier = modifier,
//                        onExpandedChange = { wish.invoke(AddNewPasswordWish.OnExpandChanged(it)) },
//                        current = state.currentCategory,
//                        options = state.categories.map { it.title },
//                        onOptionChosen = { wish.invoke(AddNewPasswordWish.OnCategoryChanged(it)) },
//                    )
//                }
//            }

            item {
                LKEmailTextField(
                    state.email,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    keyboardActions =
                    KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) },
                    ),
                    fontFamily = GoogleSansFontFamily,
                    onValueChanged = { newValue ->
                        wish.invoke(
                            AddNewPasswordWish.OnEmailChanged(
                                newValue,
                            ),
                        )
                    },
                )
                if (state.isEmailFailed) {
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
                    onToggleChanged = { wish.invoke(AddNewPasswordWish.ToggleVisibility) },
                    onNextCallback = { focusManager.moveFocus(FocusDirection.Down) },
                    fontFamily = GoogleSansFontFamily,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    keyboardActions =
                    KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) },
                    ),
                    onValueChanged = { newValue ->
                        wish.invoke(AddNewPasswordWish.OnPasswordChanged(newValue))
                    },
                )

                if (state.isPasswordFailed) {
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
                LKWebsiteAddressTextField(
                    state.websiteAddress,
                    fontFamily = GoogleSansFontFamily,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    keyboardActions =
                    KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) },
                    ),
                    onValueChanged = { newValue ->
                        wish.invoke(AddNewPasswordWish.OnWebsiteAddressChanged(newValue))
                    },
                )
                if (state.isWebsiteFailed) {
                    Text(
                        modifier = modifier.padding(start = 24.dp, top = 4.dp),
                        text = strings.websiteAddressFailure,
                        color = Color.Black.copy(alpha = 0.7f),
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    )
                }
            }

            item {
                LKNotesTextField(
                    state.notes,
                    fontFamily = GoogleSansFontFamily,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions =
                    KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        },
                    ),
                    onValueChanged = { newValue ->
                        wish.invoke(
                            AddNewPasswordWish.OnNotesChanged(
                                newValue,
                            ),
                        )
                    },
                )
                if (state.isNotesFailed) {
                    Text(
                        modifier = modifier.padding(start = 24.dp, top = 4.dp),
                        text = strings.notesFailure,
                        color = Color.Black.copy(alpha = 0.7f),
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    )
                }

            }

            item {
                Row(
                    modifier =
                    Modifier.fillMaxWidth().padding(start = 24.dp, bottom = 4.dp, top = 8.dp)
                        .clickable {
                            wish.invoke(AddNewPasswordWish.OnGeneratePasswordClicked)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = strings.generatePassword,
                        textAlign = TextAlign.Start,
                        color = Color.White.copy(0.7f),
                        fontSize = 16.sp,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )
                    Box(
                        modifier =
                        modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp,
                                color = Color.Black.copy(0.5f),
                                shape = CircleShape,
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            Icons.Outlined.ArrowForward,
                            contentDescription = null,
                            tint = Color.Black.copy(0.5f),
                        )
                    }
                }

                Spacer(modifier = modifier.height(32.dp))

                Button(
                    modifier = Modifier.fillMaxWidth().height(65.dp)
                        .padding(start = 24.dp, end = 24.dp),
                    colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = LavenderBlue.copy(0.3f),
                    ),
                    shape = RoundedCornerShape(24.dp),
                    onClick = { wish.invoke(AddNewPasswordWish.OnSubmitClicked) },
                ) {
                    Text(
                        text = strings.savePassword,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = GoogleSansFontFamily,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }
}
