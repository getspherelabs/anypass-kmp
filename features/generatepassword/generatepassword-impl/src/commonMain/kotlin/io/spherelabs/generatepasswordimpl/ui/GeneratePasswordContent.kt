package io.spherelabs.generatepasswordimpl.ui

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
import io.spherelabs.designsystem.slider.LKSlider
import io.spherelabs.designsystem.slider.LKSliderDefaults
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.*
import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordEffect
import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordState
import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordWish
import io.spherelabs.navigationapi.AddNewPasswordDestination
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow

@Composable
fun GeneratePasswordContent(
    modifier: Modifier = Modifier,
    wish: (GeneratePasswordWish) -> Unit,
    state: GeneratePasswordState,
    flow: Flow<GeneratePasswordEffect>,
    navigateToBack: () -> Unit,
    navigateToCopy: (String) -> Unit,
) {

  val snackbarHostState = useSnackbar()
  val strings = LocalStrings.current
  val navigator = LocalNavigator.currentOrThrow
  val backScreen = rememberScreen(AddNewPasswordDestination.Back(state.password))
  useEffect(true) { wish.invoke(GeneratePasswordWish.GeneratePassword()) }

  Scaffold(
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
        modifier = modifier.fillMaxSize().background(color = Grey).padding(it),
    ) {
      Row(
          modifier = modifier.fillMaxWidth().padding(16.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
      ) {
        Column {
          Text(
              modifier = modifier,
              text = strings.uppercase,
              fontSize = 12.sp,
              fontFamily = GoogleSansFontFamily,
              fontWeight = FontWeight.Medium,
              color = Color.White.copy(alpha = 0.5f),
          )
          LKSlider(
              modifier = modifier.width(100.dp),
              value = state.uppercaseLength,
              onValueChange = { value, _ ->
                wish.invoke(
                    GeneratePasswordWish.OnUppercaseLengthChanged(
                        value,
                    ),
                )
              },
              colors =
                  LKSliderDefaults.sliderColors(
                      thumbColor = LavenderBlue.copy(0.7f),
                      trackColor = LavenderBlue.copy(0.7f),
                      disabledTrackColor = Jaguar,
                  ),
              trackHeight = 8.dp,
              valueRange = 0f..10f,
          ) {
            Box(
                modifier =
                    Modifier.size(18.dp)
                        .background(
                            color = Cinderella,
                            RoundedCornerShape(8.dp),
                        ),
                contentAlignment = Alignment.Center,
            ) {
              Text(
                  text = "${state.uppercaseLength.toInt()}",
                  fontSize = 10.sp,
                  color = Color.Black,
                  fontFamily = GoogleSansFontFamily,
                  fontWeight = FontWeight.Medium,
              )
            }
          }
        }

        Column {
          Text(
              modifier = modifier.padding(start = 24.dp),
              text = strings.digits,
              fontSize = 12.sp,
              fontFamily = GoogleSansFontFamily,
              fontWeight = FontWeight.Medium,
              color = Color.White.copy(alpha = 0.5f),
          )
          LKSlider(
              modifier = modifier.width(100.dp),
              value = state.digitLength,
              onValueChange = { value, _ ->
                wish.invoke(GeneratePasswordWish.OnDigitLengthChanged(value))
              },
              trackHeight = 8.dp,
              colors =
                  LKSliderDefaults.sliderColors(
                      thumbColor = LavenderBlue.copy(0.7f),
                      trackColor = LavenderBlue.copy(0.7f),
                      disabledTrackColor = Jaguar,
                  ),
              valueRange = 0f..10f,
          ) {
            Box(
                modifier =
                    Modifier.size(18.dp)
                        .background(
                            color = Cinderella,
                            RoundedCornerShape(8.dp),
                        ),
                contentAlignment = Alignment.Center,
            ) {
              Text(
                  text = "${state.digitLength.toInt()}",
                  fontSize = 10.sp,
                  color = Color.Black,
                  fontFamily = GoogleSansFontFamily,
                  fontWeight = FontWeight.Medium,
              )
            }
          }
        }

        Column {
          Text(
              modifier = modifier.padding(start = 24.dp),
              text = strings.special,
              fontSize = 12.sp,
              fontFamily = GoogleSansFontFamily,
              fontWeight = FontWeight.Medium,
              color = Color.White.copy(alpha = 0.5f),
          )
          LKSlider(
              modifier = modifier.width(100.dp),
              value = 0f,
              onValueChange = { value, offset -> },
              colors =
                  LKSliderDefaults.sliderColors(
                      thumbColor = LavenderBlue.copy(0.7f),
                      trackColor = LavenderBlue.copy(0.7f),
                      disabledTrackColor = Jaguar,
                  ),
              trackHeight = 8.dp,
              valueRange = 0f..50f,
          ) {
            Box(
                modifier =
                    Modifier.size(18.dp)
                        .background(
                            color = Cinderella,
                            RoundedCornerShape(8.dp),
                        ),
                contentAlignment = Alignment.Center,
            ) {
              Text(
                  text = "0",
                  fontSize = 10.sp,
                  color = Color.Black,
                  fontFamily = GoogleSansFontFamily,
                  fontWeight = FontWeight.Medium,
              )
            }
          }
        }
      }

      Box(
          modifier = modifier.fillMaxWidth().size(250.dp).padding(top = 24.dp),
      ) {
        LKMeterProgress(
            state.length,
            color = LavenderBlue,
            valueFontWeight = FontWeight.Bold,
            valueFontFamily = GoogleSansFontFamily,
            valueColor = Color.White,
        )
      }

      Text(
          text = strings.maximumLimitCharacter.invoke("30"),
          fontFamily = GoogleSansFontFamily,
          fontWeight = FontWeight.Medium,
          color = Color.White.copy(alpha = 0.5f),
          fontSize = 18.sp,
          modifier = modifier.align(Alignment.CenterHorizontally).padding(top = 32.dp),
      )
      Card(
          modifier =
              modifier
                  .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 12.dp)
                  .fillMaxWidth()
                  .height(150.dp),
          backgroundColor = Jaguar,
          shape = RoundedCornerShape(16.dp),
      ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          Text(
              modifier = modifier.padding(top = 12.dp),
              text = strings.randomPassword,
              fontSize = 14.sp,
              fontFamily = GoogleSansFontFamily,
              fontWeight = FontWeight.Medium,
              color = Color.White.copy(0.3f),
          )
          Spacer(modifier = modifier.height(24.dp))

          Text(
              text = state.password,
              fontSize = 24.sp,
              fontFamily = GoogleSansFontFamily,
              fontWeight = FontWeight.Medium,
              color = Color.White,
              modifier = modifier.align(Alignment.CenterHorizontally),
          )
        }
      }
      Row(
          modifier = modifier.fillMaxWidth().padding(top = 12.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
      ) {
        LKBackButton(
            text = strings.back,
            backgroundColor = LavenderBlue.copy(0.7f),
        ) {
          navigateToBack.invoke()
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
                              uppercaseLength = state.uppercaseLength.toInt(),
                              digitLength = state.digitLength.toInt(),
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
          if (state.password.isNotEmpty()) {
            navigator.replace(backScreen)
          }
        }
      }
    }
  }
}
