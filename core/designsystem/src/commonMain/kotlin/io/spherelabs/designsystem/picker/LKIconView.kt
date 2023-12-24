package io.spherelabs.designsystem.picker

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
internal fun LKIconView(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
) {
  Box(
      modifier
          .size(itemSizeDp)
          .clickable(
              onClick = onClick,
          ),
      contentAlignment = Alignment.Center,
  ) {
    Image(
        imageVector = icon,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier =
            modifier
                .size(tickSize)
                .border(
                    width = if (selected) 1.dp else 0.dp,
                    color = if (selected) Color.Black else Color.Transparent,
                    shape = if (selected) RoundedCornerShape(6.dp) else RoundedCornerShape(0.dp),
                ),
    )
  }
}

private val itemSizeDp = 55.dp
private val tickSize = 35.dp
