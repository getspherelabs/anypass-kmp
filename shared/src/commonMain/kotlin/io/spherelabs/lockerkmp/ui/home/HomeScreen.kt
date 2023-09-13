package io.spherelabs.lockerkmp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.lockerkmp.MR
import io.spherelabs.lockerkmp.components.*
import io.spherelabs.lockerkmp.components.collapsingToolbar.CollapsingToolbarScaffold
import io.spherelabs.lockerkmp.components.collapsingToolbar.CollapsingToolbarScaffoldState
import io.spherelabs.lockerkmp.components.collapsingToolbar.ScrollStrategy
import io.spherelabs.lockerkmp.components.collapsingToolbar.rememberCollapsingToolbarScaffoldState
import dev.icerock.moko.resources.compose.painterResource as mokoPainterResource

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToCreatePassword: () -> Unit
) {
    val state = rememberScrollState(0)
    val scaffoldState = rememberScaffoldState()
    val toolbarState: CollapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = colorResource(MR.colors.lavender),
        content =  {
            CollapsingToolbarScaffold(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it),
                scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
                state = toolbarState,
                toolbar = {
                    Row(
                        modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        RoundedImage(
                            painter = mokoPainterResource(MR.images.avatar), contentDescription = null
                        )

                        NewItemButton {
                            navigateToCreatePassword.invoke()
                        }
                    }
                    Headline()

                }
            ) {
                                Column(
                    modifier = modifier.fillMaxWidth()
                        .background(color = colorResource(MR.colors.lavender)),
                    verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Tags(
                            listOf("Categories", "Logins", "Note"),
                            "Categories",
                            modifier = modifier
                        ) {

                        }
                        Box(
                            modifier = modifier.padding(top = 12.dp).clip(RoundedCornerShape(20.dp))
                                .background(color = colorResource(MR.colors.black)).width(56.dp)
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

                    Column(modifier = modifier.fillMaxSize().verticalScroll(state)) {
                        Text(
                            modifier = modifier.padding(start = 24.dp),
                            text = "Password Health",
                            fontSize = 24.sp,
                            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                            color = Color.White
                        )

                        Row(
                            modifier = modifier.fillMaxWidth().height(250.dp)
                                .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(color = colorResource(MR.colors.dynamic_yellow)),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(modifier = modifier.wrapContentSize()) {
                                Box(
                                    modifier = modifier.padding(start = 12.dp, top = 12.dp)
                                        .size(32.dp)
                                        .clip(CircleShape).background(color = Color.Black),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        modifier = modifier.size(16.dp),
                                        imageVector = Icons.Outlined.Lock,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                                Text(
                                    modifier = modifier.padding(start = 12.dp, top = 8.dp),
                                    text = "Total password: 49"
                                )
                                Image(
                                    modifier = modifier.padding(start = 12.dp, top = 8.dp),
                                    painter = painterResource(MR.images.whale_logo),
                                    contentDescription = null
                                )
                            }
                            Spacer(modifier = modifier.width(25.dp))

                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(modifier = modifier.width(25.dp))

//                                Boxes(
//                                    modifier = modifier.padding(top = 10.dp)
//                                        .align(Alignment.CenterVertically)
//                                ) {
//                                    Text(
//                                        text = "56",
//                                        fontSize = 16.sp,
//                                        fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
//                                    )
//                                    Box(
//                                        modifier = modifier.clip(CircleShape)
//                                            .background(color = Color.White)
//                                            .size(56.dp),
//                                        contentAlignment = Alignment.Center,
//                                    ) {
//                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
//                                    }
//                                    Box(
//                                        modifier = modifier.clip(CircleShape)
//                                            .background(color = Color.White)
//                                            .size(56.dp),
//                                        contentAlignment = Alignment.Center,
//                                    ) {
//                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
//                                    }
//                                    Text(text = "56", fontSize = 16.sp)
//                                    Text(text = "56", fontSize = 16.sp)
//
//                                    Box(
//                                        modifier = modifier.clip(CircleShape)
//                                            .background(color = Color.White)
//                                            .size(56.dp),
//                                        contentAlignment = Alignment.Center,
//                                    ) {
//                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
//                                    }
//
//                                    Box(
//                                        modifier = modifier.clip(CircleShape)
//                                            .background(color = Color.White)
//                                            .size(56.dp),
//                                        contentAlignment = Alignment.Center,
//                                    ) {
//                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
//                                    }
//                                    Text(text = "56", fontSize = 16.sp)
//
//                                }
                            }

                        }

                        Text(
                            modifier = modifier.padding(start = 24.dp),
                            text = "Domains",
                            fontSize = 24.sp,
                            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
                            color = Color.White
                        )
                        DomainCard(modifier)


                    }

                }

            }
        }

//        topBar = {
//            Row(
//                modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                RoundedImage(
//                    painter = mokoPainterResource(MR.images.avatar), contentDescription = null
//                )
//
//                NewItemButton {
//                    navigateToCreatePassword.invoke()
//                }
//            }
//        }
//    ) { newPaddingValues ->
//        CollapsingLayout(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(newPaddingValues),
//            collapsingTop = {
//                Headline(modifier = modifier.fillMaxWidth())
//            },
//            bodyContent = {
//                Column(
//                    modifier = modifier.fillMaxWidth()
//                        .background(color = colorResource(MR.colors.lavender)),
//                    verticalArrangement = Arrangement.Top
//                ) {
//                    Row(
//                        modifier = modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Tags(
//                            listOf("Categories", "Logins", "Note"),
//                            "Categories",
//                            modifier = modifier
//                        ) {
//
//                        }
//                        Box(
//                            modifier = modifier.padding(top = 12.dp).clip(RoundedCornerShape(20.dp))
//                                .background(color = colorResource(MR.colors.black)).width(56.dp)
//                                .height(42.dp),
//                            contentAlignment = Alignment.Center,
//                        ) {
//                            Icon(
//                                Icons.Default.Add,
//                                contentDescription = null,
//                                tint = colorResource(MR.colors.white)
//                            )
//                        }
//                    }
//
//                    Column(modifier = modifier.fillMaxSize().verticalScroll(state)) {
//                        Text(
//                            modifier = modifier.padding(start = 24.dp),
//                            text = "Password Health",
//                            fontSize = 24.sp,
//                            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
//                            color = Color.White
//                        )
//
//                        Row(
//                            modifier = modifier.fillMaxWidth().height(250.dp)
//                                .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
//                                .clip(RoundedCornerShape(16.dp))
//                                .background(color = colorResource(MR.colors.dynamic_yellow)),
//                            horizontalArrangement = Arrangement.SpaceAround
//                        ) {
//                            Column(modifier = modifier.wrapContentSize()) {
//                                Box(
//                                    modifier = modifier.padding(start = 12.dp, top = 12.dp)
//                                        .size(32.dp)
//                                        .clip(CircleShape).background(color = Color.Black),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    Icon(
//                                        modifier = modifier.size(16.dp),
//                                        imageVector = Icons.Outlined.Lock,
//                                        contentDescription = null,
//                                        tint = Color.White
//                                    )
//                                }
//                                Text(
//                                    modifier = modifier.padding(start = 12.dp, top = 8.dp),
//                                    text = "Total password: 49"
//                                )
//                                Image(
//                                    modifier = modifier.padding(start = 12.dp, top = 8.dp),
//                                    painter = painterResource(MR.images.whale_logo),
//                                    contentDescription = null
//                                )
//                            }
//                            Spacer(modifier = modifier.width(25.dp))
//
//                            Row(
//                                modifier = modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.Center,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Spacer(modifier = modifier.width(25.dp))
//
////                                Boxes(
////                                    modifier = modifier.padding(top = 10.dp)
////                                        .align(Alignment.CenterVertically)
////                                ) {
////                                    Text(
////                                        text = "56",
////                                        fontSize = 16.sp,
////                                        fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)
////                                    )
////                                    Box(
////                                        modifier = modifier.clip(CircleShape)
////                                            .background(color = Color.White)
////                                            .size(56.dp),
////                                        contentAlignment = Alignment.Center,
////                                    ) {
////                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
////                                    }
////                                    Box(
////                                        modifier = modifier.clip(CircleShape)
////                                            .background(color = Color.White)
////                                            .size(56.dp),
////                                        contentAlignment = Alignment.Center,
////                                    ) {
////                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
////                                    }
////                                    Text(text = "56", fontSize = 16.sp)
////                                    Text(text = "56", fontSize = 16.sp)
////
////                                    Box(
////                                        modifier = modifier.clip(CircleShape)
////                                            .background(color = Color.White)
////                                            .size(56.dp),
////                                        contentAlignment = Alignment.Center,
////                                    ) {
////                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
////                                    }
////
////                                    Box(
////                                        modifier = modifier.clip(CircleShape)
////                                            .background(color = Color.White)
////                                            .size(56.dp),
////                                        contentAlignment = Alignment.Center,
////                                    ) {
////                                        Text(text = "Safe", fontSize = 12.sp, color = Color.Black)
////                                    }
////                                    Text(text = "56", fontSize = 16.sp)
////
////                                }
//                            }
//
//                        }
//
//                        Text(
//                            modifier = modifier.padding(start = 24.dp),
//                            text = "Domains",
//                            fontSize = 24.sp,
//                            fontFamily = fontFamilyResource(MR.fonts.googlesans.medium),
//                            color = Color.White
//                        )
//                        DomainCard(modifier)
//
//
//                    }
//
//                }
//            }
        )
    }



