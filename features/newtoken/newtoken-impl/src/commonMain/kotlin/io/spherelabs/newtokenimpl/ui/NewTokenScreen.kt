package io.spherelabs.newtokenimpl.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.newtokenimpl.presentation.NewTokenViewModel

class NewTokenScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: NewTokenViewModel = useInject()
        val uiState = viewModel.state.collectAsStateWithLifecycle()

        NewTokenContent(
            wish = { newWish ->
                viewModel.wish(newWish)
            },
            state = uiState.value,
            effect = viewModel.effect,
            navigateToBack = {
                navigator.pop()
            },
        )
    }
}
