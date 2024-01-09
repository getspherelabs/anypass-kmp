package io.spherelabs.passwordhistoryimpl.ui


import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryEffect
import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryState
import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryViewModel
import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryWish
import io.spherelabs.passwordhistoryimpl.ui.component.PasswordHistoryColumn
import io.spherelabs.passwordhistoryimpl.ui.component.PasswordHistoryTopBar
import kotlinx.coroutines.flow.Flow

class PasswordHistoryScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: PasswordHistoryViewModel = useInject()
        val newUiState = viewModel.state.collectAsStateWithLifecycle()

        PasswordHistoryContent(
            uiState = newUiState.value,
            uiEffect = viewModel.effect,
            onStartLoadingPasswordHistory = { viewModel.wish(PasswordHistoryWish.StartLoadingPasswordHistory) },
            onBackClick = { navigator.pop() },
            onClearPasswordClick = { viewModel.wish(PasswordHistoryWish.OnClearPasswordHistory) },
        )
    }
}

@Composable
internal fun PasswordHistoryContent(
    modifier: Modifier = Modifier,
    uiState: PasswordHistoryState,
    uiEffect: Flow<PasswordHistoryEffect>,
    onStartLoadingPasswordHistory: () -> Unit,
    onBackClick: () -> Unit,
    onClearPasswordClick: () -> Unit,
) {

    useEffect(true) {
        onStartLoadingPasswordHistory.invoke()
    }
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        containerColor = BlackRussian,
        topBar = {
            PasswordHistoryTopBar {
                onBackClick.invoke()
            }
        },
    ) { newPaddingValues ->
        PasswordHistoryColumn(
            modifier = modifier.padding(newPaddingValues),
            passwords = uiState.history,
            onClearPasswordClick = { onClearPasswordClick.invoke() },
        )
    }
}
