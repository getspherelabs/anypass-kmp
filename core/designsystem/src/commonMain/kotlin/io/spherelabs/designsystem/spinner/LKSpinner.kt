package io.spherelabs.designsystem.spinner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun LKSpinner(
  expanded: Boolean,
  modifier: Modifier = Modifier,
  onExpandedChange: (Boolean) -> Unit,
  options: List<String>,
  onOptionChosen: (String) -> Unit,
  current: String
)
