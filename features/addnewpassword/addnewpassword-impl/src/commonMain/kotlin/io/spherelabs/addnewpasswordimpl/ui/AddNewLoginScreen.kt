package io.spherelabs.addnewpasswordimpl.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.spherelabs.addnewpasswordapi.model.WebsiteDomain
import io.spherelabs.addnewpasswordimpl.presentation.addnewlogin.*
import io.spherelabs.common.Empty
import io.spherelabs.designsystem.button.BackButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.hooks.useScope
import io.spherelabs.designsystem.hooks.useSnackbar
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.navigationapi.AddNewPasswordDestination
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.icons.SocialMediaResourceProvider
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AddNewLoginScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: AddNewLoginViewModel = useInject()
        val resourceProvider: SocialMediaResourceProvider = useInject()

        val navigator = LocalNavigator.currentOrThrow

        val currentUiState = viewModel.state.collectAsStateWithLifecycle()

        AddNewLoginContext(
            uiState = currentUiState.value,
            uiEffect = viewModel.effect,
            resourceProvider = resourceProvider,
            onStartLoadedWebsites = {
                viewModel.wish(AddNewLoginWish.StartLoadedWebsites)
            },
            onSearchFocusChanged = { newValue ->
                viewModel.wish(AddNewLoginWish.OnSearchFocusChanged(newValue))
            },
            onSearchClearClicked = {
                viewModel.wish(AddNewLoginWish.OnSearchClearClicked)
            },
            onSearchLoadedWebsites = { newQuery ->
                viewModel.wish(AddNewLoginWish.OnSearchLoadedWebsites(newQuery))
            },
            onQuerySearchChanged = {
                viewModel.wish(AddNewLoginWish.OnSearchQueryChanged(it))
            },
            onSearchingChanged = {
                viewModel.wish(AddNewLoginWish.OnSearchingChanged)
            },
            onBackClick = {
                navigator.pop()
            },
        )
    }
}

@OptIn(ExperimentalLayoutApi::class, FlowPreview::class)
@Composable
fun AddNewLoginContext(
    modifier: Modifier = Modifier,
    uiState: AddNewLoginState,
    uiEffect: Flow<AddNewLoginEffect>,
    resourceProvider: SocialMediaResourceProvider,
    onStartLoadedWebsites: () -> Unit,
    onSearchFocusChanged: (Boolean) -> Unit,
    onSearchLoadedWebsites: (String) -> Unit,
    onQuerySearchChanged: (String) -> Unit,
    onSearchingChanged: () -> Unit,
    onSearchClearClicked: () -> Unit,
    onBackClick: () -> Unit,
) {
    val snackbarHostState = useSnackbar()
    val scope = useScope()

    val navigator = LocalNavigator.currentOrThrow

    useEffect(true) {
        onStartLoadedWebsites()

        uiEffect.collectLatest { effect ->
            when (effect) {
                is AddNewLoginEffect.Failure -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = effect.message,
                        )
                    }
                }
            }

        }
    }

    useEffect(uiState.query) {
        snapshotFlow { uiState.query }
            .debounce(250)
            .onEach { onSearchingChanged.invoke() }
            .collectLatest { searchQuery ->
                onSearchLoadedWebsites.invoke(searchQuery)
            }
    }

    Scaffold(
        containerColor = BlackRussian,
        topBar = {
            AddNewLoginTopBar {
                onBackClick()
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom),
            )
        },
    ) { newPaddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize().padding(newPaddingValues)
                .consumeWindowInsets(paddingValues = newPaddingValues),
        ) {
            AddNewLoginTextField(
                modifier = modifier,
                query = uiState.query,
                isSearchFocused = uiState.isSearchFocused,
                onClearClick = { onSearchClearClicked() },
                onSearchFocusChanged = { isFocused ->
                    onSearchFocusChanged(isFocused)
                },
                onValueChanged = { newValue ->
                    onQuerySearchChanged(newValue)
                },
            )

            if (uiState.isSearchFocused) {
                WebsiteColumn(websites = uiState.filteredWebsites)
            }

            if (uiState.query.isEmpty() && !uiState.isSearchFocused) {
                Spacer(modifier.height(16.dp))
                Text(
                    modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    text = "Enter the service you want to add",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = GoogleSansFontFamily,
                )
                WebsiteGridLayout(
                    modifier = modifier,
                    resourceProvider = resourceProvider,
                    paddingValues = newPaddingValues,
                    websites = uiState.websites,
                )
            }


        }
    }
}

