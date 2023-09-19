package io.spherelabs.designsystem.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
actual fun Spinner(
    expanded: Boolean, onExpandedChange: (Boolean) -> Unit,
    options: List<String>, onOptionChosen: (String) -> Unit, current: String
) {
    val lightBlue = Color(0xffd8e6ff)

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = onExpandedChange) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value = current,
            onValueChange = {},
            placeholder = {
                Text(text = "Choose a category", color = Color.Black.copy(0.5f))
            },
            trailingIcon = {
                TrailingIcon(expanded)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { onExpandedChange(false) }) {
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
