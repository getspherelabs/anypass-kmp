package io.spherelabs.homeimpl.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.designsystem.hooks.usePagerEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.passwordcard.LKPasswordCard
import io.spherelabs.designsystem.passwordcard.LKPasswordCardDefaults
import io.spherelabs.designsystem.swiper.LKCardStack
import io.spherelabs.designsystem.swiper.items
import io.spherelabs.designsystem.swiper.useLKCardStackState
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.homeimpl.presentation.UIHomeCategory
import io.spherelabs.homeimpl.presentation.UIHomePassword
import io.spherelabs.homeimpl.presentation.HomeWish
import io.spherelabs.homeimpl.ui.HomeDefaults
import io.spherelabs.homeimpl.ui.SocialIcons
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.icons.AnyPassIcons
import io.spherelabs.resource.icons.anypassicons.Behance
import io.spherelabs.system.ui.randompassword.ColorizedRandomPassword
import kotlin.math.min
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
internal fun HomePager(
    modifier: Modifier = Modifier.animateContentSize(),
    pagerState: PagerState,
    category: List<UIHomeCategory>,
    passwords: List<UIHomePassword>,
    isHidden: Boolean,
    onPasswordChanged: (HomeWish) -> Unit,
    onGetStartingPasswordByCategory: (HomeWish) -> Unit,
    onCopyClick: (String) -> Unit,
    onToggleVisibility: () -> Unit,
) {
    val scope = useScope()
    val cardStackState = useLKCardStackState()
    val previousTotalItemCount = rememberSaveable(cardStackState) { mutableIntStateOf(0) }
    val clipboardManager = LocalClipboardManager.current
    val animatedBlur by animateDpAsState(
        targetValue = if (isHidden) 16.dp else 0.dp,
        animationSpec = tween(500, easing = LinearEasing),
    )
    usePagerEffect(pagerState) {
        scope.launch {
            onGetStartingPasswordByCategory.invoke(HomeWish.GetStartedPasswordByCategory(category[it].id))
        }
    }

    LaunchedEffect(cardStackState) {
        snapshotFlow { cardStackState.visibleItemIndex }
            .collect { firstIndex ->
                if (cardStackState.itemsCount < HomeDefaults.minItemCount) return@collect
                val countHasChanged = previousTotalItemCount.intValue != cardStackState.itemsCount
                if (countHasChanged && firstIndex + 1 > cardStackState.itemsCount) {
                    previousTotalItemCount.intValue = cardStackState.itemsCount
                    val lastValue = passwords.last()
                    val newList = buildList {
                        repeat(20) { index -> add(lastValue.copy(id = index.toString())) }
                    }
                    onPasswordChanged.invoke(HomeWish.OnPasswordsChanged(newList))
                }
            }
    }

    HorizontalPager(
        modifier = modifier.fillMaxSize().background(color = Color(0xff141419)),
        verticalAlignment = Alignment.Top,
        state = pagerState,
        userScrollEnabled = false,
    ) {
        if (passwords.isNotEmpty()) {
            LKCardStack(
                modifier = Modifier.padding(24.dp).fillMaxSize(),
                state = cardStackState,
            ) {
                items(items = passwords, key = { it.id.hashCode() }) { newData ->
                    val img = SocialIcons.get(newData.image).value?.image ?: AnyPassIcons.Behance

                    LKPasswordCard(
                        password = {
                            ColorizedRandomPassword(
                                modifier = modifier.blur(
                                    animatedBlur,
                                    edgeTreatment = BlurredEdgeTreatment.Unbounded,
                                ).clickable {
                                    onToggleVisibility.invoke()
                                },
                                password = newData.password,
                                fontSize = 35.sp,
                            )

                        },
                        title = newData.title,
                        icon = img,
                        email = newData.email,
                        passwordCardColor =
                        LKPasswordCardDefaults.passwordCardColor(
                            backgroundColor = Jaguar,
                            titleColor = Color.White,
                            emailColor = Color.White.copy(0.5f),
                        ),
                        passwordCardStyle =
                        LKPasswordCardDefaults.passwordCardStyle(
                            titleFontFamily = GoogleSansFontFamily,
                            passwordFontFamily = GoogleSansFontFamily,
                            emailFontFamily = GoogleSansFontFamily,
                            copyFontFamily = GoogleSansFontFamily,
                        ),
                        onCopyClick = {
                            onCopyClick.invoke(newData.password)
                            clipboardManager.setText(AnnotatedString(newData.password))
                        },
                        onToggleVisibility = {},
                    )
                }
            }
        }
    }
}

