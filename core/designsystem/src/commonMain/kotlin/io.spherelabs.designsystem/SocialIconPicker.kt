package io.spherelabs.designsystem

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeableState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import io.spherelabs.designsystem.dialog.MaterialDialog
import io.spherelabs.designsystem.dialog.MaterialDialogButtons
import io.spherelabs.designsystem.dialog.MaterialDialogProperties
import io.spherelabs.designsystem.dialog.MaterialDialogScope
import io.spherelabs.designsystem.dialog.rememberMaterialDialogState

private val itemSizeDp = 55.dp
private val tickSize = 35.dp

private enum class ColorPickerScreen {
    Palette,
    ARGB
}

@Composable
fun DialogAndShowButton(
    buttonText: String,
    size: DpSize = DpSize(400.dp, 300.dp),
    buttons: @Composable MaterialDialogButtons.() -> Unit = {},
    content: @Composable MaterialDialogScope.() -> Unit
) {
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = buttons,
        properties = MaterialDialogProperties(size = size)
    ) {
        content()
    }

    TextButton(
        onClick = { dialogState.show() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colors.primaryVariant),
    ) {
        Text(
            buttonText,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            color = MaterialTheme.colors.onPrimary
        )
    }
}

data class ARGBPickerState internal constructor(
    val allowCustomARGB: Boolean = true,
    val showAlphaSelector: Boolean = true
) {
    companion object {
        val None = ARGBPickerState(allowCustomARGB = false)
        val WithAlphaSelector = ARGBPickerState(showAlphaSelector = true)
        val WithoutAlphaSelector = ARGBPickerState(showAlphaSelector = false)
    }
}


@ExperimentalMaterialApi
@Composable
fun MaterialDialogScope.colorChooser(
    colors: List<Color>,
    subColors: List<List<Color>> = listOf(),
    initialSelection: Int = 0,
    argbPickerState: ARGBPickerState = ARGBPickerState.None,
    waitForPositiveButton: Boolean = true,
    onColorSelected: (Color) -> Unit = {}
) {
    BoxWithConstraints {
        val selectedColor = remember { mutableStateOf(colors[initialSelection]) }
        val anchors = remember(argbPickerState.allowCustomARGB) {
            if (argbPickerState.allowCustomARGB) {
                mapOf(
                    0f to ColorPickerScreen.Palette,
                    constraints.maxWidth.toFloat() to ColorPickerScreen.ARGB
                )
            } else {
                mapOf(0f to ColorPickerScreen.Palette)
            }
        }
        val swipeState = rememberSwipeableState(ColorPickerScreen.Palette)

        if (waitForPositiveButton) {
            DialogCallback { onColorSelected(selectedColor.value) }
        } else {
            DisposableEffect(selectedColor.value) {
                onColorSelected(selectedColor.value)
                onDispose { }
            }
        }

        Column(
            Modifier
                .padding(bottom = 8.dp)
                .swipeable(
                    swipeState,
                    anchors = anchors,
                    orientation = Orientation.Horizontal,
                    reverseDirection = true,
                    resistance = null,
                    enabled = argbPickerState.allowCustomARGB
                )
        ) {
            if (argbPickerState.allowCustomARGB) {
                PageIndicator(swipeState, this@BoxWithConstraints.constraints)
            }

            Layout(
                content = {
                    ColorGridLayout(
                        Modifier.width(this@BoxWithConstraints.maxWidth),
                        colors = colors,
                        selectedColor = selectedColor,
                        subColors = subColors,
                        initialSelection = initialSelection
                    )
                },
            ) { measurables, constraints ->
                val placeables = measurables.map { it.measure(constraints) }
                val height = placeables.maxByOrNull { it.height }?.height ?: 0

                layout(constraints.maxWidth, height) {
                    placeables.forEachIndexed { index, placeable ->
                        placeable.place(
                            x = -swipeState.offset.value.toInt() + index * constraints.maxWidth,
                            y = 0
                        )
                    }
                }
            }
        }
    }
}





@ExperimentalMaterialApi
@Composable
private fun PageIndicator(swipeState: SwipeableState<ColorPickerScreen>, constraints: Constraints) {
    BoxWithConstraints {
        val indicatorRadius = remember { constraints.maxWidth * 0.01f }
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 8.dp, bottom = 16.dp)
        ) {
            val ratio = remember(constraints.maxWidth, swipeState.offset.value) {
                swipeState.offset.value / constraints.maxWidth.toFloat()
            }
            val color = MaterialTheme.colors.onBackground
            Canvas(modifier = Modifier) {
                val offset = Offset(3 * indicatorRadius, 0f)
                drawCircle(
                    color.copy(0.7f + 0.3f * (1 - ratio)),
                    radius = (indicatorRadius + indicatorRadius * (1 - ratio)).coerceAtMost(15f),
                    center = center - offset
                )
                drawCircle(
                    color.copy(0.7f + 0.3f * ratio),
                    radius = (indicatorRadius + indicatorRadius * ratio).coerceAtMost(15f),
                    center = center + offset
                )
            }
        }
    }
}


