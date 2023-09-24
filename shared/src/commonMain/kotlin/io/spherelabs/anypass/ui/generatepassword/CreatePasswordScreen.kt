package io.spherelabs.anypass.ui.generatepassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.spherelabs.designsystem.button.LKBackButton
import io.spherelabs.designsystem.button.LKUseButton
import io.spherelabs.designsystem.grid.CreateBoxes
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.meterprogress.LKMeterProgress
import io.spherelabs.designsystem.slider.LKSlider
import io.spherelabs.designsystem.slider.LKSliderDefaults
import io.spherelabs.generatepasswordpresentation.GeneratePasswordEffect
import io.spherelabs.generatepasswordpresentation.GeneratePasswordState
import io.spherelabs.generatepasswordpresentation.GeneratePasswordViewModel
import io.spherelabs.generatepasswordpresentation.GeneratePasswordWish
import io.spherelabs.anypass.MR
import kotlinx.coroutines.flow.Flow
import org.koin.compose.rememberKoinInject

@Composable
fun GeneratePasswordRoute(
    viewModel: GeneratePasswordViewModel = rememberKoinInject(),
    navigateToBack: () -> Unit
) {
    val uiState = viewModel.state.collectAsState()

    GeneratePasswordScreen(
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        state = uiState,
        flow = viewModel.effect,
        navigateToBack = {
            navigateToBack.invoke()
        }
    )
}

@Composable
fun GeneratePasswordScreen(
    modifier: Modifier = Modifier,
    wish: (GeneratePasswordWish) -> Unit,
    state: State<GeneratePasswordState>,
    flow: Flow<GeneratePasswordEffect>,
    navigateToBack: () -> Unit
) {

    val snackbarHostState = useSnackbar()

    useEffect(true) {
        wish.invoke(GeneratePasswordWish.GeneratePassword())
    }

    Column(
        modifier = modifier.fillMaxSize().background(color = colorResource(MR.colors.grey))
    ) {

        Text(
            modifier = modifier.padding(start = 24.dp, top = 8.dp),
            text = "Create Unique Password",
            fontSize = 36.sp,
            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
            color = Color.White
        )

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Text(
                    modifier = modifier,
                    text = "Uppercase",
                    fontSize = 12.sp,
                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                    color = Color.White.copy(alpha = 0.5f)
                )
                LKSlider(
                    modifier = modifier.width(100.dp),
                    value = state.value.uppercaseLength,
                    onValueChange = { value, _ ->
                        wish.invoke(
                            GeneratePasswordWish.OnUppercaseLengthChanged(
                                value
                            )
                        )

                    },
                    colors = LKSliderDefaults.sliderColors(
                        thumbColor = colorResource(MR.colors.cinderella),
                        trackColor = colorResource(MR.colors.cinderella),
                        disabledTrackColor = colorResource(MR.colors.cinderella)
                    ),
                    trackHeight = 8.dp,
                    valueRange = 0f..10f
                ) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(
                                color = colorResource(MR.colors.cinderella),
                                RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${state.value.uppercaseLength.toInt()}",
                            fontSize = 10.sp,
                            color = Color.Black,
                            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
                        )

                    }
                }
            }

            Column {
                Text(
                    modifier = modifier.padding(start = 24.dp),
                    text = "Digits",
                    fontSize = 12.sp,
                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                    color = Color.White.copy(alpha = 0.5f)
                )
                LKSlider(
                    modifier = modifier.width(100.dp),
                    value = state.value.digitLength,
                    onValueChange = { value, _ ->
                        wish.invoke(GeneratePasswordWish.OnDigitLengthChanged(value))
                    },
                    trackHeight = 8.dp,
                    valueRange = 0f..10f
                ) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(
                                color = colorResource(MR.colors.cinderella),
                                RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${state.value.digitLength.toInt()}",
                            fontSize = 10.sp,
                            color = Color.Black,
                            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
                        )

                    }
                }
            }

            Column {
                Text(
                    modifier = modifier.padding(start = 24.dp),
                    text = "Special",
                    fontSize = 12.sp,
                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                    color = Color.White.copy(alpha = 0.5f)
                )
                LKSlider(
                    modifier = modifier.width(100.dp),
                    value = 0f,
                    onValueChange = { value, offset ->

                    },
                    trackHeight = 8.dp,
                    valueRange = 0f..50f
                ) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(
                                color = colorResource(MR.colors.cinderella),
                                RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "0",
                            fontSize = 10.sp,
                            color = Color.Black,
                            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
                        )

                    }
                }
            }
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .size(250.dp)
                .padding(top = 24.dp)
        )
        {
            LKMeterProgress(state.value.length, color = colorResource(MR.colors.lavender))
        }

        Text(
            "Maximum limit character: 30",
            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
            color = Color.White.copy(alpha = 0.5f),
            fontSize = 18.sp,
            modifier = modifier.align(Alignment.CenterHorizontally).padding(top = 32.dp)
        )
        Card(
            modifier = modifier
                .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 12.dp)
                .fillMaxWidth()
                .height(150.dp),
            backgroundColor = colorResource(MR.colors.lavender_pink),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = modifier.padding(top = 12.dp),
                    text = "Random password",
                    fontSize = 14.sp,
                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                    color = Color.White.copy(0.3f)
                )
                Spacer(modifier = modifier.height(24.dp))

                Text(
                    text = state.value.password,
                    fontSize = 24.sp,
                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                    color = Color.White,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
        Row(
            modifier = modifier.fillMaxWidth().padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CreateBoxes(modifier = modifier.fillMaxWidth().align(Alignment.CenterVertically)) {
                LKBackButton("Back", backgroundColor = colorResource(MR.colors.dynamic_yellow)) {
                    navigateToBack.invoke()
                }
                Box(
                    modifier = modifier.size(70.dp)
                        .background(
                            color = Color.Yellow,
                            shape = CircleShape
                        )
                        .clickable {
                            wish.invoke(
                                GeneratePasswordWish.GeneratePassword(
                                    uppercaseLength = state.value.uppercaseLength.toInt(),
                                    digitLength = state.value.digitLength.toInt()
                                )
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = modifier.size(55.dp)
                            .background(
                                color = Color.Black,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Replay,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                }
                LKUseButton("Use", backgroundColor = colorResource(MR.colors.dynamic_yellow)) {

                }
            }
        }
    }

}



