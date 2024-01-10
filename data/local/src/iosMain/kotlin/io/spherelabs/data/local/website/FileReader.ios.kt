package io.spherelabs.data.local.website

import io.spherelabs.anypass.local.MR

actual abstract class ComposeContext

actual class FileReader {
    actual fun get(): String {
        return MR.files.list_of_websites.readText()
    }
}
