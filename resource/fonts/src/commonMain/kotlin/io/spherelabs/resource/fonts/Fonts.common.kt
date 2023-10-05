package io.spherelabs.resource.fonts

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
expect fun font(
  fontName: String,
  resourceId: String,
  weight: FontWeight,
  style: FontStyle = FontStyle.Normal,
): Font

val GoogleSansFontFamily: FontFamily
  @Composable
  get() =
    FontFamily(
      font(
        fontName = "GoogleSans",
        resourceId = "googlesans_regular",
        weight = FontWeight.Normal,
      ),
      font(fontName = "GoogleSans", resourceId = "googlesans_medium", weight = FontWeight.Medium),
      font(fontName = "GoogleSans", resourceId = "googlesans_bold", weight = FontWeight.Bold),
    )

val OpenSansFontFamily: FontFamily
  @Composable
  get() =
    FontFamily(
      font(
        fontName = "OpenSans",
        resourceId = "opensans_regular",
        weight = FontWeight.Normal,
      ),
      font(fontName = "GoogleSans", resourceId = "opensans_medium", weight = FontWeight.Medium),
      font(fontName = "GoogleSans", resourceId = "opensans_bold", weight = FontWeight.Bold),
    )
