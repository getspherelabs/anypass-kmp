package io.spherelabs.crypto.tinypass.database.core

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.model.component.KdbxQuery

data class KdbxQuery(
    val credentials: Credentials,
    val header: KdbxOuterHeader,
    val content: KdbxQuery,
    val innerHeader: KdbxInnerHeader,
) {
    companion object {

    }
}
