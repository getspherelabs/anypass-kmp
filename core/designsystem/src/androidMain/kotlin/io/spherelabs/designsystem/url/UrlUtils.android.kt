package io.spherelabs.designsystem.url

import android.content.Context
import android.content.Intent
import android.net.Uri

actual class BrowserNavigator(
  private val context: Context,
) {
  actual fun openUrl(url: String) {
    val intent =
      Intent().apply {
        action = Intent.ACTION_VIEW
        data = Uri.parse(url)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      }
    context.startActivity(intent)
  }
}
