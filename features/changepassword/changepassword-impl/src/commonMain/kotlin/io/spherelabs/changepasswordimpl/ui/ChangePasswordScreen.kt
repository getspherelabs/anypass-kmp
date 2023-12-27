package io.spherelabs.changepasswordimpl.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.changepasswordimpl.presentation.ChangePasswordViewModel
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle

class ChangePasswordScreen : Screen {

  @Composable
  override fun Content() {
    val navigator = LocalNavigator.currentOrThrow
    val viewModel: ChangePasswordViewModel = useInject()
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    ChangePasswordContent(
        uiState = uiState.value,
        uiEffect = viewModel.effect,
        wish = { newWish -> viewModel.wish(newWish) },
        navigateToBack = { navigator.pop() },
    )
  }
}
