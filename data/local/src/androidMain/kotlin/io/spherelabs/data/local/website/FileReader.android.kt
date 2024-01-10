package io.spherelabs.data.local.website

import android.content.Context
import io.spherelabs.anypass.local.MR

actual typealias ComposeContext = Context

actual class FileReader(
    private val context: Context,
) {

    actual fun get(): String {
        return MR.files.list_of_websites.readText(context)
    }
}
