package io.spherelabs.designsystem.picker

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import io.spherelabs.designsystem.hooks.useIntState
import io.spherelabs.designsystem.hooks.useState

@Composable
internal fun LKSocialIconGridLayout(
    modifier: Modifier = Modifier,
    socialIcons: List<SocialMedia>,
    selectedSocialIcon: MutableState<SocialMedia>,
    initialSelection: Int,
) {
    val (mainIndex, setMainIndex) = useIntState(initialSelection)

    val itemSize = with(LocalDensity.current) { itemSizeDp.toPx().toInt() }

    LKGridView(modifier, itemSize = itemSize) {
        socialIcons.forEachIndexed { index, item ->
            LKIconView(icon = item.painter, selected = index == mainIndex) {
                if (mainIndex != index) {
                    setMainIndex.invoke(index)
                    selectedSocialIcon.value = item
                }
            }
        }
    }
}

private val itemSizeDp = 55.dp
