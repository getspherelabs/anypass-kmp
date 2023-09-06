package io.spherelabs.lockerkmp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.colorResource
import io.spherelabs.lockerkmp.MR

@Composable
fun Tags(
    list: List<String>,
    selectedTag: String,
    modifier: Modifier = Modifier,
    onTagClicked: (String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        modifier = modifier.padding(start = 24.dp, top = 16.dp)
    ) {
        items(list) {
            Tag(it, isSelected = it == selectedTag) { tag ->
                onTagClicked.invoke(tag)
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Tag(
    name: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onItemSelected: (String) -> Unit
) {
    Surface(modifier = modifier.background(Color.Transparent).height(36.dp),
        shape = RoundedCornerShape(20.dp),
        border = if (isSelected) BorderStroke(
            width = 0.dp,
            color = colorResource(MR.colors.black)
        ) else {
            BorderStroke(
                width = 1.dp,
                color = colorResource(MR.colors.black)
            )
        },
        color = if (isSelected) colorResource(MR.colors.black) else Color.Transparent,
        onClick = { onItemSelected(name) }) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                name,
                color = if (isSelected) colorResource(MR.colors.white) else colorResource(MR.colors.black),
                modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                    .background(Color.Transparent),
                textAlign = TextAlign.Center
            )
        }
    }
}
