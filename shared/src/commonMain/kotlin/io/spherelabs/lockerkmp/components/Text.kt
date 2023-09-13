package io.spherelabs.lockerkmp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.spherelabs.lockerkmp.MR
import kotlin.math.roundToInt

@Composable
fun Headline(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
    Text(
        modifier = modifier.padding(start = 24.dp, top = 16.dp),
        text = text,
        fontSize = 32.sp,
        fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
        color = textColor
    )
}


@Composable
internal fun CollapsingLayout(
    collapsingTop: @Composable BoxScope.() -> Unit,
    bodyContent: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
) {
    var collapsingTopHeight by remember { mutableStateOf(0f) }

    var offset by remember { mutableStateOf(0f) }

    fun calculateOffset(delta: Float): Offset {
        val oldOffset = offset
        val newOffset = (oldOffset + delta).coerceIn(-collapsingTopHeight, 0f)
        offset = newOffset
        return Offset(0f, newOffset - oldOffset)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset =
                when {
                    available.y >= 0 -> Offset.Zero
                    offset == -collapsingTopHeight -> Offset.Zero
                    else -> calculateOffset(available.y)
                }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource,
            ): Offset =
                when {
                    available.y <= 0 -> Offset.Zero
                    offset == 0f -> Offset.Zero
                    else -> calculateOffset(available.y)
                }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        Box(
            modifier = Modifier
                .onSizeChanged { size ->
                    collapsingTopHeight = size.height.toFloat()
                }
                .offset { IntOffset(x = 0, y = (collapsingTopHeight).roundToInt()) },
            content = collapsingTop,
        )
        Box(
            modifier = Modifier.offset {
                IntOffset(
                    x = 0,
                    y = (collapsingTopHeight + offset).roundToInt()
                )
            },
            content = bodyContent,
        )
    }
}