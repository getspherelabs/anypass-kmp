package io.spherelabs.lockerkmp.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import io.spherelabs.designsystem.swiper.useLKCardStackState
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.designsystem.hooks.usePagerEffect
import io.spherelabs.designsystem.passwordcard.LKPasswordCard
import io.spherelabs.designsystem.passwordcard.LKPasswordCardDefaults
import io.spherelabs.designsystem.swiper.LKCardStack
import io.spherelabs.designsystem.swiper.items
import io.spherelabs.home.homepresentation.HomeCategoryUi
import io.spherelabs.lockerkmp.MR
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

data class TextData(
    val id: Int,
    val color: Color,
    val text: String
)

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.pagerFadeTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
        translationX = pageOffset * size.width
        alpha = 1 - pageOffset.absoluteValue
    }

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ItemPager(
    modifier: Modifier = Modifier.animateContentSize(),
    pagerState: PagerState,
    category: List<HomeCategoryUi>,
) {
    var currentItem by remember {
        mutableStateOf(1)
    }


    val cardStackState = useLKCardStackState()
    val previousTotalItemCount = rememberSaveable(cardStackState) { mutableIntStateOf(0) }

    val list = remember {
        mutableStateOf(
            listOf(
                TextData(
                    id = 1,
                    color = Color.Yellow,
                    "1"
                ),
                TextData(
                    id = 2,
                    color = Color.Red,
                    text = "2"
                ),
                TextData(
                    id = 3,
                    color = Color.Red,
                    text = "3"
                )
            )
        )
    }






    usePagerEffect(pagerState) {
        currentItem = category[it].id.toInt()
    }


    LaunchedEffect(cardStackState) {
        snapshotFlow { cardStackState.visibleItemIndex }
            .collect { firstIndex ->
                if (cardStackState.itemsCount < 3) return@collect
                val countHasChanged = previousTotalItemCount.intValue != cardStackState.itemsCount
                if (countHasChanged && firstIndex + 3 > cardStackState.itemsCount) {
                    previousTotalItemCount.intValue = cardStackState.itemsCount
                    val lastValue = list.value.last()
                    val newList = buildList {
                        repeat(20) { index -> add(lastValue.copy(id = index)) }
                    }
                    list.value += newList
                }


            }
    }


    HorizontalPager(
        modifier = modifier.fillMaxSize().background(color = colorResource(MR.colors.lavender)),
        verticalAlignment = Alignment.Top,
        state = pagerState,
        userScrollEnabled = false
    ) {


//        Column() {
//            if (it == 0) {
//                Text(
//                    modifier = modifier.padding(start = 24.dp),
//                    text = "Password Health",
//                    fontSize = 24.sp,
//                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
//                    color = Color.White
//                )
//
//                Row(
//                    modifier = modifier.fillMaxWidth().height(250.dp)
//                        .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
//                        .clip(RoundedCornerShape(16.dp))
//                        .background(color = colorResource(MR.colors.dynamic_yellow)),
//                    horizontalArrangement = Arrangement.SpaceAround
//                ) {
//                    Column(modifier = modifier.wrapContentSize()) {
//                        Box(
//                            modifier = modifier.padding(start = 12.dp, top = 12.dp)
//                                .size(32.dp)
//                                .clip(CircleShape).background(color = Color.Black),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Icon(
//                                modifier = modifier.size(16.dp),
//                                imageVector = Icons.Outlined.Lock,
//                                contentDescription = null,
//                                tint = Color.White
//                            )
//                        }
//                        Text(
//                            modifier = modifier.padding(start = 12.dp, top = 8.dp),
//                            text = "Total password: 49"
//                        )
//                        Image(
//                            modifier = modifier.padding(start = 12.dp, top = 8.dp),
//                            painter = painterResource(MR.images.whale_logo),
//                            contentDescription = null
//                        )
//                    }
//                    Spacer(modifier = modifier.width(25.dp))
//
//                    Row(
//                        modifier = modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Spacer(modifier = modifier.width(25.dp))
//
////                                Boxes(
////                                    modifier = modifier.padding(top = 10.dp)
////                                        .align(Alignment.CenterVertically)
////                                ) {
////                                    Text(
////                                        text = "56",
////                                        fontSize = 16.sp,
////                                        fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
////                                    )
////                                    Box(
////                                        modifier = modifier.clip(CircleShape)
////                                            .background(color = Color.White)
////                                            .size(56.dp),
////                                        contentAlignment = Alignment.Center,
////                                    ) {
////                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
////                                    }
////                                    Box(
////                                        modifier = modifier.clip(CircleShape)
////                                            .background(color = Color.White)
////                                            .size(56.dp),
////                                        contentAlignment = Alignment.Center,
////                                    ) {
////                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
////                                    }
////                                    Text(text = "56", fontSize = 16.sp)
////                                    Text(text = "56", fontSize = 16.sp)
////
////                                    Box(
////                                        modifier = modifier.clip(CircleShape)
////                                            .background(color = Color.White)
////                                            .size(56.dp),
////                                        contentAlignment = Alignment.Center,
////                                    ) {
////                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
////                                    }
////
////                                    Box(
////                                        modifier = modifier.clip(CircleShape)
////                                            .background(color = Color.White)
////                                            .size(56.dp),
////                                        contentAlignment = Alignment.Center,
////                                    ) {
////                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
////                                    }
////                                    Text(text = "56", fontSize = 16.sp)
////
////                                }
//                    }
//
//                }
//
//                Text(
//                    modifier = modifier.padding(start = 24.dp),
//                    text = "Domains",
//                    fontSize = 24.sp,
//                    fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
//                    color = Color.White
//                )
//                DomainCard(modifier)
//
//
//            } else {
        LKCardStack(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            state = cardStackState,
        ) {
            items(items = list.value, key = { it.hashCode() }) { newData ->

                LKPasswordCard(
                    password = "yesdllkdskoeroer123249lk",
                    title = "Behance",
                    icon = painterResource(MR.images.behance),
                    email = "behzoddev@gmail.com",
                    passwordCardColor = LKPasswordCardDefaults.passwordCardColor(
                        backgroundColor = colorResource(MR.colors.lavender_pink),
                        titleColor = colorResource(resource = MR.colors.white),
                        emailColor = colorResource(resource = MR.colors.white).copy(0.5f)
                    ),
                    passwordCardStyle = LKPasswordCardDefaults.passwordCardStyle(
                        titleFontFamily = fontFamilyResource(fontResource = MR.fonts.googlesans.medium),
                        passwordFontFamily = fontFamilyResource(fontResource = MR.fonts.googlesans.medium),
                        emailFontFamily = fontFamilyResource(fontResource = MR.fonts.googlesans.medium),
                        copyFontFamily = fontFamilyResource(fontResource = MR.fonts.googlesans.medium)
                    )

                )
            }
        }


    }
//        } else {

//            }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tag(
    index: Int,
    modifier: Modifier = Modifier,
    category: String,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    val isSelected = pagerState.currentPage == index
    val color: Color by animateColorAsState(
        if (isSelected) colorResource(MR.colors.white) else Color.Black
    )

    Tab(
        modifier = modifier.background(Color.Transparent).height(36.dp).zIndex(6f)
            .clip(RoundedCornerShape(50.dp)),
        text = {
            Text(
                text = category,
                color = color
            )
        },
        selected = isSelected,
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(index, pagerState.currentPageOffsetFraction)
            }
        }
    )

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CustomIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
    val transition = updateTransition(pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
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
            .background(color = Black, RoundedCornerShape(50))
            .zIndex(1f)

    )
}

