package passwordhealthimpl.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import domain.model.PasswordStats
import io.spherelabs.common.uuid4
import io.spherelabs.designsystem.collapsingtoolbar.CollapsingToolbarScaffold
import io.spherelabs.designsystem.collapsingtoolbar.ScrollStrategy
import io.spherelabs.designsystem.collapsingtoolbar.rememberCollapsingToolbarScaffoldState
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.foundation.color.BlackRussian
import kotlinx.coroutines.flow.Flow
import passwordhealthimpl.presentation.PasswordHealthEffect
import passwordhealthimpl.presentation.PasswordHealthState
import passwordhealthimpl.presentation.PasswordHealthWish
import passwordhealthimpl.ui.component.PasswordHealthColumn
import passwordhealthimpl.ui.component.PasswordHealthTopBar
import passwordhealthimpl.ui.component.PasswordStatContent
import passwordhealthimpl.ui.component.SemiProgressBar
import passwordhealthimpl.ui.component.SemiProgressBarTokens.animationDuration

@Composable
fun PasswordHealthContent(
    modifier: Modifier = Modifier,
    wish: (PasswordHealthWish) -> Unit,
    uiState: PasswordHealthState,
    // Flow is unstable. FIX!
    uiEffect: Flow<PasswordHealthEffect>,
    navigateToHome: () -> Unit,
) {

  val scrollState = rememberCollapsingToolbarScaffoldState()
  val toolbarProgress = scrollState.toolbarState.progress

  val currentAnimatingProgress =
      animateFloatAsState(
          targetValue = uiState.currentProgress,
          animationSpec =
              tween(
                  durationMillis = animationDuration,
              ),
      )

  useEffect(true) {
    wish.invoke(PasswordHealthWish.StartLoadingPasswords)
    wish.invoke(PasswordHealthWish.StartLoadingPasswordProgress)
    wish.invoke(PasswordHealthWish.StartLoadingPasswordStats)


  }

  CollapsingToolbarScaffold(
      modifier = modifier.fillMaxSize(),
      containerColor = BlackRussian,
      scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
      toolbar = {
        PasswordHealthTopBar(
            modifier = modifier.pin(),
        ) {
          navigateToHome.invoke()
        }
        Header(
            modifier = modifier.parallax().graphicsLayer { alpha = toolbarProgress },
            currentProgress = currentAnimatingProgress.value,
            stats = uiState.stats,
        )
      },
      state = scrollState,
  ) {
    PasswordHealthColumn(
        modifier = modifier,
        passwords = uiState.passwords,
    )
  }
}

@Composable
fun Header(
    modifier: Modifier,
    currentProgress: Float,
    stats: List<PasswordStats>,
) {

  Column(
      modifier = modifier.fillMaxWidth().padding(top = 64.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Spacer(modifier.height(16.dp))
    SemiProgressBar(modifier, currentProgress = currentProgress)
    PasswordStatContent(
        modifier,
        stats = stats,
    )
  }
}

private fun getStats(): List<PasswordStats> {
  return listOf(
      PasswordStats(
          id = uuid4(),
          title = "Total Passwords",
          count = 25,
      ),
      PasswordStats(
          id = uuid4(),
          title = "Strong",
          count = 25,
      ),
      PasswordStats(
          id = uuid4(),
          title = "Weak",
          count = 25,
      ),
      PasswordStats(
          id = uuid4(),
          title = "Reused",
          count = 25,
      ),
  )
}
