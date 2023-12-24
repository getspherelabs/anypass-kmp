package io.spherelabs.homeimpl.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.painterResource
import io.spherelabs.designsystem.fonts.LocalStrings
import io.spherelabs.designsystem.image.RoundedImage
import io.spherelabs.resource.fonts.GoogleSansFontFamily
import io.spherelabs.resource.images.MR

@Composable
fun HomeHeadline(
    fontSize: TextUnit = 42.sp,
    modifier: Modifier = Modifier,
) {

  val strings = LocalStrings.current

  LazyColumn(
      modifier = modifier.padding(start = 24.dp),
  ) {
    item {
      Row {
        Text(
            text = strings.keep,
            color = Color.White,
            fontSize = fontSize,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = modifier.width(12.dp))
        RoundedImage(
            imageSize = 56,
            painter = painterResource(MR.images.avatar3),
            contentDescription = null,
        )
      }
    }
    item {
      Row {
        RoundedImage(
            imageSize = 56,
            painter = painterResource(MR.images.avatar6),
            contentDescription = null,
        )
        Spacer(modifier = modifier.width(12.dp))
        Text(
            text = strings.yourLife,
            color = Color.White,
            fontSize = fontSize,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Bold,
        )
      }
    }

    item {
      Row {
        Text(
            text = strings.safe,
            color = Color.White,
            fontSize = fontSize,
            fontFamily = GoogleSansFontFamily,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = modifier.width(12.dp))
        RoundedImage(
            imageSize = 56,
            painter = painterResource(MR.images.avatar4),
            contentDescription = null,
        )
      }
    }
  }
}
