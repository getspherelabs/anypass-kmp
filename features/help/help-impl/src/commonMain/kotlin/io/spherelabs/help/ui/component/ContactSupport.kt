package io.spherelabs.help.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.spherelabs.foundation.color.LavenderBlue
import io.spherelabs.resource.fonts.GoogleSansFontFamily

@Composable
fun ContactSupport(
    modifier: Modifier = Modifier,
    title: String,
    email: String,
) {
  Card(
      colors =
          CardDefaults.cardColors(
              containerColor = LavenderBlue.copy(alpha = 0.4f),
          ),
      modifier =
          modifier.height(95.dp).fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 8.dp),
      shape = RoundedCornerShape(16.dp),
  ) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
          modifier = modifier.padding(start = 8.dp).size(48.dp).clip(CircleShape),
          imageVector = Icons.Filled.Email,
          tint = Color.White,
          contentDescription = null,
      )

      Column(modifier = modifier) {
        Text(
            modifier = modifier.padding(start = 24.dp),
            text = title,
            fontSize = 14.sp,
            fontFamily = GoogleSansFontFamily,
            color = Color.White.copy(alpha = 0.5f),
            textAlign = TextAlign.Start,
        )

        Text(
            modifier = modifier.padding(top = 4.dp, start = 24.dp),
            text = email,
            fontSize = 14.sp,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White.copy(alpha = 0.9f),
            textAlign = TextAlign.Start,
        )
      }
    }
  }
}
