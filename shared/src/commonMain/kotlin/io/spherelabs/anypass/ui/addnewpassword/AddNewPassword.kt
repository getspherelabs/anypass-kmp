package io.spherelabs.anypass.ui.addnewpassword

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordEffect
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordState
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordViewModel
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordWish
import io.spherelabs.designsystem.picker.LKSocialMediaPicker
import io.spherelabs.designsystem.spinner.LKSpinner
import io.spherelabs.designsystem.dialog.title
import io.spherelabs.designsystem.image.RoundedImage
import io.spherelabs.designsystem.picker.socialIconsPicker
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.designsystem.textfield.LKEmailTextField
import io.spherelabs.designsystem.textfield.LKNotesTextField
import io.spherelabs.designsystem.textfield.LKPasswordTextField
import io.spherelabs.designsystem.textfield.LKTitleTextField
import io.spherelabs.designsystem.textfield.LKUserNameTextField
import io.spherelabs.designsystem.textfield.LKWebsiteAddressTextField
import io.spherelabs.anypass.MR
import io.spherelabs.anypass.di.useInject
import io.spherelabs.designsystem.hooks.*
import io.spherelabs.designsystem.picker.SocialMedia
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddNewPasswordRoute(
    viewModel: AddNewPasswordViewModel = useInject(),
    navigateToGeneratePassword: () -> Unit,
) {

    val uiState = viewModel.state.collectAsStateWithLifecycle()

    AddNewPasswordScreen(
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        state = uiState.value,
        flow = viewModel.effect,
        navigateToGeneratePassword = {
            navigateToGeneratePassword.invoke()
        },
    )
}

@Composable
fun AddNewPasswordScreen(
    modifier: Modifier = Modifier,
    wish: (AddNewPasswordWish) -> Unit,
    state: AddNewPasswordState,
    flow: Flow<AddNewPasswordEffect>,
    navigateToGeneratePassword: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val snackbarHostState = useSnackbar()
    val scope = useScope()

    val (expanded, setExpanded) = useBooleanState(false)
    val waitForPositiveButton by remember { mutableStateOf(false) }

    useEffect(true) {
        wish.invoke(AddNewPasswordWish.GetCategoriesStarted)

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
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom),
            )
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
                .verticalScroll(scrollState),
        ) {
            Headline(
                text = "Add new password",
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                textColor = Color.Black,
            )

            Text(
                text = "Add new password to your records",
                fontSize = 16.sp,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                color = Color.Black.copy(alpha = 0.5F),
                modifier = modifier.padding(start = 24.dp, top = 8.dp),
            )

            Row(
                modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 8.dp),
            ) {
                RoundedImage(
                    painter = painterResource(MR.images.avatar), contentDescription = null,
                )

                LKTitleTextField(
                    state.title, modifier,
                    onValueChanged = { newValue ->
                        wish.invoke(AddNewPasswordWish.OnTitleChanged(newValue))
                    },
                )

                LKSocialMediaPicker {
                    title("Select a icon")
                    socialIconsPicker(
                        socialIcons = SocialIcons.getSocialMedia().value,
                        waitForPositiveButton = waitForPositiveButton,
                    ) {
                        wish.invoke(AddNewPasswordWish.OnImageChanged(it.title))

                    }
                }
            }


            LKUserNameTextField(
                state.username,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                onValueChanged = { newValue ->
                    wish.invoke(AddNewPasswordWish.OnUserNameChanged(newValue))
                },
                textLength = state.username.length,
            )

            Column {
                Text(
                    text = "Category",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, bottom = 4.dp),
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                )
                LKSpinner(
                    expanded = expanded,
                    modifier = modifier,
                    onExpandedChange = {
                        setExpanded.invoke(it)
                    },
                    current = state.currentCategory,
                    options = state.categories.map { it.title },
                    onOptionChosen = {
                        wish.invoke(AddNewPasswordWish.OnCategoryChanged(it))
                    },
                )

            }

            LKEmailTextField(
                state.email,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                onValueChanged = { newValue ->
                    wish.invoke(AddNewPasswordWish.OnEmailChanged(newValue))
                },
            )

            LKPasswordTextField(
                state.password,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                onValueChanged = { newValue ->
                    wish.invoke(AddNewPasswordWish.OnPasswordChanged(newValue))
                },
            )

            LKWebsiteAddressTextField(
                state.websiteAddress,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                onValueChanged = { newValue ->
                    wish.invoke(AddNewPasswordWish.OnWebsiteAddressChanged(newValue))
                },
            )

            LKNotesTextField(
                state.notes,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                onValueChanged = { newValue ->
                    wish.invoke(AddNewPasswordWish.OnNotesChanged(newValue))
                },
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, bottom = 4.dp, top = 8.dp)
                    .clickable {

                        wish.invoke(AddNewPasswordWish.OnGeneratePasswordClicked)
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Generate password",
                    textAlign = TextAlign.Start,
                    color = Color.Black.copy(0.5f),
                    fontSize = 16.sp,
                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                )
                Box(
                    modifier = modifier.size(16.dp).clip(CircleShape).border(
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(start = 24.dp, end = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(MR.colors.grey),
                ),
                shape = RoundedCornerShape(24.dp),
                onClick = {
                    wish.invoke(AddNewPasswordWish.OnSubmitClicked)
                },
            ) {
                Text(
                    text = "Save password",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = fontFamilyResource(
                        fontResource = MR.fonts.googlesans.medium,
                    ),
                )
            }
        }
    }
}

object SocialIcons {
    @Composable
    fun getSocialMedia(): State<List<SocialMedia>> {
        return useUpdatedState(
            listOf(
                SocialMedia(
                    "Behance",
                    painterResource(MR.images.behance),
                ),
                SocialMedia(
                    "Linkedin",
                    painterResource(MR.images.linkedin),
                ),
                SocialMedia(
                    title = "Dribble",
                    painter = painterResource(MR.images.dribble),
                ),
            ),
        )
    }

    @Composable
    fun get(title: String): State<SocialMedia?> {
        val items = getSocialMedia().value
        return useUpdatedState(
            items.find { it.title == title },
        )
    }

//    @Composable
//    internal fun getSocialIcons(): State<List<Painter>> {
//        return useUpdatedState(
//            listOf(
//                painterResource(MR.images.behance),
//                painterResource(MR.images.linkedin),
//                painterResource(MR.images.apple_podcasts),
//                painterResource(MR.images.quora),
//                painterResource(MR.images.discord),
//                painterResource(MR.images.dribble),
//                painterResource(MR.images.facebook),
//                painterResource(MR.images.googleMeet),
//                painterResource(MR.images.medium),
//                painterResource(MR.images.messenger),
//                painterResource(MR.images.patreon),
//                painterResource(MR.images.reddit),
//                painterResource(MR.images.telegram),
//            ),
//        )
//    }

}


