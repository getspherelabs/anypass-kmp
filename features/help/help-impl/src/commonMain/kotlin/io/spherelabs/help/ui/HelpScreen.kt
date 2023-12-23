package io.spherelabs.help.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.help.presentation.HelpViewModel

class HelpScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: HelpViewModel = useInject()
        val currentUiState = viewModel.state.collectAsStateWithLifecycle()

        HelpContent(
            uiState = currentUiState.value,
            uiEffect = viewModel.effect,
            wish = { newWish ->
                viewModel.wish(newWish)
            },
            navigateToBack = {
                navigator.pop()
            },
        )
    }
}
