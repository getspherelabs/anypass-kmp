package passwordhealthimpl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.meteorviewmodel.commonflow.asCommonFlow
import passwordhealthimpl.presentation.PasswordHealthViewModel

class PasswordHealthScreen : Screen {

  @Composable
  override fun Content() {
    val navigator = LocalNavigator.currentOrThrow
    val viewModel: PasswordHealthViewModel = useInject()
    val currentUiState = viewModel.state.collectAsStateWithLifecycle()

    PasswordHealthContent(
        wish = { newWish -> viewModel.wish(newWish) },
        uiState = currentUiState.value,
        uiEffect = viewModel.effect,
        navigateToHome = { navigator.pop() },
    )
  }
}
