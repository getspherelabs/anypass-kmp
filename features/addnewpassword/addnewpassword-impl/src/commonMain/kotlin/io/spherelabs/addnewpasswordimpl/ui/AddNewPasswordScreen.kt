package io.spherelabs.addnewpasswordimpl.ui


import androidx.compose.runtime.*
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.addnewpasswordimpl.presentation.AddNewPasswordViewModel
import io.spherelabs.designsystem.hooks.*
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.navigationapi.GeneratePasswordDestination
import io.spherelabs.resource.icons.SocialMediaResourceProvider


data class AddNewPasswordScreen(
    val password: String? = null,
    val url: String? = null,
    val name: String? = null,
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AddNewPasswordViewModel = useInject()
        val resourceProvider: SocialMediaResourceProvider = useInject()

        val uiState = viewModel.state.collectAsStateWithLifecycle()
        val generatePasswordScreen = rememberScreen(GeneratePasswordDestination.GeneratePassword)

        println("Url is $url")
        println("Name is $name")

        AddNewPasswordContent(
            usePassword = password,
            website = name,
            websiteUrl = url,
            wish = { newWish -> viewModel.wish(newWish) },
            state = uiState.value,
            flow = viewModel.effect,
            resourceProvider = resourceProvider,
            navigateToBack = { navigator.pop() },
            navigateToGeneratePassword = { navigator.push(generatePasswordScreen) },
        )
    }
}
