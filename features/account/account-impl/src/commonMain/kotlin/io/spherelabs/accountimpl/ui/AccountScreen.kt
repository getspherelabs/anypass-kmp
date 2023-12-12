package io.spherelabs.accountimpl.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.accountimpl.presentation.AccountViewModel
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle

class AccountScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AccountViewModel = useInject()
        val uiState = viewModel.state.collectAsStateWithLifecycle()

        AccountContent(
            wish = { newWish ->
                viewModel.wish(newWish)
            },
            state = uiState.value,
            effect = viewModel.effect,
            navigateToBack = {
                navigator.pop()
            },
            navigateToChangePassword = {},
        )
    }
}
