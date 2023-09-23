package io.spherelabs.designsystem.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import io.spherelabs.designsystem.button.LKNumberButton

@Composable
fun LKGridLayout(
    items: List<String>,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
        content = {
            itemsIndexed(items) { _, item ->
                LKNumberButton(value = item, fontFamily = fontFamily) {
                    onValueChanged.invoke(item)
                }
            }
        }
    )
}