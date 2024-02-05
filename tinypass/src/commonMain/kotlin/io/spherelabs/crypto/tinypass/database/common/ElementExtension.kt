package io.spherelabs.crypto.tinypass.database.common

import com.fleeksoft.ksoup.nodes.Element

internal fun Element.selectAsInt(query: String): Int {
    return this.select(query).text().toInt()
}

internal fun Element.selectAsBoolean(query: String): Boolean {
    return this.select(query).text().toBoolean()
}

internal fun Element.selectAsString(query: String): String {
    return this.select(query).text()
}
