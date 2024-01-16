package io.spherelabs.addnewpasswordimpl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import io.spherelabs.addnewpasswordapi.model.WebsiteDomain
import io.spherelabs.addnewpasswordimpl.presentation.addnewlogin.AddNewLoginState
import io.spherelabs.addnewpasswordimpl.presentation.addnewlogin.AddNewLoginViewModel
import io.spherelabs.addnewpasswordimpl.presentation.addnewlogin.AddNewLoginWish
import io.spherelabs.designsystem.button.BackButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.hooks.useEffect
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.BlackRussian
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.fonts.font

class AddNewLoginScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: AddNewLoginViewModel = useInject()
        val currentUiState = viewModel.state.collectAsStateWithLifecycle()

        AddNewLoginContext(
            uiState = currentUiState.value,
            onStartLoadedWebsites = {
                viewModel.wish(AddNewLoginWish.StartLoadedWebsites)
            },
        )
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalCoilApi::class)
@Composable
fun AddNewLoginContext(
    modifier: Modifier = Modifier,
    uiState: AddNewLoginState,
    onStartLoadedWebsites: () -> Unit,
) {

    useEffect(true) {
        onStartLoadedWebsites()
    }

    Scaffold(
        containerColor = BlackRussian,
        topBar = {
            AddNewLoginTopBar { }
        },
    ) { newPaddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(newPaddingValues)
                .consumeWindowInsets(paddingValues = newPaddingValues),
        ) {
            AddNewLoginTextField(modifier = modifier, textValue = "") {
            }
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
                paddingValues = newPaddingValues,
                websites = uiState.websites,
            )
        }
    }
}

@Composable
fun WebsiteGridLayout(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    websites: List<WebsiteDomain>,
) {

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
        ) {
            val request = ImageRequest.Builder(LocalPlatformContext.current)
                .data("https://icon.horse/icon/${it.url}")
                .crossfade(true)
                .build()

            Column(
                modifier = modifier
                    .padding(16.dp)
                    .width(120.dp)
                    .height(56.dp)
                    .background(color = Jaguar, RoundedCornerShape(16.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = request, contentDescription = null,
                    modifier = Modifier
                        .requiredSize(28.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = it.name,
                    fontFamily = GoogleSansFontFamily,
                    fontSize = 12.sp,
                    color = Color.White,
                )
            }

        }

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
    textValue: String,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> Unit,
) {
    Column {
        androidx.compose.material.TextField(
            modifier = modifier.fillMaxWidth().padding(start = 12.dp, end = 12.dp, top = 16.dp),
            value = textValue,
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
            onValueChange = { newValue -> onValueChanged.invoke(newValue) },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
        )
    }
}
