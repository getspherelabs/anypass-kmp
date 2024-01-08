package io.spherelabs.passwordhistoryimpl.ui


import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryEffect
import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryState
import io.spherelabs.passwordhistoryimpl.ui.component.PasswordHistoryTopBar
import kotlinx.coroutines.flow.Flow

class PasswordHistoryScreen : Screen {

    @Composable
    override fun Content() {

    }
}

@Composable
internal fun PasswordHistoryContent(
    uiState: PasswordHistoryState,
    uiEffect: Flow<PasswordHistoryEffect>,
    onToggleVisibility: () -> Unit,
    onBackClick: () -> Unit,
) {

    Scaffold(
        topBar = {
            PasswordHistoryTopBar {
                onBackClick.invoke()
            }
        },
    ) { newPaddingValues ->

    }
}
