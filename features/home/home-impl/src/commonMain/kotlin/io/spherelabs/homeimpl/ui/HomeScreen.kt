package io.spherelabs.homeimpl.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.accountnavigation.AccountSharedScreen
import io.spherelabs.addnewpasswordnavigation.AddNewPasswordSharedScreen
import io.spherelabs.designsystem.button.LKNewItemButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.*
import io.spherelabs.designsystem.image.RoundedImage
import io.spherelabs.designsystem.passwordcard.LKPasswordCard
import io.spherelabs.designsystem.passwordcard.LKPasswordCardDefaults
import io.spherelabs.designsystem.picker.SocialMedia
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.designsystem.swiper.LKCardStack
import io.spherelabs.designsystem.swiper.items
import io.spherelabs.designsystem.swiper.useLKCardStackState
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.homeimpl.presentation.*
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.icons.AnyPassIcons
import io.spherelabs.resource.icons.anypassicons.*
import io.spherelabs.resource.images.MR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: HomeViewModel = useInject()
        val addNewPasswordScreen = rememberScreen(AddNewPasswordSharedScreen.AddNewPasswordScreen)
        val accountScreen = rememberScreen(AccountSharedScreen.AccountScreen)
        val uiState = viewModel.state.collectAsStateWithLifecycle()

        HomeContent(
            wish = { newWish ->
                viewModel.wish(newWish)
            },
            uiState = viewModel.state.value,
            effect = viewModel.effect,
            navigateToAuthenticator = {},
            navigateToMyAccount = {
                navigator.push(accountScreen)
            },
            navigateToCreatePassword = {
                navigator.push(addNewPasswordScreen)
            },
            navigateToGenerator = {},
            navigateToHelp = {},
            navigateToPasswordHealth = {},
        )
    }
}


@Composable
fun HomeContent(
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

                else -> {}
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
        backgroundColor = color,
        drawerContent = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = color)
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
                                painter = painterResource(MR.images.avatar),
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
                                tint = Color.White,
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = null,
                            )
                            Spacer(modifier.width(8.dp))
                            androidx.compose.material.Text(
                                modifier = modifier,
                                text = strings.myAccount,
                                fontSize = 20.sp,
                                color = Color.White,
                                fontFamily = GoogleSansFontFamily,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier.height(16.dp))

                        Row(
                            modifier = modifier
                                .padding(start = 24.dp)
                                .fillMaxWidth()
                                .clickable {
                                    wish.invoke(HomeWish.NavigateToPasswordHealth)
                                },
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Icon(
                                tint = Color.White,
                                imageVector = Icons.Default.HealthAndSafety,
                                contentDescription = null,
                            )
                            Spacer(modifier.width(8.dp))
                            androidx.compose.material.Text(
                                modifier = modifier,
                                text = strings.passwordHealth,
                                fontSize = 20.sp,
                                color = Color.White,
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
                                tint = Color.White,
                                contentDescription = null,
                            )
                            Spacer(modifier.width(8.dp))
                            androidx.compose.material.Text(
                                modifier = modifier,
                                text = strings.authenticator,
                                fontSize = 20.sp,
                                color = Color.White,
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
                                tint = Color.White,
                                contentDescription = null,
                            )
                            Spacer(modifier.width(8.dp))
                            androidx.compose.material.Text(
                                modifier = modifier,
                                text = strings.generator,
                                fontSize = 20.sp,
                                color = Color.White,
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
                                tint = Color.White,
                                imageVector = Icons.Default.HelpCenter,
                                contentDescription = null,
                            )
                            Spacer(modifier.width(8.dp))
                            androidx.compose.material.Text(
                                modifier = modifier,
                                text = strings.help,
                                fontSize = 20.sp,
                                color = Color.White,
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
                    painter = painterResource(MR.images.avatar),
                    contentDescription = null,
                )

                LKNewItemButton(
                    contentText = strings.newItem,
                    backgroundColor = LavenderBlue.copy(0.7f),
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
                    color,
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
                androidx.compose.material.Text(
                    text = strings.keep,
                    color = Color.White,
                    fontSize = fontSize,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = modifier.width(12.dp))
                RoundedImage(
                    imageSize = 56,
                    painter = painterResource(MR.images.avatar3), contentDescription = null,
                )

            }
        }
        item {
            Row {
                RoundedImage(
                    imageSize = 56,
                    painter = painterResource(MR.images.avatar6), contentDescription = null,
                )
                Spacer(modifier = modifier.width(12.dp))
                androidx.compose.material.Text(
                    text = strings.yourLife,
                    color = Color.White,
                    fontSize = fontSize,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Bold,
                )

            }
        }

        item {
            Row {
                androidx.compose.material.Text(
                    text = strings.safe,
                    color = Color.White,
                    fontSize = fontSize,
                    fontFamily = GoogleSansFontFamily,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = modifier.width(12.dp))
                RoundedImage(
                    imageSize = 56,
                    painter = painterResource(MR.images.avatar4),
                    contentDescription = null,
                )

            }
        }

    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
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
                            backgroundColor = color2,
                            titleColor = Color.White,
                            emailColor = Color.White.copy(0.5f),
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
        if (isSelected) color2 else Color.White.copy(alpha = 0.5f),
    )

    Tab(
        modifier = modifier.background(Color.Transparent).height(36.dp).zIndex(6f)
            .clip(RoundedCornerShape(50.dp)),
        text = {
            androidx.compose.material.Text(
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

object SocialIcons {
    @Composable
    fun getSocialMedia(): State<List<SocialMedia>> {
        return useUpdatedState(
            listOf(
                SocialMedia(
                    "Behance",
                    AnyPassIcons.Behance,
                ),
                SocialMedia(
                    "Linkedin",
                    AnyPassIcons.Linkedin,
                ),
                SocialMedia(
                    title = "Dribble",
                    image = AnyPassIcons.Dribble,
                ),
                SocialMedia(
                    title = "ApplePodcasts",
                    image = AnyPassIcons.ApplePodcasts,
                ),
                SocialMedia(
                    title = "Discord",
                    image = AnyPassIcons.Discord,
                ),
                SocialMedia(
                    title = "Facebook",
                    image = AnyPassIcons.Facebook,
                ),
                SocialMedia(
                    title = "GoogleMeet",
                    image = AnyPassIcons.Googlemeet,
                ),
                SocialMedia(
                    title = "Medium",
                    image = AnyPassIcons.Medium,
                ),
                SocialMedia(
                    title = "Messenger",
                    image = AnyPassIcons.Messenger,
                ),
                SocialMedia(
                    title = "Pinterest",
                    image = AnyPassIcons.Pinterest,
                ),
                SocialMedia(
                    title = "Quora",
                    image = AnyPassIcons.Quora,
                ),
                SocialMedia(
                    title = "Reddit",
                    image = AnyPassIcons.Reddit,
                ),
                SocialMedia(
                    title = "Skype",
                    image = AnyPassIcons.Skype,
                ),
                SocialMedia(
                    title = "Telegram",
                    image = AnyPassIcons.Telegram,
                ),
            ),
        )
    }

    @Composable
    fun get(title: String): State<SocialMedia?> {
        val items = getSocialMedia().value
        return useUpdatedState(
            items.find { it.title == title },
        )
    }

}


enum class HomeDrawerState {
    OPENED,
    CLOSED
}

val color: Color = Color(0xff141419)
val color2: Color = Color(0xff292933)
