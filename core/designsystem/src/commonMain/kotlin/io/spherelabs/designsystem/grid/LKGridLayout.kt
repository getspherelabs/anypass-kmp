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
import io.spherelabs.designsystem.button.ClearButton
import io.spherelabs.designsystem.button.FingerPrintButton
import io.spherelabs.designsystem.button.KeypadType
import io.spherelabs.designsystem.button.LKNumberButton

@Composable
fun LKGridLayout(
    items: List<Pair<KeypadType, String>>,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    onValueChanged: (Pair<KeypadType, String>) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth().padding(top = 18.dp),
        content = {
            itemsIndexed(items) { _, (keypadType, itemValue) ->
                when (keypadType) {
                    KeypadType.Number -> {
                        LKNumberButton(
                            value = itemValue,
                            fontFamily = fontFamily,
                        ) {
                            onValueChanged.invoke(keypadType to itemValue)
                        }
                    }

                    KeypadType.FingerPrint -> {
                        FingerPrintButton {
                            onValueChanged.invoke(keypadType to itemValue)
                        }
                    }

                    KeypadType.Clear -> {
                        ClearButton {
                            onValueChanged.invoke(keypadType to itemValue)
                        }
                    }
                }

            }
        },
    )
}
