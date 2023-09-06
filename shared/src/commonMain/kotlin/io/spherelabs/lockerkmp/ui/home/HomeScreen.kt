package io.spherelabs.lockerkmp.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.spherelabs.lockerkmp.MR
import dev.icerock.moko.resources.compose.painterResource as mokoPainterResource
import io.spherelabs.lockerkmp.components.NewItemButton
import io.spherelabs.lockerkmp.components.RoundedImage
import io.spherelabs.lockerkmp.components.Tags
import kotlin.math.abs

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().background(color = colorResource(MR.colors.lavender)),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = modifier.height(24.dp))
        Row(
            modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedImage(
                painter = mokoPainterResource(MR.images.whale_logo),
                contentDescription = null
            )

            NewItemButton { }
        }
        Spacer(modifier = modifier.height(24.dp))
        Headline()
        Spacer(modifier = modifier.height(24.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Tags(listOf("Categories", "Logins", "Note"), "Categories", modifier = modifier) {

            }
            Box(
                modifier = modifier
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = colorResource(MR.colors.black))
                    .width(56.dp)
                    .height(42.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    tint = colorResource(MR.colors.white)
                )
            }
        }

        PasswordStats(modifier)


    }
}

@Composable
fun PasswordStats(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        Modifier
            .background(color = Color.Blue)
            .padding(10.dp)
    ) {

        val boxWidth = this.maxWidth
        Box(
            modifier = Modifier
                .clip(CircleShape).background(color = Color.White)
                .size(56.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Safe", color = Color.Black)
        }
        Column() {
            Spacer(
                modifier = Modifier
                    .height(36.dp)
            )
            Row() {
                Box(
                    modifier = Modifier
                        .clip(CircleShape).background(color = Color.White)
                        .size(56.dp)
                        .zIndex(2f)
                    ,
                    contentAlignment = Alignment.Center,
                ) {
                    Text("Reused", color = Color.Black)
                }
            }
        }
    }
}


@Composable
fun Stat(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clip(CircleShape).background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("Weak")
    }
}

@Composable
fun Headline(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(start = 24.dp)
    ) {
        Row {
            Text(
                text = "Keep",
                color = colorResource(MR.colors.white),
                fontSize = 42.sp,
                fontFamily = fontFamilyResource(MR.fonts.hiraginosans.`gb-w6`)
            )
            Spacer(modifier = modifier.width(12.dp))
            RoundedImage(
                painter = mokoPainterResource(MR.images.whale_logo),
                contentDescription = null
            )

        }

        Row {

            RoundedImage(
                painter = mokoPainterResource(MR.images.whale_logo),
                contentDescription = null
            )
            Spacer(modifier = modifier.width(12.dp))
            Text(
                text = "Your Life",
                color = colorResource(MR.colors.white),
                fontSize = 42.sp,
                fontFamily = fontFamilyResource(MR.fonts.hiraginosans.`gb-w6`)
            )

        }

        Row {
            Text(
                text = "Safe",
                color = colorResource(MR.colors.white),
                fontSize = 42.sp,
                fontFamily = fontFamilyResource(MR.fonts.hiraginosans.`gb-w6`)
            )
            Spacer(modifier = modifier.width(12.dp))
            RoundedImage(
                painter = mokoPainterResource(MR.images.whale_logo),
                contentDescription = null
            )

        }
    }
}

@Composable
fun Carousel(
    count: Int,
    parentModifier: Modifier = Modifier.fillMaxWidth().height(540.dp),
    contentWidth: Dp,
    contentHeight: Dp,
    content: @Composable (modifier: Modifier, index: Int) -> Unit
) {
    val listState = rememberLazyListState(Int.MAX_VALUE / 2)

    BoxWithConstraints(
        modifier = parentModifier
    ) {
        val halfRowWidth = constraints.maxWidth / 2

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(-contentHeight / 2),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                count = Int.MAX_VALUE,
                itemContent = { globalIndex ->
                    val scale by remember {
                        derivedStateOf {
                            val currentItem =
                                listState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == globalIndex }
                                    ?: return@derivedStateOf 0.85f

                            (1f - minOf(
                                1f,
                                abs(currentItem.offset + (currentItem.size / 2) - halfRowWidth).toFloat() / halfRowWidth
                            ) * 0.25f)

                        }
                    }


                    content(
                        Modifier
                            .width(contentWidth)
                            .height(contentHeight)
                            .scale(scale)
                            .zIndex(scale * 10),
                        globalIndex % count

                    )
                }
            )
        }
    }
}