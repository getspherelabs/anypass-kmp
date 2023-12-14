package io.spherelabs.generatepasswordimpl.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.addnewpasswordnavigation.AddNewPasswordSharedScreen
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordViewModel

class GeneratePasswordScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: GeneratePasswordViewModel = useInject()
        val uiState = viewModel.state.collectAsStateWithLifecycle()


        GeneratePasswordContent(
            state = uiState.value,
            wish = { newWish ->
                viewModel.wish(newWish)
            },
            flow = viewModel.effect,
            navigateToBack = {},
            navigateToCopy = {

            },
        )
    }
}
