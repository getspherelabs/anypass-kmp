package io.spherelabs.designsystem.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LKNewItemButton(
    contentText: String,
    contentFontFamily: FontFamily,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.White,
    iconColor: Color = Color.White,
    onNewItemClick: () -> Unit,
) {
  Button(
      modifier = modifier,
      colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
      shape = RoundedCornerShape(16.dp),
      onClick = { onNewItemClick.invoke() },
  ) {
    Text(
        text = contentText,
        color = contentColor,
        fontSize = 14.sp,
        fontFamily = contentFontFamily,
        fontWeight = FontWeight.Medium,
    )

    Icon(
        modifier = modifier.size(14.dp),
        imageVector = Icons.Default.Add,
        contentDescription = null,
        tint = iconColor,
    )
  }
}

@Composable
fun LKBackButton(
    text: String,
    backgroundColor: Color = Color.Yellow,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
) {
  Box(
      modifier =
          modifier
              .width(75.dp)
              .height(32.dp)
              .clip(RoundedCornerShape(24.dp))
              .clickable { onButtonClick.invoke() }
              .background(color = backgroundColor),
  ) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
      Icon(
          modifier = modifier.size(12.dp),
          tint = Color.White,
          imageVector = Icons.Outlined.ArrowBackIos,
          contentDescription = null,
      )
      Spacer(modifier = modifier.width(4.dp))
      Text(text, fontSize = 12.sp, color = Color.White)
    }
  }
}

@Composable
fun LKUseButton(
    text: String,
    backgroundColor: Color = Color.Yellow,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
) {
  Box(
      modifier =
          modifier
              .width(75.dp)
              .height(32.dp)
              .clip(RoundedCornerShape(24.dp))
              .clickable { onButtonClick.invoke() }
              .background(color = backgroundColor),
  ) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
      Text(text, fontSize = 12.sp, color = Color.White)
      Spacer(modifier = modifier.width(4.dp))
      Icon(
          modifier = modifier.size(12.dp),
          tint = Color.White,
          imageVector = Icons.Outlined.ArrowForwardIos,
          contentDescription = null,
      )
    }
  }
}

@Composable
fun LKNumberButton(
    value: String,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
  Box(
      modifier = modifier.size(55.dp).clickable { onClick.invoke() },
      contentAlignment = Alignment.Center,
  ) {
    Text(
        text = value,
        color = Color.White,
        style = MaterialTheme.typography.h4,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
    )
  }
}

@Composable
fun FingerPrintButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.size(65.dp).clickable { onClick.invoke() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Fingerprint,
            tint = Color.White,
            contentDescription = "Finger print button"
        )
    }
}

@Composable
fun ClearButton(
    modifier: Modifier = Modifier,
    onClearClick: () -> Unit
) {
    Box(
        modifier = modifier.size(65.dp).clickable { onClearClick.invoke() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Clear,
            tint = Color.White,
            contentDescription = "Clear button"
        )
    }
}

enum class KeypadType {
    Number,
    FingerPrint,
    Clear;
}
