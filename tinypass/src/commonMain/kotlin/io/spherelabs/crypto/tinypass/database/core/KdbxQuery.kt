package io.spherelabs.crypto.tinypass.database.core

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.model.component.DatabaseContent

data class KdbxQuery(
    val credentials: Credentials,
    val header: KdbxOuterHeader,
    val content: DatabaseContent,
    val innerHeader: KdbxInnerHeader,
) {
    companion object {

    }
}
