package io.spherelabs.help.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.designsystem.button.BackButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.help.presentation.HelpEffect
import io.spherelabs.help.presentation.HelpState
import io.spherelabs.help.presentation.HelpWish
import io.spherelabs.help.ui.component.ContactSupport
import io.spherelabs.help.ui.component.ExpandableCard
import io.spherelabs.model.FAQs
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun HelpContent(
    modifier: Modifier = Modifier,
    wish: (HelpWish) -> Unit,
    uiState: HelpState,
    uiEffect: Flow<HelpEffect>,
    navigateToBack: () -> Unit,
) {
  val snackbarState = useSnackbar()
  val scope = useScope()

  useEffect(true) {
    wish.invoke(HelpWish.StartLoadingGetFaqs)
    wish.invoke(HelpWish.LoadedEmail)

    uiEffect.collectLatest { newEffect ->
      when (newEffect) {
        is HelpEffect.Failure -> {
          scope.launch {
            snackbarState.showSnackbar(
                message = newEffect.message,
            )
          }
        }
        HelpEffect.Back -> navigateToBack.invoke()
      }
    }
  }

  Scaffold(
      containerColor = BlackRussian,
      snackbarHost = {
        SnackbarHost(
            hostState = snackbarState,
            modifier = modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom),
        )
      },
      topBar = {
        HelpTopBar(
            modifier,
            navigateToBack = { wish.invoke(HelpWish.NavigateToBack) },
        )
      },
      modifier = modifier,
  ) { newPaddingValues ->
    BasicHelpContent(
        modifier = modifier.padding(newPaddingValues),
        faqs = uiState.list,
        contactSupport = uiState.email,
    )
  }
}

@Composable
fun BasicHelpContent(
    modifier: Modifier,
    faqs: FAQs,
    contactSupport: String,
) {

  Column(
      modifier.fillMaxSize().padding(top = 16.dp),
  ) {
    ContactSupport(
        title = "Write us at",
        email = contactSupport,
    )

    LazyColumn(
        modifier = modifier.padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      item {
        Text(
            text = "Frequently asked questions",
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White.copy(0.8f),
            fontSize = 18.sp,
        )
      }
      items(
          items = faqs,
          key = { it.uuid },
      ) {
        ExpandableCard(
            question = it.question,
            answer = it.answer,
        )
      }
    }
  }
}

@Composable
internal fun HelpTopBar(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
  val strings = LocalStrings.current

  Row(
      modifier = modifier.fillMaxWidth().padding(top = 16.dp),
      verticalAlignment = Alignment.CenterVertically,
  ) {
    BackButton(
        modifier,
        navigateToBack = { navigateToBack.invoke() },
    )
    Headline(
        text = strings.help,
        modifier = modifier,
        fontFamily = GoogleSansFontFamily,
        fontWeight = FontWeight.Medium,
        textColor = Color.White,
    )
  }
}