@Composable
private fun ColorGridLayout(
    modifier: Modifier = Modifier,
    colors: List<Color>,
    selectedColor: MutableState<Color>,
    subColors: List<List<Color>> = listOf(),
    initialSelection: Int
) {
    var mainSelectedIndex by remember { mutableStateOf(initialSelection) }
    var showSubColors by remember { mutableStateOf(false) }

    val itemSize = with(LocalDensity.current) { itemSizeDp.toPx().toInt() }

    GridView(modifier, itemSize = itemSize) {
        if (!showSubColors) {
            colors.forEachIndexed { index, item ->
                ColorView(
                    color = item,
                    selected = index == mainSelectedIndex
                ) {
                    if (mainSelectedIndex != index) {
                        mainSelectedIndex = index
                        selectedColor.value = item
                    }

                    if (subColors.isNotEmpty()) {
                        showSubColors = true
                    }
                }
            }
        } else {
            Box(
                Modifier
//                    .testTag("dialog_sub_color_back_btn")
                    .size(itemSizeDp)
                    .clip(CircleShape)
                    .clickable(
                        onClick = {
                            showSubColors = false
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    Icons.Default.ArrowBack,
                    contentDescription = "Go back to main color page",
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(tickSize)
                )
            }

            subColors[mainSelectedIndex].forEachIndexed { index, item ->
                ColorView(
//                    modifier = Modifier.testTag("dialog_sub_color_selector_$index"),
                    color = item,
                    selected = selectedColor.value == item
                ) {
                    selectedColor.value = item
                }
            }
        }
    }
}


@Composable
private fun ColorView(
    modifier: Modifier = Modifier,
    color: Color,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier
            .size(itemSizeDp)
            .clip(CircleShape)
            .background(color)
            .border(1.dp, MaterialTheme.colors.onBackground, CircleShape)
            .clickable(
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center
    ) {
        if (selected) {
            Image(
                Icons.Default.Done,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color.foreground()),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(tickSize)
            )
        }
    }
}
private fun Color.foreground(): Color {
    return if (luminance() > 0.5f) Color.Black else Color.White
}

@Composable
private fun SocialIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier
            .size(65.dp)
            .clip(CircleShape)
            .border(1.dp, MaterialTheme.colors.onBackground, CircleShape)
            .clickable(
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = icon,
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
private fun GridView(
    modifier: Modifier = Modifier,
    itemsInRow: Int = 4,
    itemSize: Int,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(modifier) {
        LazyColumn {
            item {
                Layout(
                    { content() },
                    Modifier
                        .padding(top = 8.dp, start = 24.dp, end = 24.dp)
                        .fillMaxWidth()
                        .align(Alignment.Center)
                ) { measurables, constraints ->
                    val spacing =
                        (constraints.maxWidth - (itemSize * itemsInRow)) / (itemsInRow - 1)
                    val additionalRow = measurables.size % 2
                    val rows = maxOf((measurables.size / itemsInRow) + additionalRow, 1)
                    val layoutHeight = (rows * itemSize) + ((rows - 1) * spacing)

                    layout(constraints.maxWidth, layoutHeight) {
                        measurables
                            .map {
                                it.measure(
                                    Constraints(
                                        maxHeight = itemSize,
                                        maxWidth = itemSize
                                    )
                                )
                            }
                            .forEachIndexed { index, it ->
                                it.place(
                                    x = (index % itemsInRow) * (itemSize + spacing),
                                    y = (index / itemsInRow) * (itemSize + spacing)
                                )
                            }
                    }
                }
            }
        }
    }
}

object ColorPalette {
    val Primary = listOf(
        Color(0xFFF44336), Color(0xFFE91E63), Color(0xFF9C27B0),
        Color(0xFF673AB7), Color(0xFF3F51B5), Color(0xFF2196F3),
        Color(0xFF03A9F4), Color(0xFF00BCD4), Color(0xFF009688),
        Color(0xFF4CAF50), Color(0xFF8BC34A), Color(0xFFCDDC39),
        Color(0xFFFFEB3B), Color(0xFFFFC107), Color(0xFFFF9800),
        Color(0xFFFF5722), Color(0xFF795548), Color(0xFF9E9E9E),
        Color(0xFF607D8B)
    )
}