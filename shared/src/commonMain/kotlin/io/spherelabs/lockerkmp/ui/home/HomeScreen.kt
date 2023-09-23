package io.spherelabs.lockerkmp.ui.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import io.spherelabs.designsystem.button.LKNewItemButton
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.usePagerEffect
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useState
import io.spherelabs.designsystem.image.RoundedImage
import io.spherelabs.designsystem.passwordcard.LKPasswordCard
import io.spherelabs.designsystem.passwordcard.LKPasswordCardDefaults
import io.spherelabs.designsystem.swiper.LKCardStack
import io.spherelabs.designsystem.swiper.items
import io.spherelabs.designsystem.swiper.useLKCardStackState
import io.spherelabs.home.homepresentation.*
import io.spherelabs.lockerkmp.MR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.compose.rememberKoinInject
import dev.icerock.moko.resources.compose.painterResource as mokoPainterResource

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = rememberKoinInject(),
    navigateToCreatePassword: () -> Unit
) {
    val uiState = viewModel.state.collectAsState()

    HomeScreen(
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        uiState = uiState,
        effect = viewModel.effect,
        navigateToCreatePassword = {
            navigateToCreatePassword.invoke()
        }
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    wish: (HomeWish) -> Unit,
    uiState: State<HomeState>,
    effect: Flow<HomeEffect>,
    navigateToCreatePassword: () -> Unit
) {
    val state = rememberScrollState(0)
    val scaffoldState = rememberScaffoldState()

    useEffect(true) {
        wish.invoke(HomeWish.GetStartedCategories)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = colorResource(MR.colors.lavender),
        topBar = {
            Row(
                modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RoundedImage(
                    painter = mokoPainterResource(MR.images.avatar),
                    contentDescription = null
                )

                LKNewItemButton(
                    contentText = stringResource(MR.strings.new_item),
                    borderColor = colorResource(MR.colors.cinderella),
                    contentFontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
                ) {
                    navigateToCreatePassword.invoke()

                }

            }
        },
        content = {
            Column(
                modifier = modifier.fillMaxSize().background(
                    color =
                    colorResource(MR.colors.lavender)
                )
            ) {
                HomeHeadline()

                if (uiState.value.categories.isNotEmpty()) {
                    CategoryCard(uiState.value.categories)
                }
            }


        })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryCard(
    categories: List<HomeCategoryUi>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { categories.size }

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        LKIndicator(tabPositions, pagerState)
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScrollableTabRow(
            modifier = Modifier
                .padding(start = 24.dp, top = 12.dp)
                .height(30.dp),
            selectedTabIndex = pagerState.currentPage,
            indicator = indicator,
            backgroundColor = Color.Transparent,
            edgePadding = 0.dp,
            divider = {
                Divider(color = Color.Transparent)
            }
        ) {
            categories.forEachIndexed { index, category ->
                LKTag(index, category = category.title, pagerState = pagerState)
            }
        }
        LKPager(modifier = modifier, category = categories, pagerState = pagerState)
    }

}

@Composable
fun DomainCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            RoundedImage(
                modifier = modifier.padding(start = 24.dp),
                painter = painterResource(MR.images.whale_logo),
                contentDescription = null
            )
            Spacer(modifier = modifier.width(12.dp))
            Column {
                Text("Site", color = Color.Black)
                Text("Site2", color = Color.Black)
            }
            Spacer(modifier = modifier.weight(1f))
            RoundedImage(
                modifier = modifier.padding(end = 24.dp),
                painter = painterResource(MR.images.whale_logo),
                contentDescription = null
            )
        }
    }
}

@Composable
fun HomeHeadline(
    fontSize: TextUnit = 32.sp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(start = 24.dp)
    ) {
        Row {
            Text(
                text = "Keep",
                color = colorResource(MR.colors.white),
                fontSize = fontSize,
                fontFamily = fontFamilyResource(MR.fonts.hiraginosans.medium)
            )
            Spacer(modifier = modifier.width(12.dp))
            RoundedImage(
                painter = mokoPainterResource(MR.images.whale_logo), contentDescription = null
            )

        }

        Row {
            RoundedImage(
                painter = mokoPainterResource(MR.images.whale_logo), contentDescription = null
            )
            Spacer(modifier = modifier.width(12.dp))
            Text(
                text = "Your Life",
                color = colorResource(MR.colors.white),
                fontSize = fontSize,
                fontFamily = fontFamilyResource(MR.fonts.hiraginosans.medium)
            )

        }
        Row {
            Text(
                text = "Safe",
                color = colorResource(MR.colors.white),
                fontSize = fontSize,
                fontFamily = fontFamilyResource(MR.fonts.hiraginosans.medium)
            )
            Spacer(modifier = modifier.width(12.dp))
            RoundedImage(
                painter = mokoPainterResource(MR.images.whale_logo), contentDescription = null
            )

        }
    }
}


@Composable
fun LKPager(
    modifier: Modifier = Modifier.animateContentSize(),
    pagerState: PagerState,
    category: List<HomeCategoryUi>,
) {

    val (currentItem, setCurrentItem) = useState(1)

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
        setCurrentItem(category[it].id.toInt())
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

}





@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LKTag(
    index: Int,
    modifier: Modifier = Modifier,
    category: String,
    pagerState: PagerState
) {
    val scope = useScope()
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
internal fun LKIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
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
            .background(color = Color.Black, RoundedCornerShape(50))
            .zIndex(1f)

    )
}
data class TextData(
    val id: Int,
    val color: Color,
    val text: String
)
