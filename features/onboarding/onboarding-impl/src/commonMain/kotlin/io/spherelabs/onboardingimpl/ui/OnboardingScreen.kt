package io.spherelabs.onboardingimpl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.onboardingimpl.presentation.OnboardingEffect
import io.spherelabs.onboardingimpl.presentation.OnboardingState
import io.spherelabs.onboardingimpl.presentation.OnboardingWish
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

class OnboardingScreen : Screen {

    @Composable
    override fun Content() {
        TODO("Not yet implemented")
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    wish: (OnboardingWish) -> Unit,
    state: OnboardingState,
    flow: Flow<OnboardingEffect>,
    navigateToPassword: () -> Unit,
) {

    LaunchedEffect(key1 = true) {
        flow.collectLatest {
            when (it) {
                OnboardingEffect.SignUp -> {
                    navigateToPassword.invoke()
                }

                else -> {}
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize().background(color = BlackRussian),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        when {
            state.isLogged -> {
                useEffect(true) {
                    navigateToPassword.invoke()

                }
            }

            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                )
            }

            state.isFirstTime -> {
                Spacer(modifier = modifier.height(24.dp))

                OnboardingImage(
                    modifier = modifier.fillMaxWidth().weight(1f)
                        .padding(start = 24.dp, end = 24.dp),
                )

                OnboardingHeadline()

                OnboardingDescription(modifier)

                Spacer(modifier.height(24.dp))

                GetStartedButton(modifier) { wish.invoke(OnboardingWish.GetStartedClick) }

                Spacer(modifier = modifier.height(24.dp))
            }
        }

    }
}

@Composable
private fun OnboardingImage(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier,
//        painter = painterResource(MR.images.illustration),
        contentDescription = null,
    )
}

@Composable
private fun OnboardingHeadline() {
    Text(
        text = LocalStrings.current.onboardingHeadline,
        fontSize = 32.sp,
        color = Color.White,
        fontFamily = GoogleSansFontFamily,
        fontWeight = FontWeight.Medium,
    )
}

@Composable
private fun OnboardingDescription(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
        text = LocalStrings.current.onboardingDescription(),
        fontSize = 18.sp,
        fontFamily = GoogleSansFontFamily,
        fontWeight = FontWeight.Medium,
        color = Color.White.copy(0.5F),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun GetStartedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth().height(65.dp).padding(start = 24.dp, end = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = LavenderBlue.copy(0.3f),
        ),
        onClick = {
            onClick.invoke()
        },
    ) {
        GetStartedText()
    }
}

@Composable
private fun GetStartedText() {
    Text(
        text = LocalStrings.current.getStarted,
        fontFamily = GoogleSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        color = Color.White,
    )
}


