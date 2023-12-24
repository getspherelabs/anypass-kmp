package io.spherelabs.homeimpl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.designsystem.hooks.*
import io.spherelabs.designsystem.picker.SocialMedia
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.homeimpl.presentation.*
import io.spherelabs.homeimpl.ui.component.CategoryCard
import io.spherelabs.homeimpl.ui.component.HomeDrawer
import io.spherelabs.homeimpl.ui.component.HomeHeadline
import io.spherelabs.homeimpl.ui.component.HomeTopBar
import io.spherelabs.navigationapi.AccountDestination
import io.spherelabs.navigationapi.AddNewPasswordDestination
import io.spherelabs.navigationapi.AuthenticatorDestination
import io.spherelabs.navigationapi.GeneratePasswordDestination
import io.spherelabs.navigationapi.HelpDestination
import io.spherelabs.navigationapi.PasswordHealthDestination
import io.spherelabs.resource.icons.AnyPassIcons
import io.spherelabs.resource.icons.anypassicons.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel: HomeViewModel = useInject()

        val addNewPasswordScreen = rememberScreen(AddNewPasswordDestination.AddNewPasswordScreen)
        val accountScreen = rememberScreen(AccountDestination.Account)
        val authenticatorScreen = rememberScreen(AuthenticatorDestination.Authenticator)
        val passwordHealthScreen = rememberScreen(PasswordHealthDestination.PasswordHealth)
        val helpScreen = rememberScreen(HelpDestination.Help)
        val generatePasswordScreen = rememberScreen(GeneratePasswordDestination.GeneratePassword)
        val uiState = viewModel.state.collectAsStateWithLifecycle()

        HomeContent(
            wish = { newWish -> viewModel.wish(newWish) },
            uiState = uiState.value,
            effect = viewModel.effect,
            navigateToAuthenticator = { navigator.push(authenticatorScreen) },
            navigateToMyAccount = { navigator.push(accountScreen) },
            navigateToCreatePassword = { navigator.push(addNewPasswordScreen) },
            navigateToGenerator = {
                navigator.push(generatePasswordScreen)
            },
            navigateToHelp = { navigator.push(helpScreen) },
            navigateToPasswordHealth = { navigator.push(passwordHealthScreen) },
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

    useHomeEffect(
        wish,
        effect,
        scaffoldState,
        coroutineScope,
        snackbarState,
        navigateToCreatePassword,
        navigateToMyAccount,
        navigateToGenerator,
        navigateToPasswordHealth,
        navigateToAuthenticator,
        navigateToHelp,
    )

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom),
            )
        },
        backgroundColor = BlackRussian,
        drawerContent = {
            HomeDrawer(
                modifier = modifier,
                navigateToMyAccount = { wish.invoke(HomeWish.NavigateToMyAccount) },
                navigateToPasswordHealth = { wish.invoke(HomeWish.NavigateToPasswordHealth) },
                navigateToAuthenticator = { wish.invoke(HomeWish.NavigateToAuthenticator) },
                navigateToGenerator = { wish.invoke(HomeWish.NavigateToGenerator) },
                navigateToHelp = { wish.invoke(HomeWish.NavigateToHelp) },
            )
        },
        topBar = {
            HomeTopBar(
                modifier = modifier,
                onOpenClick = { coroutineScope.launch { scaffoldState.drawerState.open() } },
                navigateToAddNewPassword = { wish.invoke(HomeWish.NavigateToAddNewPassword) },
            )
        },
        content = {
            Column(
                modifier =
                modifier
                    .fillMaxSize()
                    .background(
                        color = BlackRussian,
                    )
                    .padding(it),
            ) {
                HomeHeadline()

                CategoryCard(
                    uiState.categories,
                    uiState.passwords,
                    onPasswordChanged = { newWish -> wish.invoke(newWish) },
                    isVisible = uiState.isVisible,
                    onGetStartingPasswordByCategory = { newWish -> wish.invoke(newWish) },
                    onCopyClick = { newPassword ->
                        wish.invoke(HomeWish.OnCopyClipboardChanged(newPassword))
                    },
                )
            }
        },
    )
}

@Composable
private fun useHomeEffect(
    wish: (HomeWish) -> Unit,
    effect: Flow<HomeEffect>,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    snackbarState: SnackbarHostState,
    navigateToCreatePassword: () -> Unit,
    navigateToMyAccount: () -> Unit,
    navigateToGenerator: () -> Unit,
    navigateToPasswordHealth: () -> Unit,
    navigateToAuthenticator: () -> Unit,
    navigateToHelp: () -> Unit,
) {

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
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                    navigateToMyAccount.invoke()
                }

                HomeEffect.NavigateToAuthenticator -> {
                    coroutineScope.launch { scaffoldState.drawerState.close() }
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
