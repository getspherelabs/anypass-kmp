package io.spherelabs.anypass.ui.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import io.spherelabs.anypass.MR
import io.spherelabs.designsystem.hooks.useSnackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.compose.rememberKoinInject
import dev.icerock.moko.resources.compose.painterResource as mokoPainterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import io.spherelabs.anypass.ui.addnewpassword.SocialIcons
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.icons.AnyPassIcons
import io.spherelabs.resource.icons.anypassicons.Behance

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = rememberKoinInject(),
    navigateToCreatePassword: () -> Unit,
    navigateToMyAccount: () -> Unit,
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        uiState = uiState.value,
        effect = viewModel.effect,
        navigateToCreatePassword = {
            navigateToCreatePassword.invoke()
        },
        navigateToMyAccount = {
            navigateToMyAccount.invoke()
        },
        navigateToGenerator = {

        },
        navigateToAuthenticator = {},
        navigateToHelp = {},
        navigateToPasswordHealth = {},
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    wish: (HomeWish) -> Unit,
    uiState: HomeState,
    effect: Flow<HomeEffect>,
    navigateToCreatePassword: () -> Unit,
    navigateToMyAccount: () -> Unit,
    navigateToGenerator: () -> Unit,
    navigateToPasswordHealth: () -> Unit,
    navigateToAuthenticator: () -> Unit,
    navigateToHelp: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = useScope()
    val snackbarState = useSnackbar()
    val strings = LocalStrings.current

    useEffect(true) {
        wish.invoke(HomeWish.GetStartedCategories)

        effect.collectLatest { newEffect ->
            when (newEffect) {
                is HomeEffect.Failure -> {
                    coroutineScope.launch {
                        snackbarState.showSnackbar(
                            message = newEffect.message,
                        )
                    }
                }

                is HomeEffect.CopyClipboard -> {
                    coroutineScope.launch {
                        snackbarState.showSnackbar(
                            message = newEffect.message,
                        )
                    }
                }

                HomeEffect.NavigateToAddNewPassword -> {
                    navigateToCreatePassword.invoke()
                }

                HomeEffect.NavigateToGenerator -> {
                    navigateToGenerator.invoke()
                }

                HomeEffect.NavigateToMyAccount -> {
                    coroutineScope.launch {
                        navigateToMyAccount.invoke()
                    }
                }

                HomeEffect.NavigateToAuthenticator -> {
                    navigateToAuthenticator.invoke()
                }

                HomeEffect.NavigateToHelp -> {
                    navigateToHelp.invoke()
                }

                HomeEffect.NavigateToPasswordHealth -> {
                    navigateToPasswordHealth.invoke()
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom),
            )
        },
        backgroundColor = colorResource(MR.colors.lavender),
        drawerContent = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colorResource(MR.colors.lavender).copy(alpha = 0.6f))
                    .padding(start = 8.dp, top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LazyColumn(modifier = modifier.weight(1f)) {
                    item {
                        Row(
                            modifier = modifier.padding(start = 24.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            RoundedImage(
                                imageSize = 75,
                                painter = mokoPainterResource(MR.images.avatar),
                                contentDescription = null,
                            )
                        }

                        Spacer(modifier.height(16.dp))

                        Spacer(
                            modifier.padding(horizontal = 8.dp).height(1.dp).fillMaxWidth()
                                .background(
                                    Color.Black.copy(
                                        0.5f,
                                    ),
                                ),
                        )

                        Spacer(modifier.height(16.dp))
                        Row(
                            modifier = modifier.padding(start = 24.dp).fillMaxWidth().clickable {
                                wish.invoke(HomeWish.NavigateToMyAccount)
                            },
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = null,
                            )
                            Spacer(modifier.width(8.dp))
                            Text(
                                modifier = modifier,
                                text = strings.myAccount,
                                fontSize = 20.sp,
                                fontFamily = GoogleSansFontFamily,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier.height(16.dp))

                        Row(
                            modifier = modifier.padding(start = 24.dp).fillMaxWidth().clickable {
                                wish.invoke(HomeWish.NavigateToPasswordHealth)
                            },
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Icon(
                                imageVector = Icons.Default.HealthAndSafety,
                                contentDescription = null,
                            )
                            Spacer(modifier.width(8.dp))
                            Text(
                                modifier = modifier,
                                text = strings.passwordHealth,
                                fontSize = 20.sp,
                                fontFamily = GoogleSansFontFamily,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier.height(16.dp))
                        Row(
                            modifier = modifier.padding(start = 24.dp).fillMaxWidth().clickable {
                                wish.invoke(HomeWish.NavigateToAuthenticator)
                            },
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Icon(
                                imageVector = Icons.Default.CastConnected,
                                contentDescription = null,
                            )
                            Spacer(modifier.width(8.dp))
                            Text(
                                modifier = modifier,
                                text = strings.authenticator,
                                fontSize = 20.sp,
                                fontFamily = GoogleSansFontFamily,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier.height(16.dp))

                        Row(
                            modifier = modifier.padding(start = 24.dp).fillMaxWidth().clickable {
                                wish.invoke(HomeWish.NavigateToGenerator)
                            },
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Password,
                                contentDescription = null,
                            )
                            Spacer(modifier.width(8.dp))
                            Text(
                                modifier = modifier,
                                text = strings.generator,
                                fontSize = 20.sp,
                                fontFamily = GoogleSansFontFamily,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                        Spacer(modifier.height(16.dp))

                        Row(
                            modifier = modifier.padding(start = 24.dp).fillMaxWidth().clickable {
                                wish.invoke(HomeWish.NavigateToHelp)
                            },
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Icon(
                                imageVector = Icons.Default.HelpCenter,
                                contentDescription = null,
                            )
                            Spacer(modifier.width(8.dp))
                            Text(
                                modifier = modifier,
                                text = strings.help,
                                fontSize = 20.sp,
                                fontFamily = GoogleSansFontFamily,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                    }


                }


            }
        },
        topBar = {
            Row(
                modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                RoundedImage(
                    modifier = modifier.clickable {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    painter = mokoPainterResource(MR.images.avatar),
                    contentDescription = null,
                )

                LKNewItemButton(
                    contentText = strings.newItem,
                    borderColor = colorResource(MR.colors.cinderella),
                    contentFontFamily = GoogleSansFontFamily,
                ) {
                    wish.invoke(HomeWish.NavigateToAddNewPassword)
                }

            }
        },
        content = {
            Column(
                modifier = modifier.fillMaxSize().background(
                    color =
                    colorResource(MR.colors.lavender),
                ),
            ) {
                HomeHeadline()

                if (uiState.categories.isNotEmpty()) {
                    CategoryCard(
                        uiState.categories, uiState.passwords,
                        onPasswordChanged = { newWish ->
                            wish.invoke(newWish)
                        },
                        onGetStartingPasswordByCategory = { newWish ->
                            wish.invoke(newWish)
                        },
                        onCopyClick = { newPassword ->
                            wish.invoke(HomeWish.OnCopyClipboardChanged(newPassword))
                        },
                    )
                }
            }


        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryCard(
    categories: List<HomeCategoryUi>,
    passwords: List<HomePasswordUi>,
    modifier: Modifier = Modifier,
    onPasswordChanged: (HomeWish) -> Unit,
    onGetStartingPasswordByCategory: (HomeWish) -> Unit,
    onCopyClick: (String) -> Unit,
) {
    val pagerState = rememberPagerState { categories.size }

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        LKIndicator(tabPositions, pagerState)
    }


    Column(
        modifier = modifier.fillMaxSize(),
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
            },
        ) {
            categories.forEachIndexed { index, category ->
                LKTag(index, category = category.title, pagerState = pagerState)
            }
        }
        LKPager(
            modifier = modifier,
            category = categories,
            pagerState = pagerState,
            passwords = passwords,
            onPasswordChanged = { newWish ->
                onPasswordChanged.invoke(newWish)
            },
            onGetStartingPasswordByCategory = { newWish ->
                onGetStartingPasswordByCategory.invoke(newWish)
            },
            onCopyClick = { newPassword ->
                onCopyClick.invoke(newPassword)
            },
        )
    }

}

@Composable
fun HomeHeadline(
    fontSize: TextUnit = 42.sp,
    modifier: Modifier = Modifier,
) {

    val strings = LocalStrings.current

    LazyColumn(
        modifier = modifier.padding(start = 24.dp),
    ) {
        item {
            Row {
                Text(
                    text = strings.keep,
                    color = colorResource(MR.colors.white),
                    fontSize = fontSize,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = modifier.width(12.dp))
                RoundedImage(
                    imageSize = 56,
                    painter = mokoPainterResource(MR.images.avatar3), contentDescription = null,
                )

            }
        }
        item {
            Row {
                RoundedImage(
                    imageSize = 56,
                    painter = mokoPainterResource(MR.images.avatar6), contentDescription = null,
                )
                Spacer(modifier = modifier.width(12.dp))
                Text(
                    text = strings.yourLife,
                    color = colorResource(MR.colors.white),
                    fontSize = fontSize,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Bold,
                )

            }
        }

        item {
            Row {
                Text(
                    text = strings.safe,
                    color = colorResource(MR.colors.white),
                    fontSize = fontSize,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = modifier.width(12.dp))
                RoundedImage(
                    imageSize = 56,
                    painter = mokoPainterResource(MR.images.avatar4),
                    contentDescription = null,
                )

            }
        }

    }
}


@Composable
fun LKPager(
    modifier: Modifier = Modifier.animateContentSize(),
    pagerState: PagerState,
    category: List<HomeCategoryUi>,
    passwords: List<HomePasswordUi>,
    onPasswordChanged: (HomeWish) -> Unit,
    onGetStartingPasswordByCategory: (HomeWish) -> Unit,
    onCopyClick: (String) -> Unit,
) {

    val (currentItem, setCurrentItem) = useState("1")
    val scope = useScope()
    val cardStackState = useLKCardStackState()
    val previousTotalItemCount = rememberSaveable(cardStackState) { mutableIntStateOf(0) }
    val clipboardManager = LocalClipboardManager.current

    usePagerEffect(pagerState) {
        scope.launch {
            onGetStartingPasswordByCategory.invoke(HomeWish.GetStartedPasswordByCategory(category[it].id))
        }

    }

    LaunchedEffect(cardStackState) {
        snapshotFlow { cardStackState.visibleItemIndex }
            .collect { firstIndex ->
                if (cardStackState.itemsCount < 1) return@collect
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
        modifier = modifier.fillMaxSize().background(color = colorResource(MR.colors.lavender)),
        verticalAlignment = Alignment.Top,
        state = pagerState,
        userScrollEnabled = false,
    ) {

        if (passwords.isNotEmpty()) {
            LKCardStack(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                state = cardStackState,
            ) {
                items(items = passwords, key = { it.id.hashCode() }) { newData ->
                    val img = SocialIcons.get(newData.image).value?.image ?: AnyPassIcons.Behance

                    LKPasswordCard(
                        password = newData.password,
                        title = newData.title,
                        icon = img,
                        email = newData.email,
                        passwordCardColor = LKPasswordCardDefaults.passwordCardColor(
                            backgroundColor = colorResource(MR.colors.lavender_pink),
                            titleColor = colorResource(resource = MR.colors.white),
                            emailColor = colorResource(resource = MR.colors.white).copy(0.5f),
                        ),
                        passwordCardStyle = LKPasswordCardDefaults.passwordCardStyle(
                            titleFontFamily = GoogleSansFontFamily,
                            passwordFontFamily = GoogleSansFontFamily,
                            emailFontFamily = GoogleSansFontFamily,
                            copyFontFamily = GoogleSansFontFamily,
                        ),
                        onCopyClick = {
                            onCopyClick.invoke(newData.password)
                            clipboardManager.setText(AnnotatedString(newData.password))
                        },
                    )
                }
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
    pagerState: PagerState,
) {
    val scope = useScope()
    val isSelected = pagerState.currentPage == index

    val color: Color by animateColorAsState(
        if (isSelected) colorResource(MR.colors.white) else Color.Black,
    )

    Tab(
        modifier = modifier.background(Color.Transparent).height(36.dp).zIndex(6f)
            .clip(RoundedCornerShape(50.dp)),
        text = {
            Text(
                text = category,
                color = color,
            )
        },
        selected = isSelected,
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(index, pagerState.currentPageOffsetFraction)
            }
        },
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
        },
        label = "${pagerState.currentPage}",
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
            .background(color = Color.Black, RoundedCornerShape(50))
            .zIndex(1f),

        )
}

enum class HomeDrawerState {
    OPENED,
    CLOSED
}
