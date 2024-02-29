package io.spherelabs.homeimpl.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.spherelabs.homeimpl.presentation.UIHomePassword
import io.spherelabs.homeimpl.presentation.HomeWish

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CategoryCard(
    categories: List<Category>,
    passwords: List<UIHomePassword>,
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    isHidden: Boolean,
    onPasswordChanged: (HomeWish) -> Unit,
    onGetStartingPasswordByCategory: (HomeWish) -> Unit,
    onCopyClick: (String) -> Unit,
    onToggleVisibilityClick: () -> Unit,
) {
    val pagerState = rememberPagerState { categories.size }

    val indicator =
        @Composable { tabPositions: List<TabPosition> -> HomeIndicator(tabPositions, pagerState) }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        AnimatedVisibility(
            visible = isVisible,
            modifier = modifier.align(Alignment.CenterHorizontally),
            enter = slideInHorizontally(),
            exit = slideOutHorizontally(),
        ) {
            ScrollableTabRow(
                modifier = Modifier.padding(start = 24.dp, top = 12.dp).height(30.dp),
                selectedTabIndex = pagerState.currentPage,
                indicator = indicator,
                backgroundColor = Color.Transparent,
                edgePadding = 0.dp,
                divider = { Divider(color = Color.Transparent) },
            ) {
                categories.forEachIndexed { index, category ->
                    HomeTag(index, categoryType = category.categoryType, pagerState = pagerState)
                }
            }
        }
        if (categories.isNotEmpty()) {
            HomePager(
                modifier = modifier,
                category = categories,
                pagerState = pagerState,
                passwords = passwords,
                onPasswordChanged = { newWish -> onPasswordChanged.invoke(newWish) },
                onGetStartingPasswordByCategory = { newWish ->
                    onGetStartingPasswordByCategory.invoke(newWish)
                },
                isHidden = isHidden,
                onToggleVisibility =
                { onToggleVisibilityClick.invoke() },
                onCopyClick = { newPassword -> onCopyClick.invoke(newPassword) },
            )
        }
    }
}
