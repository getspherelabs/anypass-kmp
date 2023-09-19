package io.spherelabs.lockerkmp.components.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.spherelabs.designsystem.button.LKNumberButton
import io.spherelabs.lockerkmp.MR

@Composable
fun GridLayout(
    items: List<String>,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
        content = {
            itemsIndexed(items) { _, item ->
                LKNumberButton(value = item, fontFamily = fontFamilyResource(MR.fonts.googlesans.medium)) {
                    onValueChanged.invoke(item)
                }
            }
        }
    )
}