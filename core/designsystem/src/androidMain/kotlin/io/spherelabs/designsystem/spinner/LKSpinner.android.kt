package io.spherelabs.designsystem.spinner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import io.spherelabs.foundation.color.Jaguar

@OptIn(ExperimentalMaterialApi::class)
@Composable
actual fun LKSpinner(
    expanded: Boolean,
    modifier: Modifier,
    onExpandedChange: (Boolean) -> Unit,
    options: List<String>,
    onOptionChosen: (String) -> Unit,
    current: String,
) {

    ExposedDropdownMenuBox(
        modifier =
        modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Jaguar),
        expanded = expanded,
        onExpandedChange = onExpandedChange,
    ) {
        TextField(
            modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
            readOnly = true,
            value = current,
            onValueChange = {},
            placeholder = { Text(text = "Choose a category", color = Color.White.copy(0.5f)) },
            trailingIcon = { LKDropdownDefaults.TrailingIcon(expanded) },
            colors =
            TextFieldDefaults.textFieldColors(
                backgroundColor = Jaguar,
                cursorColor = Color.White,
                disabledLabelColor = Jaguar,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { onExpandedChange(false) }) {
            options.forEach {
                DropdownMenuItem(
                    onClick = {
                        onOptionChosen(it)
                        onExpandedChange(false)
                    },
                ) {
                    Text(it)
                }
            }
        }
    }
}
