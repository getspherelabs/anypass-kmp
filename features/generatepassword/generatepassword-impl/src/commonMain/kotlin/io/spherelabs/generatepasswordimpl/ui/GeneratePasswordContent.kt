package io.spherelabs.generatepasswordimpl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.designsystem.button.LKBackButton
import io.spherelabs.designsystem.button.LKUseButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.meterprogress.LKMeterProgress
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.*
import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordEffect
import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordState
import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordWish
import io.spherelabs.generatepasswordimpl.ui.component.DigitSlider
import io.spherelabs.generatepasswordimpl.ui.component.RandomPassword
import io.spherelabs.generatepasswordimpl.ui.component.SpecialSlider
import io.spherelabs.generatepasswordimpl.ui.component.UppercaseSlider
import io.spherelabs.navigationapi.AddNewPasswordDestination
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GeneratePasswordContent(
    modifier: Modifier = Modifier,
    wish: (GeneratePasswordWish) -> Unit,
    uiState: GeneratePasswordState,
    uiEffect: Flow<GeneratePasswordEffect>,
) {
    val navigator = LocalNavigator.currentOrThrow
    val snackbarState = useSnackbar()
    val strings = LocalStrings.current

    val useAndBackScreen = rememberScreen(AddNewPasswordDestination.Back(uiState.password))

    useEffect(true) {
        wish.invoke(GeneratePasswordWish.GeneratePassword())

        uiEffect.collectLatest { newEffect ->
            when (newEffect) {
                is GeneratePasswordEffect.Failure -> {
                    snackbarState.showSnackbar(newEffect.message)
                }

                GeneratePasswordEffect.RouteToBack -> {
                    navigator.pop()
                }

            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom),
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0),
        containerColor = BlackRussian,
        topBar = {
            Row(
                modifier = modifier.padding(top = 16.dp, start = 8.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Headline(
                    text = strings.generatePassword,
                    modifier = modifier,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    textColor = Color.White,
                )

            }
        },
    ) {
        Column(
            modifier = modifier.fillMaxSize().background(color = Grey).padding(it).consumeWindowInsets(paddingValues = it),
        ) {
            Row(
                modifier = modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                UppercaseSlider(
                    modifier = modifier,
                    uppercaseLength = uiState.uppercaseLength,
                    onUppercaseChanged = { newValue ->
                        wish.invoke(GeneratePasswordWish.OnUppercaseLengthChanged(newValue))
                    },
                )

                DigitSlider(
                    modifier = modifier,
                    length = uiState.digitLength,
                    onDigitChanged = { newValue ->
                        wish.invoke(GeneratePasswordWish.OnDigitLengthChanged(newValue))
                    },
                )
                SpecialSlider(
                    modifier = modifier,
                    specialLength = uiState.specialLength,
                    onSpecialChanged = { newValue ->
                        wish.invoke(GeneratePasswordWish.OnSpecialLengthChanged(newValue))
                    },
                )
            }

            Box(
                modifier = modifier.fillMaxWidth().size(250.dp).padding(top = 24.dp),
            ) {
                LKMeterProgress(
                    uiState.length,
                    color = LavenderBlue,
                    valueFontWeight = FontWeight.Bold,
                    valueFontFamily = GoogleSansFontFamily,
                    valueColor = Color.White,
                )
            }
            Row(
                modifier = modifier.fillMaxWidth().padding(top = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = strings.maximumLimitCharacter.invoke("30"),
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 18.sp,
                )
                Spacer(modifier.width(6.dp))
                IconButton(
                    onClick = {},
                    content = {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Outlined.History,
                            contentDescription = null,
                        )
                    },
                )
            }

            RandomPassword(modifier, password = uiState.password)

            Row(
                modifier = modifier.fillMaxWidth().padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                LKBackButton(
                    text = strings.back,
                    backgroundColor = LavenderBlue.copy(0.7f),
                ) {
                    wish.invoke(GeneratePasswordWish.RouteToBack)
                }
                Box(
                    modifier =
                    modifier
                        .size(70.dp)
                        .background(
                            color = LavenderBlue.copy(0.7f),
                            shape = CircleShape,
                        )
                        .clickable {
                            wish.invoke(
                                GeneratePasswordWish.GeneratePassword(
                                    uppercaseLength = uiState.uppercaseLength.toInt(),
                                    digitLength = uiState.digitLength.toInt(),
                                    specialLength = uiState.specialLength.toInt(),
                                ),
                            )
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier =
                        modifier
                            .size(55.dp)
                            .background(
                                color = Jaguar,
                                shape = CircleShape,
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Replay,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                }
                LKUseButton(
                    text = strings.use,
                    backgroundColor = LavenderBlue.copy(0.7f),
                ) {
                    if (uiState.isPasswordValid()) {
                        navigator.replace(useAndBackScreen)
                    }
                }
            }
        }
    }
}