@Composable
fun WebsiteGridLayout(
    paddingValues: PaddingValues,
    resourceProvider: SocialMediaResourceProvider,
    modifier: Modifier = Modifier,
    websites: List<UIWebsite>,
) {
    val navigator = LocalNavigator.currentOrThrow

    LazyVerticalStaggeredGrid(
        modifier = modifier.padding(horizontal = 24.dp),
        contentPadding = paddingValues,
        columns = StaggeredGridCells.Fixed(3),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        items(
            items = websites,
            key = {
                it.url
            },
        ) { website ->
            val img = resourceProvider.loadMedia(website.name)

            val addPasswordScreen = rememberScreen(
                AddNewPasswordDestination.UpdatePasswordScreen(
                    website.name,
                    website.url,
                ),
            )
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .width(120.dp)
                    .height(56.dp)
                    .background(color = Jaguar, RoundedCornerShape(16.dp))
                    .clickable {
                        navigator.push(addPasswordScreen)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    imageVector = img,
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSize(28.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = website.name,
                    fontFamily = GoogleSansFontFamily,
                    fontSize = 12.sp,
                    color = Color.White,
                )
            }

        }

    }
}

@Composable
private fun WebsiteColumn(
    websites: List<UIWebsite>,
    modifier: Modifier = Modifier,
) {
    val navigator = LocalNavigator.currentOrThrow
    Spacer(modifier.height(16.dp))
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 24.dp),
    ) {
        if (websites.isNotEmpty()) {
            items(
                websites,
                key = {
                    it.name
                },
            ) { website ->
                val addPasswordScreen = rememberScreen(
                    AddNewPasswordDestination.UpdatePasswordScreen(
                        website.name,
                        website.url,
                    ),
                )
                WebsiteRow(
                    website.name,
                    onClick = {
                        navigator.push(addPasswordScreen)
                    },
                )
            }
        } else {
            item {
                Text(
                    "The item does not exist",
                    fontFamily = GoogleSansFontFamily,
                    fontSize = 18.sp,
                    color = Color.White,
                )
            }
        }

    }

}

@Composable
private fun WebsiteRow(
    name: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable {
            onClick()
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            fontFamily = GoogleSansFontFamily,
            fontSize = 16.sp,
            color = Color.White,
        )
    }

}

@Composable
internal fun AddNewLoginTopBar(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    val strings = LocalStrings.current

    Row(
        modifier = modifier.padding(top = 16.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BackButton(
            modifier = modifier,
            backgroundColor = LavenderBlue.copy(0.7f),
            iconColor = Color.White,
            navigateToBack = { navigateToBack.invoke() },
        )
        Headline(
            text = strings.addLogin,
            modifier = modifier,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Medium,
            textColor = Color.White,
        )
    }
}

@Composable
fun AddNewLoginTextField(
    query: String,
    isSearchFocused: Boolean,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> Unit,
    onSearchFocusChanged: (Boolean) -> Unit,
    onClearClick: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val isVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    useEffect(isVisible) {
        if (!isVisible) {
            onSearchFocusChanged(false)
        } else {
            onSearchFocusChanged(true)
        }
    }

    Column {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester).onFocusChanged {
                    onSearchFocusChanged.invoke(it.isFocused)
                }.padding(start = 12.dp, end = 12.dp, top = 16.dp),
            value = query,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Jaguar,
                cursorColor = Color.White.copy(0.7f),
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            leadingIcon = {
                AnimatedVisibility(
                    visible = isSearchFocused,
                    enter = fadeIn(animationSpec = tween(2000)),
                    exit = fadeOut(animationSpec = tween(2000)),
                ) {
                    IconButton(
                        onClick = {
                            focusManager.clearFocus()
                            onValueChanged.invoke(String.Empty)
                        },
                    ) {
                        Icon(
                            Icons.Rounded.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                }

            },
            trailingIcon = {
                if (query.isNotBlank()) {
                    AnimatedContent(isSearchFocused) {
                        IconButton(onClick = onClearClick) {
                            Icon(
                                Icons.Rounded.Close,
                                contentDescription = null,
                                tint = Color.Red.copy(0.6f),
                            )
                        }

                    }
                }
            },
            onValueChange = { newValue -> onValueChanged.invoke(newValue) },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
        )
    }
}

@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}
