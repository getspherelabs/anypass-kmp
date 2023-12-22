@file:OptIn(ExperimentalMaterialApi::class)

package io.spherelabs.addnewpasswordimpl.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.addnewpasswordimpl.presentation.AddNewPasswordViewModel
import io.spherelabs.designsystem.hooks.*
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.designsystem.textfield.*
import io.spherelabs.navigationapi.GeneratePasswordDestination
import io.spherelabs.resource.icons.anypassicons.*

data class AddNewPasswordScreen(
    val password: String? = null,
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AddNewPasswordViewModel = useInject()
        val uiState = viewModel.state.collectAsStateWithLifecycle()
        val generatePasswordScreen =
            rememberScreen(GeneratePasswordDestination.GeneratePassword)

        AddNewPasswordContent(
            usePassword = password,
            wish = { newWish ->
                viewModel.wish(newWish)
            },
            state = uiState.value,
            flow = viewModel.effect,
            navigateToBack = {
                navigator.pop()
            },
            navigateToGeneratePassword = {
                navigator.push(generatePasswordScreen)
            },
        )
    }
}


