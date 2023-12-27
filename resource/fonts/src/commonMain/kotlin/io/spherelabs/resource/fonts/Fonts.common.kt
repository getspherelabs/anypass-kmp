package io.spherelabs.resource.fonts

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
expect fun font(
    resourceId: String,
    weight: FontWeight = FontWeight.Normal,
    style: FontStyle = FontStyle.Normal,
): Font

val GoogleSansFontFamily: FontFamily
  @Composable
  get() =
      FontFamily(
          font(
              resourceId = "googlesans_regular",
              weight = FontWeight.Normal,
          ),
          font(resourceId = "googlesans_medium", weight = FontWeight.Medium),
          font(resourceId = "googlesans_bold", weight = FontWeight.Bold),
      )

val OpenSansFontFamily: FontFamily
  @Composable
  get() =
      FontFamily(
          font(
              resourceId = "opensans_regular",
              weight = FontWeight.Normal,
          ),
          font(resourceId = "opensans_medium", weight = FontWeight.Medium),
          font(resourceId = "opensans_bold", weight = FontWeight.Bold),
      )
