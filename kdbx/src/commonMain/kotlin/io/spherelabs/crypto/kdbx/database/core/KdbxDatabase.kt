package io.spherelabs.crypto.kdbx.database.core

import io.spherelabs.crypto.kdbx.database.header.KdbxInnerHeader
import io.spherelabs.crypto.kdbx.database.header.KdbxOuterHeader
import io.spherelabs.crypto.kdbx.database.model.component.KdbxQuery


data class KdbxDatabase(
    val configuration: KdbxConfiguration,
    val outerHeader: KdbxOuterHeader,
    val innerHeader: KdbxInnerHeader,
    val query: KdbxQuery,
)
