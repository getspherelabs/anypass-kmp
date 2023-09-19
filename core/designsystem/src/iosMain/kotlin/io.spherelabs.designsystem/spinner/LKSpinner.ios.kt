package io.spherelabs.designsystem.spinner

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.node.Ref
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import io.spherelabs.designsystem.hooks.useState
import io.spherelabs.designsystem.hooks.useTransition
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalMaterialApi::class)
@Composable
actual fun LKSpinner(
    expanded: Boolean, modifier: Modifier, onExpandedChange: (Boolean) -> Unit,
    options: List<String>, onOptionChosen: (String) -> Unit, current: String
) {
    val lightBlue = Color(0xffd8e6ff)

    DropdownMenuBox(
        modifier = modifier.fillMaxWidth().height(65.dp).padding(horizontal = 24.dp).clip(
            RoundedCornerShape(8.dp)
        ),
        expanded = expanded,
        onExpandedChange = onExpandedChange
    ) {
        TextField(
            modifier = modifier.fillMaxWidth().clip(
                RoundedCornerShape(8.dp)
            ),
            readOnly = true,
            value = current,
            onValueChange = {},
            placeholder = {
                Text(text = "Choose a category", color = Color.Black.copy(0.5f))
            },
            trailingIcon = {
                LKDropdownDefaults.TrailingIcon(expanded)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { onExpandedChange(false) }) {
            options.forEach {
                DropdownMenuItem(onClick = {
                    onOptionChosen(it)
                    onExpandedChange(false)
                }) {
                    Text(it)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
internal fun DropdownMenuBox(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable DropdownMenuBoxScope.() -> Unit
) {
    val density = LocalDensity.current

    val (width, setWidth) = useState(0)
    val (height, setHeight) = useState(0)

    val coordinates = remember { Ref<LayoutCoordinates>() }

    val scope = remember(density, height, width) {
        object : DropdownMenuBoxScope {
            override fun Modifier.dropdownSize(matchTextFieldWidth: Boolean): Modifier {
                return with(density) {
                    heightIn(max = height.toDp()).let {
                        if (matchTextFieldWidth) {
                            it.width(width.toDp())
                        } else it
                    }
                }
            }
        }
    }
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier.onGloballyPositioned {
            setWidth(it.size.width)
            coordinates.value = it
            setHeight(1920)
        }.expandable(
            onExpandedChange = { onExpandedChange(!expanded) },
            menuLabel = "Dropdown Menu"
        ).focusRequester(focusRequester)
    ) {
        scope.content()
    }

    SideEffect {
        if (expanded) focusRequester.requestFocus()
    }
}

@ExperimentalMaterialApi
internal interface DropdownMenuBoxScope {

    fun Modifier.dropdownSize(
        matchTextFieldWidth: Boolean = true
    ): Modifier

    @Composable
    fun DropdownMenu(
        expanded: Boolean,
        onDismissRequest: () -> Unit,
        modifier: Modifier = Modifier,
        content: @Composable ColumnScope.() -> Unit
    ) {
        val expandedStates = useTransition(false)
        expandedStates.targetState = expanded

        if (expandedStates.currentState || expandedStates.targetState) {
            val transformOriginState = remember { mutableStateOf(TransformOrigin.Center) }
            val density = LocalDensity.current
            val popupPositionProvider = LKDropdownMenuPositionProvider(
                DpOffset.Zero,
                density
            ) { parentBounds, menuBounds ->
                transformOriginState.value = calculateTransformOrigin(parentBounds, menuBounds)
            }

            Popup(
                onDismissRequest = onDismissRequest,
                popupPositionProvider = popupPositionProvider
            ) {
                DropdownMenuContent(
                    expandedStates = expandedStates,
                    transformOriginState = transformOriginState,
                    modifier = modifier.dropdownSize(),
                    content = content
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun Modifier.expandable(
    onExpandedChange: () -> Unit,
    menuLabel: String
) = pointerInput(Unit) {
    awaitEachGesture {
        awaitFirstDown(pass = PointerEventPass.Initial)
        val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
        if (upEvent != null) {
            onExpandedChange()
        }
    }
}.semantics {
    contentDescription = menuLabel
    onClick {
        onExpandedChange()
    }
}

@Composable
internal fun DropdownMenuItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    dropdownStyle: LKDropdownStyle = LKDropdownDefaults.dropdownStyle(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    DropdownMenuItemContent(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = dropdownStyle.itemContentPadding().value,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
internal fun DropdownMenuContent(
    expandedStates: MutableTransitionState<Boolean>,
    transformOriginState: MutableState<TransformOrigin>,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    // Menu open/close animation.
    val transition = updateTransition(expandedStates, "DropDownMenu")

    val scale by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded
                tween(
                    durationMillis = LKDropdownTokens.InTransitionDuration,
                    easing = LinearOutSlowInEasing
                )
            } else {
                // Expanded to dismissed.
                tween(
                    durationMillis = 1,
                    delayMillis = LKDropdownTokens.OutTransitionDuration - 1
                )
            }
        }
    ) {
        if (it) {
            // Menu is expanded.
            1f
        } else {
            // Menu is dismissed.
            0.8f
        }
    }

    val alpha by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded
                tween(durationMillis = 30)
            } else {
                // Expanded to dismissed.
                tween(durationMillis = LKDropdownTokens.OutTransitionDuration)
            }
        }
    ) {
        if (it) {
            // Menu is expanded.
            1f
        } else {
            // Menu is dismissed.
            0f
        }
    }
    Card(
        modifier = Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
            this.alpha = alpha
            transformOrigin = transformOriginState.value
        },
        elevation = LKDropdownTokens.MenuElevation
    ) {
        Column(
            modifier = modifier
                .padding(vertical = LKDropdownTokens.MenuVerticalPadding)
                .width(IntrinsicSize.Max)
                .verticalScroll(rememberScrollState()),
            content = content
        )
    }
}

@Composable
internal fun DropdownMenuItemContent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = MenuDefaults.DropdownMenuItemContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {

    Row(
        modifier = modifier
            .clickable(
                enabled = enabled,
                onClick = onClick,
                interactionSource = interactionSource,
                indication = rememberRipple(true)
            )
            .fillMaxWidth()
            .sizeIn(
                minWidth = LKDropdownTokens.MenuItemDefaultMinWidth,
                maxWidth = LKDropdownTokens.MenuItemDefaultMaxWidth,
                minHeight = LKDropdownTokens.MenuItemDefaultMinHeight
            )
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val typography = MaterialTheme.typography
        ProvideTextStyle(typography.subtitle1) {
            val contentAlpha = if (enabled) ContentAlpha.high else ContentAlpha.disabled
            CompositionLocalProvider(LocalContentAlpha provides contentAlpha) {
                content()
            }
        }
    }
}

internal fun calculateTransformOrigin(
    parentBounds: IntRect,
    menuBounds: IntRect
): TransformOrigin {
    val pivotX = when {
        menuBounds.left >= parentBounds.right -> 0f
        menuBounds.right <= parentBounds.left -> 1f
        menuBounds.width == 0 -> 0f
        else -> {
            val intersectionCenter =
                (max(parentBounds.left, menuBounds.left) +
                        min(parentBounds.right, menuBounds.right)) / 2
            (intersectionCenter - menuBounds.left).toFloat() / menuBounds.width
        }
    }
    val pivotY = when {
        menuBounds.top >= parentBounds.bottom -> 0f
        menuBounds.bottom <= parentBounds.top -> 1f
        menuBounds.height == 0 -> 0f
        else -> {
            val intersectionCenter = (max(parentBounds.top, menuBounds.top) +
                    min(parentBounds.bottom, menuBounds.bottom)) / 2
            (intersectionCenter - menuBounds.top).toFloat() / menuBounds.height
        }
    }
    return TransformOrigin(pivotX, pivotY)
}

