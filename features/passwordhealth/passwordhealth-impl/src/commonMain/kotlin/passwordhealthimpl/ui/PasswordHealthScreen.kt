package passwordhealthimpl.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class PasswordHealthScreen : Screen {

    @Composable
    override fun Content() {
        PasswordHealthContent(percent = 20F) {}
    }
}
