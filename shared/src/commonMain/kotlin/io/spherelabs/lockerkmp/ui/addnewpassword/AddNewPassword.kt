package io.spherelabs.lockerkmp.ui.addnewpassword

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
import androidx.compose.ui.graphics.painter.Painter
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
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.hooks.useUpdatedState
import io.spherelabs.designsystem.picker.socialIconsPicker
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.designsystem.textfield.LKEmailTextField
import io.spherelabs.designsystem.textfield.LKNotesTextField
import io.spherelabs.designsystem.textfield.LKPasswordTextField
import io.spherelabs.designsystem.textfield.LKTitleTextField
import io.spherelabs.designsystem.textfield.LKUserNameTextField
import io.spherelabs.designsystem.textfield.LKWebsiteAddressTextField
import io.spherelabs.lockerkmp.MR
import io.spherelabs.lockerkmp.components.RoundedImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.compose.rememberKoinInject

@Composable
fun AddNewPasswordRoute(
    viewModel: AddNewPasswordViewModel = rememberKoinInject(),
    navigateToGeneratePassword: () -> Unit
) {

    val uiState = viewModel.state.collectAsState()

    AddNewPasswordScreen(
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        state = uiState,
        flow = viewModel.effect,
        navigateToGeneratePassword = {
            navigateToGeneratePassword.invoke()
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddNewPasswordScreen(
    modifier: Modifier = Modifier,
    wish: (AddNewPasswordWish) -> Unit,
    state: State<AddNewPasswordState>,
    flow: Flow<AddNewPasswordEffect>,
    navigateToGeneratePassword: () -> Unit
) {
    val scrollState = rememberScrollState()
    val snackbarHostState = useSnackbar()
    val scope = useScope()
    val options = List(5) { "Item $it" }
    var expanded by remember { mutableStateOf(false) }
    var option by remember { mutableStateOf("") }
    val waitForPositiveButton by remember { mutableStateOf(false) }

    useEffect(true) {
        flow.collectLatest { effect ->
            when (effect) {
                is AddNewPasswordEffect.Failure -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = effect.message
                        )
                    }

                }

                AddNewPasswordEffect.GeneratePassword -> {
                    navigateToGeneratePassword.invoke()
                }

                is AddNewPasswordEffect.Success -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = effect.message
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
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom)
            )
        }
    ) {
        Column(
            modifier = modifier.fillMaxSize().background(color = Color.White)
                .verticalScroll(scrollState)
        ) {
            Headline(
                text = "Add new password",
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                textColor = Color.Black
            )

            Text(
                text = "Add new password to your records",
                fontSize = 16.sp,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                color = Color.Black.copy(alpha = 0.5F),
                modifier = modifier.padding(start = 24.dp, top = 8.dp)
            )

            Row(
                modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 8.dp),
            ) {
                RoundedImage(
                    painter = painterResource(MR.images.avatar), contentDescription = null
                )

                LKTitleTextField(state.value.title, modifier, onValueChanged = { newValue ->
                    wish.invoke(AddNewPasswordWish.OnTitleChanged(newValue))
                })

                LKSocialMediaPicker {
                    title("Select a icon")
                    socialIconsPicker(
                        socialIcons = SocialIcons.getSocialIcons().value,
                        waitForPositiveButton = waitForPositiveButton
                    ) {
                        println("Painter is $it")
                    }
                }
            }


            LKUserNameTextField(
                state.value.username,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                onValueChanged = { newValue ->
                    wish.invoke(AddNewPasswordWish.OnUserNameChanged(newValue))
                },
                textLength = state.value.username.length
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
                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
                )
                LKSpinner(
                    expanded = expanded,
                    modifier = modifier,
                    onExpandedChange = {
                        expanded = it
                    }, current = option, options = options, onOptionChosen = {
                        option = it
                    })

            }


            LKEmailTextField(state.value.email,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                onValueChanged = { newValue ->
                    wish.invoke(AddNewPasswordWish.OnEmailChanged(newValue))
                })

            LKPasswordTextField(state.value.password,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                onValueChanged = { newValue ->
                    wish.invoke(AddNewPasswordWish.OnPasswordChanged(newValue))
                })

            LKWebsiteAddressTextField(
                state.value.websiteAddress,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                onValueChanged = { newValue ->
                    wish.invoke(AddNewPasswordWish.OnWebsiteAddressChanged(newValue))
                })

            LKNotesTextField(state.value.notes,
                fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                onValueChanged = { newValue ->
                    wish.invoke(AddNewPasswordWish.OnNotesChanged(newValue))
                })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, bottom = 4.dp, top = 8.dp)
                    .clickable {

                        wish.invoke(AddNewPasswordWish.OnGeneratePasswordClicked)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Generate password",
                    textAlign = TextAlign.Start,
                    color = Color.Black.copy(0.5f),
                    fontSize = 16.sp,
                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
                )
                Box(
                    modifier = modifier.size(16.dp).clip(CircleShape).border(
                        width = 1.dp,
                        color = Color.Black.copy(0.5f),
                        shape = CircleShape
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Outlined.ArrowForward,
                        contentDescription = null,
                        tint = Color.Black.copy(0.5f)
                    )
                }
            }


            Spacer(modifier = modifier.height(32.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(start = 24.dp, end = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(MR.colors.grey)
                ),
                shape = RoundedCornerShape(24.dp),
                onClick = {
                    wish.invoke(AddNewPasswordWish.OnSubmitClicked)
                }) {
                Text(
                    text = "Save password",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = fontFamilyResource(
                        fontResource = MR.fonts.googlesans.medium
                    )
                )
            }
        }
    }
}

object SocialIcons {

    @Composable
    internal fun getSocialIcons(): State<List<Painter>> {
        return useUpdatedState(
            listOf(
                painterResource(MR.images.behance),
                painterResource(MR.images.linkedin),
                painterResource(MR.images.apple_podcasts),
                painterResource(MR.images.quora),
                painterResource(MR.images.discord),
                painterResource(MR.images.dribble),
                painterResource(MR.images.facebook),
                painterResource(MR.images.googleMeet),
                painterResource(MR.images.medium),
                painterResource(MR.images.messenger),
                painterResource(MR.images.patreon),
                painterResource(MR.images.reddit),
                painterResource(MR.images.telegram),
            )
        )
    }

}


