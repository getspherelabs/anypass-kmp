package io.spherelabs.accountimpl.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.accountimpl.presentation.AccountViewModel
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.navigationapi.AuthDestination
import io.spherelabs.navigationapi.ChangePasswordDestination

class AccountScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AccountViewModel = useInject()
        val uiState = viewModel.state.collectAsStateWithLifecycle()
        val changePasswordScreen = rememberScreen(ChangePasswordDestination.ChangePassword)
        val signInScreen = rememberScreen(AuthDestination.SignIn)

        AccountContent(
            wish = { newWish -> viewModel.wish(newWish) },
            state = uiState.value,
            effect = viewModel.effect,
            navigateToBack = { navigator.pop() },
            navigateToChangePassword = { navigator.push(changePasswordScreen) },
            navigateToSignIn = {
                navigator.replaceAll(signInScreen)
            },
        )

    }
}
