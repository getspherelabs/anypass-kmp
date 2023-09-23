package io.spherelabs.anypass.ui.onboarding

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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.features.onboardingpresentation.OnboardingEffect
import io.spherelabs.features.onboardingpresentation.OnboardingState
import io.spherelabs.features.onboardingpresentation.OnboardingViewModel
import io.spherelabs.features.onboardingpresentation.OnboardingWish
import io.spherelabs.anypass.MR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.rememberKoinInject

@Composable
fun OnboardingRoute(
    viewModel: OnboardingViewModel = rememberKoinInject(),
    navigateToPassword: () -> Unit
) {

    val onboardingState = viewModel.state.collectAsState()

    OnboardingScreen(
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        state = onboardingState,
        flow = viewModel.effect,
        navigateToPassword = {
            navigateToPassword.invoke()
        }
    )
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    wish: (OnboardingWish) -> Unit,
    state: State<OnboardingState>,
    flow: Flow<OnboardingEffect>,
    navigateToPassword: () -> Unit
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
        modifier = modifier.fillMaxSize().background(color = colorResource(MR.colors.white)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when {
            state.value.isLogged -> {
                LaunchedEffect(true) {
                    navigateToPassword.invoke()
                }
            }

            state.value.isLoading -> {
                CircularProgressIndicator(
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
            }

            state.value.isFirstTime -> {
                Spacer(modifier = modifier.height(24.dp))

                OnboardingImage(
                    modifier = modifier.fillMaxWidth().weight(1f)
                        .padding(start = 24.dp, end = 24.dp)
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
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painterResource(MR.images.illustration),
        contentDescription = null
    )
}

@Composable
private fun OnboardingHeadline() {
    Text(
        "Password Manager \n From Anywhere",
        fontSize = 32.sp,
        fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
    )
}

@Composable
private fun OnboardingDescription(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
        text = "Keep your passwords in a secure private vault-and simply access them with one click from all your devices.",
        fontSize = 18.sp,
        fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
        color = Color.Black.copy(0.5F),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun GetStartedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(modifier = modifier
        .fillMaxWidth().height(65.dp).padding(start = 24.dp, end = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(MR.colors.lavender)
        ),
        onClick = {
            onClick.invoke()
        }) {
        GetStartedText()
    }
}

@Composable
private fun GetStartedText() {
    Text(
        text = "Get Started",
        fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
        fontSize = 24.sp,
        color = Color.White
    )
}