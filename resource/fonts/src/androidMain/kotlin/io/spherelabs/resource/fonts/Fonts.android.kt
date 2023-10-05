package io.spherelabs.resource.fonts

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

private val idCache = mutableMapOf<String, Int>()

@Composable
actual fun font(fontName: String, resourceId: String, weight: FontWeight, style: FontStyle): Font {
  val context = LocalContext.current
  val id = idCache.getOrPut(resourceId) { context.getFont(resourceId) }
  return Font(resId = id, weight = weight, style = style)
}

internal fun Context.getFont(resourceId: String): Int {
  return this.resources.getIdentifier(resourceId, "font", this.packageName)
}
