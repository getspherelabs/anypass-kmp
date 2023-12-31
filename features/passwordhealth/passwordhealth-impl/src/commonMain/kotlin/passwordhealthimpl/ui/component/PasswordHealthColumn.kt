@file:OptIn(ExperimentalFoundationApi::class)

package passwordhealthimpl.ui.component

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.PasswordHealth
import domain.model.PasswordType
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useUpdatedState
import io.spherelabs.designsystem.picker.SocialMedia
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.icons.AnyPassIcons
import io.spherelabs.resource.icons.anypassicons.ApplePodcasts
import io.spherelabs.resource.icons.anypassicons.Behance
import io.spherelabs.resource.icons.anypassicons.Discord
import io.spherelabs.resource.icons.anypassicons.Dribble
import io.spherelabs.resource.icons.anypassicons.Facebook
import io.spherelabs.resource.icons.anypassicons.Googlemeet
import io.spherelabs.resource.icons.anypassicons.Linkedin
import io.spherelabs.resource.icons.anypassicons.Medium
import io.spherelabs.resource.icons.anypassicons.Messenger
import io.spherelabs.resource.icons.anypassicons.Pinterest
import io.spherelabs.resource.icons.anypassicons.Quora
import io.spherelabs.resource.icons.anypassicons.Reddit
import io.spherelabs.resource.icons.anypassicons.Skype
import io.spherelabs.resource.icons.anypassicons.Telegram
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordHealthColumn(
    modifier: Modifier = Modifier,
    passwords: List<PasswordHealth>,
) {

  val listState = rememberLazyListState()
  val scope = useScope()

  LazyColumn(
      modifier = modifier.fillMaxWidth().padding(top = 8.dp),
      state = listState,
  ) {
    val grouped = passwords.groupBy { it.type }

    grouped.forEach { (type, passwords) ->
      stickyHeader {
        val header =
            when (type) {
              PasswordType.Reused -> {
                "Reused"
              }
              PasswordType.Strong -> "Strong"
              PasswordType.Weak -> "Weak"
            }
        androidx.compose.material.Text(
            modifier = modifier.padding(start = 24.dp, top = 8.dp),
            text = header,
            fontSize = 18.sp,
            fontFamily = GoogleSansFontFamily,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
      }
      items(
          passwords,
          key = { it.id },
      ) { password ->
        val image = SocialIcons.get(password.image).value?.image ?: AnyPassIcons.Behance

        PasswordHealthRow(
            title = password.name,
            email = password.email,
            image = image,
        )
      }
      scope.launch {
        listState.scrollToItem(
            passwords.size - 1,
        )
      }
    }
  }
}

@Composable
fun PasswordHealthRow(
    modifier: Modifier = Modifier,
    title: String,
    email: String,
    image: ImageVector,
) {

  Card(
      colors =
          CardDefaults.cardColors(
              containerColor = LavenderBlue.copy(alpha = 0.7f),
          ),
      modifier =
          modifier.height(95.dp).fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 8.dp),
      shape = RoundedCornerShape(16.dp),
  ) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
      Image(
          modifier = modifier.size(56.dp).clip(RoundedCornerShape(16.dp)).padding(start = 16.dp),
          imageVector = image,
          contentDescription = "Password Health Image",
      )

      Column(modifier = modifier) {
        Text(
            modifier = modifier.padding(start = 24.dp),
            text = title,
            fontSize = 14.sp,
            fontFamily = GoogleSansFontFamily,
            color = Color.White.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
        )

        Text(
            modifier = modifier.padding(top = 4.dp, start = 24.dp),
            text = email,
            fontSize = 18.sp,
            fontFamily = GoogleSansFontFamily,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.White.copy(alpha = 0.9f),
        )
      }
    }
  }
}

object SocialIcons {
  @Composable
  fun getSocialMedia(): State<List<SocialMedia>> {
    return useUpdatedState(
        listOf(
            SocialMedia(
                "Behance",
                AnyPassIcons.Behance,
            ),
            SocialMedia(
                "Linkedin",
                AnyPassIcons.Linkedin,
            ),
            SocialMedia(
                title = "Dribble",
                image = AnyPassIcons.Dribble,
            ),
            SocialMedia(
                title = "ApplePodcasts",
                image = AnyPassIcons.ApplePodcasts,
            ),
            SocialMedia(
                title = "Discord",
                image = AnyPassIcons.Discord,
            ),
            SocialMedia(
                title = "Facebook",
                image = AnyPassIcons.Facebook,
            ),
            SocialMedia(
                title = "GoogleMeet",
                image = AnyPassIcons.Googlemeet,
            ),
            SocialMedia(
                title = "Medium",
                image = AnyPassIcons.Medium,
            ),
            SocialMedia(
                title = "Messenger",
                image = AnyPassIcons.Messenger,
            ),
            SocialMedia(
                title = "Pinterest",
                image = AnyPassIcons.Pinterest,
            ),
            SocialMedia(
                title = "Quora",
                image = AnyPassIcons.Quora,
            ),
            SocialMedia(
                title = "Reddit",
                image = AnyPassIcons.Reddit,
            ),
            SocialMedia(
                title = "Skype",
                image = AnyPassIcons.Skype,
            ),
            SocialMedia(
                title = "Telegram",
                image = AnyPassIcons.Telegram,
            ),
        ),
    )
  }

  @Composable
  fun get(title: String): State<SocialMedia?> {
    val items = getSocialMedia().value
    return useUpdatedState(
        items.find { it.title == title },
    )
  }
}

// Value obtained empirically so that the header buttons don't surpass the header container
private val HeaderTransitionOffset = 280.dp

/** Class that contains derived state for when the toolbar should be shown */
data class DetailsScroller(
    val scrollState: ScrollState,
    val namePosition: Float,
) {
  val toolbarTransitionState = MutableTransitionState(ToolbarState.HIDDEN)

  fun getToolbarState(density: Density): ToolbarState {
    // When the namePosition is placed correctly on the screen (position > 1f) and it's
    // position is close to the header, then show the toolbar.
    return if (namePosition > 1f &&
        scrollState.value > (namePosition - getTransitionOffset(density))) {
      toolbarTransitionState.targetState = ToolbarState.SHOWN
      ToolbarState.SHOWN
    } else {
      toolbarTransitionState.targetState = ToolbarState.HIDDEN
      ToolbarState.HIDDEN
    }
  }

  private fun getTransitionOffset(density: Density): Float =
      with(density) { HeaderTransitionOffset.toPx() }
}

// Toolbar state related classes and functions to achieve the CollapsingToolbarLayout animation
enum class ToolbarState {
  HIDDEN,
  SHOWN
}

val ToolbarState.isShown
  get() = this == ToolbarState.SHOWN