@Composable
fun DomainCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            RoundedImage(
                modifier = modifier.padding(start = 24.dp),
                painter = painterResource(MR.images.whale_logo),
                contentDescription = null
            )
            Spacer(modifier = modifier.width(12.dp))
            Column {
                Text("Site", color = Color.Black)
                Text("Site2", color = Color.Black)
            }
            Spacer(modifier = modifier.weight(1f))
            RoundedImage(
                modifier = modifier.padding(end = 24.dp),
                painter = painterResource(MR.images.whale_logo),
                contentDescription = null
            )
        }
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
                fontSize = 32.sp,
                fontFamily = fontFamilyResource(MR.fonts.hiraginosans.medium)
            )
            Spacer(modifier = modifier.width(12.dp))
            RoundedImage(
                painter = mokoPainterResource(MR.images.whale_logo), contentDescription = null
            )

        }

        Row {
            RoundedImage(
                painter = mokoPainterResource(MR.images.whale_logo), contentDescription = null
            )
            Spacer(modifier = modifier.width(12.dp))
            Text(
                text = "Your Life",
                color = colorResource(MR.colors.white),
                fontSize = 32.sp,
                fontFamily = fontFamilyResource(MR.fonts.hiraginosans.medium)
            )

        }
        Row {
            Text(
                text = "Safe",
                color = colorResource(MR.colors.white),
                fontSize = 32.sp,
                fontFamily = fontFamilyResource(MR.fonts.hiraginosans.medium)
            )
            Spacer(modifier = modifier.width(12.dp))
            RoundedImage(
                painter = mokoPainterResource(MR.images.whale_logo), contentDescription = null
            )

        }
    }
}

