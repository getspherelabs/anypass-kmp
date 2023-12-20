package passwordhealthimpl.ui


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import domain.model.PasswordHealth
import domain.model.PasswordStats
import domain.model.PasswordType
import io.spherelabs.common.uuid4
import io.spherelabs.foundation.color.BlackRussian
import passwordhealthimpl.ui.component.DetailsScroller
import passwordhealthimpl.ui.component.PasswordHealthColumn
import passwordhealthimpl.ui.component.PasswordHealthTopBar
import passwordhealthimpl.ui.component.PasswordStatContent
import passwordhealthimpl.ui.component.SemiProgressBar
import passwordhealthimpl.ui.component.ToolbarState


@Composable
fun PasswordHealthContent(
    modifier: Modifier = Modifier,
    percent: Float,
    navigateToHome: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val mScrollState = rememberLazyListState()

    var detailScroller by remember {
        mutableStateOf(DetailsScroller(scrollState, Float.MIN_VALUE))
    }

    val transitionState =
        remember(detailScroller) { detailScroller.toolbarTransitionState }
    val toolbarState = detailScroller.getToolbarState(LocalDensity.current)

    // Transition that fades in/out the header with the image and the Toolbar
    val transition = updateTransition(transitionState, label = "")
    val contentAlpha = transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = "",
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 1f else 0f
    }

    val toolbarHeightPx = with(LocalDensity.current) {
        278.dp.roundToPx().toFloat()
    }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    val currentPasswords = listOf(
        PasswordHealth(
            id = "1",
            name = "Behance",
            email = "test@gmail.com",
            image = "Behance",
            type = PasswordType.Weak,
        ),
        PasswordHealth(
            id = "2",
            name = "Linkedin",
            email = "test@gmail.com",
            image = "Linkedin",
            type = PasswordType.Strong,
        ),
        PasswordHealth(
            id = "3",
            name = "Behance",
            email = "test@gmail.com",
            image = "Behance",
            type = PasswordType.Reused,
        ),
    )
    Scaffold(
        modifier = modifier.nestedScroll(nestedScrollConnection),
        containerColor = BlackRussian,
        topBar = {
            PasswordHealthTopBar {
                navigateToHome.invoke()
            }
        },
    ) { newPaddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(newPaddingValues),
//                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Spacer(modifier.height(16.dp))
            SemiProgressBar(modifier, currentProgress = percent)
            Spacer(modifier.height(16.dp))
            PasswordStatContent(
                modifier,
                stats = listOf(
                    PasswordStats(
                        id = uuid4(),
                        title = "Total Passwords",
                        count = 25,
                    ),
                    PasswordStats(
                        id = uuid4(),
                        title = "Strong",
                        count = 25,
                    ),
                    PasswordStats(
                        id = uuid4(),
                        title = "Weak",
                        count = 25,
                    ),
                    PasswordStats(
                        id = uuid4(),
                        title = "Reused",
                        count = 25,
                    ),
                ),
            )
            PasswordHealthColumn(
                scrollState = mScrollState,
                modifier = modifier,
                passwords = currentPasswords,
            )
        }
    }


}

