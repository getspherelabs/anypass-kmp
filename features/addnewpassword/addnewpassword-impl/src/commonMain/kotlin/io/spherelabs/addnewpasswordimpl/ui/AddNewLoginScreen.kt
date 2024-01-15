package io.spherelabs.addnewpasswordimpl.ui

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.compose.setSingletonImageLoaderFactory
import coil3.fetch.NetworkFetcher
import coil3.request.ImageRequest
import io.spherelabs.addnewpasswordapi.model.WebsiteDomain
import io.spherelabs.addnewpasswordimpl.presentation.addnewlogin.AddNewLoginState
import io.spherelabs.designsystem.button.BackButton
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.text.Headline
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily

class AddNewLoginScreen : Screen {

    @Composable
    override fun Content() {

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddNewLoginContext(
    modifier: Modifier = Modifier,
    uiState: AddNewLoginState,
) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                add(NetworkFetcher.Factory())
            }
            .build()
    }
    Scaffold(
        topBar = {
            AddNewLoginTopBar { }
        },
    ) { newPaddingValues ->
        Column(
            modifier = modifier.fillMaxSize().padding(newPaddingValues)
                .consumeWindowInsets(paddingValues = newPaddingValues),
        ) {
            AddNewLoginTextField("") {

            }
            Spacer(modifier.height(16.dp))
            Text("Enter the service you want to add")
            WebsiteGridLayout(
                websites = uiState.websites,
            )
        }
    }
}

@Composable
fun WebsiteGridLayout(
    modifier: Modifier = Modifier,
    websites: List<WebsiteDomain>,
) {
    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(3),
    ) {
        items(
            items = websites,
            key = {
                it.name
            },
        ) {
            val request = ImageRequest.Builder(LocalPlatformContext.current)
                .data("https://www.google.com/s2/favicons?domain=${it.url}")
                .build()

            println("Request is ${request.error()}")
            AsyncImage(
                model = request, contentDescription = null,
                modifier = Modifier
                    .size(65.dp),
                contentScale = ContentScale.Crop,
            )
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
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> Unit,
) {
    Column {
        androidx.compose.material.TextField(
            modifier = modifier.width(240.dp).padding(start = 12.dp, end = 12.dp),
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
