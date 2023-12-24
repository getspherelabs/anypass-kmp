@file:OptIn(ExperimentalForeignApi::class)

package io.spherelabs.resource.fonts

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSBundle
import platform.Foundation.NSData
import platform.Foundation.NSFileManager
import platform.posix.memcpy

private val cache: MutableMap<String, Font> = mutableMapOf()

@Composable
actual fun font(resourceId: String, weight: FontWeight, style: FontStyle): Font {
  return cache.getOrPut(resourceId) {
    androidx.compose.ui.text.platform.Font(
        identity = resourceId,
        data = readBundleFile("$resourceId.ttf"),
        weight = weight,
        style = style,
    )
  }
}

private fun readBundleFile(path: String): ByteArray {
  val fileManager = NSFileManager.defaultManager()
  val nsContent: NSData? = fileManager.contentsAtPath(findResourcePath(path))
  if (nsContent != null) {
    return nsContent.toByteArray()
  } else {
    error("File $path not found in Bundle")
  }
}

private fun findResourcePath(path: String): String {
  return NSBundle.mainBundle.resourcePath + "/" + path
}

internal fun NSData.toByteArray(): ByteArray {
  val byteArray = ByteArray(this.length.toInt())
  byteArray.usePinned { memcpy(it.addressOf(0), this.bytes, this.length) }
  return byteArray
}
