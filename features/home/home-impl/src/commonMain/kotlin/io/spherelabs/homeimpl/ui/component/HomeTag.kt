package io.spherelabs.homeimpl.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.foundation.color.Jaguar
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeTag(
    index: Int,
    modifier: Modifier = Modifier,
    category: String,
    pagerState: PagerState,
) {
  val scope = useScope()
  val isSelected = pagerState.currentPage == index

  val color: Color by
      animateColorAsState(
          if (isSelected) Jaguar else Color.White.copy(alpha = 0.5f),
      )

  Tab(
      modifier =
          modifier
              .background(Color.Transparent)
              .height(36.dp)
              .zIndex(6f)
              .clip(RoundedCornerShape(50.dp)),
      text = {
        Text(
            text = category,
            color = color,
        )
      },
      selected = isSelected,
      onClick = {
        scope.launch { pagerState.animateScrollToPage(index, pagerState.currentPageOffsetFraction) }
      },
  )
}