@Composable
fun Boxes(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val text1 = measurables[0]
        val box1 = measurables[1]
        val box2 = measurables[2]
        val text2 = measurables[3]
        val text3 = measurables[4]
        val box3 = measurables[5]
        val box4 = measurables[6]
        val text4 = measurables[7]

        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )
        val text1Placeable = text1.measure(looseConstraints)
        val box1Placeable = box1.measure(looseConstraints)
        val box2Placeable = box2.measure(looseConstraints)
        val text2Placeable = text2.measure(looseConstraints)
        val text3Placeable = text3.measure(looseConstraints)
        val box3Placeable = box3.measure(looseConstraints)
        val box4Placeable = box4.measure(looseConstraints)
        val text4Placeable = text4.measure(looseConstraints)


        layout(
            width = constraints.maxWidth, height = constraints.maxHeight
        ) {
            text1Placeable.placeRelative(0, box1Placeable.height / 4)
            box1Placeable.placeRelative(text1Placeable.width + 16, 0)
            box2Placeable.placeRelative(
                text2Placeable.width + 16, box1Placeable.height - box2Placeable.height / 4
            )
            text2Placeable.placeRelative(
                text1Placeable.width + box1Placeable.width + 32, box2Placeable.height
            )
            text3Placeable.placeRelative(
                0, ((box1Placeable.height / 3) + (box2Placeable.height / 2) + box3Placeable.height)
            )
            box3Placeable.placeRelative(
                text3Placeable.width + 16, box1Placeable.height + box2Placeable.height / 2
            )
            box4Placeable.placeRelative(
                text1Placeable.width + 16,
                box1Placeable.height + box2Placeable.height + box3Placeable.height / 4
            )
            text4Placeable.placeRelative(
                text1Placeable.width + box1Placeable.width + 32,
                box3Placeable.height + box2Placeable.height + (box3Placeable.height / 2)
            )
        }
    }
}




@Composable
fun CreateBoxes(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val box1 = measurables[0]
        val box2 = measurables[1]
        val box3 = measurables[2]


        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )

        val box1Placeable = box1.measure(looseConstraints)
        val box2Placeable = box2.measure(looseConstraints)
        val box3Placeable = box3.measure(looseConstraints)


        layout(
            width = constraints.maxWidth, height = constraints.maxHeight
        ) {
            box1Placeable.placeRelative(constraints.maxWidth / 4, box2Placeable.height / 4)
            box2Placeable.placeRelative(
                constraints.maxWidth / 4 + box1Placeable.width - 12, 0
            )
            box3Placeable.placeRelative(
                (constraints.maxWidth / 4) + (box2Placeable.width + box1Placeable.width) - 22,
                box2Placeable.height / 4
            )

        }
    }
}
