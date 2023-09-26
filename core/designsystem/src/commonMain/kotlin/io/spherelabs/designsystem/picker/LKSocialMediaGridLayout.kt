package io.spherelabs.designsystem.picker

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
internal fun LKSocialIconGridLayout(
  modifier: Modifier = Modifier,
  socialIcons: List<SocialMedia>,
  selectedSocialIcon: MutableState<SocialMedia>,
  initialSelection: Int
) {
  var mainSelectedIndex by remember { mutableStateOf(initialSelection) }

  val itemSize = with(LocalDensity.current) { itemSizeDp.toPx().toInt() }

  LKGridView(modifier, itemSize = itemSize) {
    socialIcons.forEachIndexed { index, item ->
      LKIconView(icon = item.painter, selected = index == mainSelectedIndex) {
        if (mainSelectedIndex != index) {
          mainSelectedIndex = index
          selectedSocialIcon.value = item
        }
      }
    }
  }
}

private val itemSizeDp = 55.dp
