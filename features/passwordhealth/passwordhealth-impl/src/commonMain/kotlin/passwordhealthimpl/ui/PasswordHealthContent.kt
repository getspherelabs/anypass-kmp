package passwordhealthimpl.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.spherelabs.foundation.color.BlackRussian
import passwordhealthimpl.ui.component.PasswordHealthTopBar
import passwordhealthimpl.ui.component.SemiProgressBar


@Composable
fun PasswordHealthContent(
    modifier: Modifier = Modifier,
    percent: Float,
    navigateToHome: () -> Unit,
) {
    Scaffold(
        containerColor = BlackRussian,
        topBar = {
            PasswordHealthTopBar {
                navigateToHome.invoke()
            }
        },
    ) { newPaddingValues ->
        Column(
            modifier = modifier.fillMaxSize().padding(newPaddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier.height(16.dp))
            SemiProgressBar(currentProgress = percent)
        }
    }


}

