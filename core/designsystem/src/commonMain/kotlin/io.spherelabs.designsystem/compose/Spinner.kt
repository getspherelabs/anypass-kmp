package io.spherelabs.designsystem.compose

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate


@Composable
expect fun Spinner(expanded: Boolean, onExpandedChange: (Boolean) -> Unit,
                            options: List<String>, onOptionChosen: (String) -> Unit, current: String)


@Composable
fun TrailingIcon(expanded: Boolean) {
    Icon(
        Icons.Filled.ArrowDropDown,
        null,
        Modifier.rotate(if (expanded) 180f else 0f)
    )
}