package io.spherelabs.homeimpl.ui.component

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import io.spherelabs.homeimpl.ui.HomeDefaults


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
    val transition = updateTransition(pagerState.currentPage, label = "")

    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(
                    dampingRatio = HomeDefaults.minDampingRatio,
                    stiffness = HomeDefaults.minStiffness,
                )
            } else {
                spring(
                    dampingRatio = HomeDefaults.minDampingRatio,
                    stiffness = HomeDefaults.maxStiffness,
                )
            }
        },
        label = "${pagerState.currentPage}",
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(
                    dampingRatio = HomeDefaults.minDampingRatio,
                    stiffness = HomeDefaults.maxStiffness,
                )
            } else {
                spring(
                    dampingRatio = HomeDefaults.minDampingRatio,
                    stiffness = HomeDefaults.minStiffness,
                )
            }
        },
        label = "",
    ) {
        tabPositions[it].right
    }

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .requiredHeight(30.dp)
            .padding(2.dp)
            .fillMaxSize()
            .background(color = Color.White, RoundedCornerShape(HomeDefaults.cornerShape))
            .zIndex(HomeDefaults.zIndex),

        )
}
