package io.spherelabs.data.local.website

import android.content.Context
import dev.icerock.moko.resources.FileResource
import io.spherelabs.anypass.local.MR

actual typealias ComposeContext = Context

actual class FileReader(
    private val context: Context,
) {

    actual fun get(): Result<String> {
        return runCatching {
            FileResource(MR.files.list_of_websites.rawResId).readText(context)
        }
    }
}
