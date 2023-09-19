package io.spherelabs.designsystem.spinner

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate


@Composable
expect fun LKSpinner(
    expanded: Boolean, modifier: Modifier = Modifier, onExpandedChange: (Boolean) -> Unit,
    options: List<String>, onOptionChosen: (String) -> Unit, current: String
)


