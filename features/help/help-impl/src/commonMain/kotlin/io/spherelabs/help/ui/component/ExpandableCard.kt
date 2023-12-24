package io.spherelabs.help.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.foundation.color.Jaguar
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(
    question: String,
    answer: String,
) {
  var expandedState by remember { mutableStateOf(ExpandableCardState.Collapsed) }

  val rotationState by
      animateFloatAsState(
          targetValue = expandedState.changeSizeOfCard(),
      )

  Card(
      modifier =
          Modifier.fillMaxWidth()
              .animateContentSize(
                  animationSpec =
                      tween(
                          durationMillis = 300,
                          easing = LinearOutSlowInEasing,
                      ),
              ),
      colors =
          CardDefaults.cardColors(
              containerColor = Jaguar,
          ),
      shape = RoundedCornerShape(16.dp),
      onClick = { expandedState = expandedState.toggle() },
  ) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
    ) {
      Row(
          verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
            modifier = Modifier.weight(6f),
            text = question,
            fontSize = 14.sp,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
        )
        IconButton(
            modifier = Modifier.weight(1f).alpha(ContentAlpha.medium).rotate(rotationState),
            onClick = { expandedState = expandedState.toggle() },
        ) {
          Icon(
              tint = Color.White,
              imageVector = Icons.Default.ArrowDropDown,
              contentDescription = "Drop-Down Arrow",
          )
        }
      }
      if (expandedState == ExpandableCardState.Expanded) {
        Text(
            text = answer,
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.7f),
            fontFamily = GoogleSansFontFamily,
            overflow = TextOverflow.Ellipsis,
        )
      }
    }
  }
}

enum class ExpandableCardState {
  Expanded,
  Collapsed;

  fun toggle(): ExpandableCardState {
    return when (this) {
      Expanded -> Collapsed
      Collapsed -> Expanded
    }
  }

  fun changeSizeOfCard(): Float {
    return when (this) {
      Expanded -> ExpandableCardDefaults.SIZE_OF_EXPANDED
      Collapsed -> ExpandableCardDefaults.SIZE_OF_COLLAPSED
    }
  }
}
