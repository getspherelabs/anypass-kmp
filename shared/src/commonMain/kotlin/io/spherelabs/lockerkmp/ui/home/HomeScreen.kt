package io.spherelabs.lockerkmp.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import io.spherelabs.designsystem.button.LKNewItemButton
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.home.homepresentation.*
import io.spherelabs.lockerkmp.MR
import io.spherelabs.lockerkmp.components.*
import kotlinx.coroutines.flow.Flow
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
                Headline()

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
        CustomIndicator(tabPositions, pagerState)
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
                Tag(index, category = category.title, pagerState = pagerState)

            }
        }
        ItemPager(modifier = modifier, category = categories, pagerState = pagerState)
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
fun Headline(
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
fun CreateBoxes(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val box1 = measurables[0]
        val box2 = measurables[1]
        val box3 = measurables[2]


        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )

        val box1Placeable = box1.measure(looseConstraints)
        val box2Placeable = box2.measure(looseConstraints)
        val box3Placeable = box3.measure(looseConstraints)


        layout(
            width = constraints.maxWidth, height = constraints.maxHeight
        ) {
            box1Placeable.placeRelative(constraints.maxWidth / 4, box2Placeable.height / 4)
            box2Placeable.placeRelative(
                constraints.maxWidth / 4 + box1Placeable.width - 12, 0
            )
            box3Placeable.placeRelative(
                (constraints.maxWidth / 4) + (box2Placeable.width + box1Placeable.width) - 22,
                box2Placeable.height / 4
            )

        }
    }
}
