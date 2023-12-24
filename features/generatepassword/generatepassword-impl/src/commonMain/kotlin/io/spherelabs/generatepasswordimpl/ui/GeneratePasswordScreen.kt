package io.spherelabs.generatepasswordimpl.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordViewModel

class GeneratePasswordScreen : Screen {

  @Composable
  override fun Content() {
    val viewModel: GeneratePasswordViewModel = useInject()
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    GeneratePasswordContent(
        state = uiState.value,
        wish = { newWish -> viewModel.wish(newWish) },
        flow = viewModel.effect,
        navigateToBack = {},
        navigateToCopy = {},
    )
  }
}
