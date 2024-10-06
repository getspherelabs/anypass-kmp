package io.spherelabs.crypto.kdbx.database.core

import io.spherelabs.crypto.kdbx.database.header.KdbxInnerHeader
import io.spherelabs.crypto.kdbx.database.header.KdbxOuterHeader
import io.spherelabs.crypto.kdbx.database.model.component.KdbxQuery

/**
 * Represents a Kdbx database, encapsulating its configuration, headers, and query capabilities.
 *
 * @property configuration The configuration settings for the Kdbx database,
 * which dictate how the database operates and is structured.
 * @property outerHeader The outer header of the database, containing metadata
 * related to encryption and storage.
 * @property innerHeader The inner header of the database, providing additional
 * metadata and structural information for the database content.
 * @property query The query object that facilitates retrieval, updating,
 * and deletion of entries within the database.
 */

data class KdbxDatabase(
    val configuration: KdbxConfiguration,
    val outerHeader: KdbxOuterHeader,
    val innerHeader: KdbxInnerHeader,
    val query: KdbxQuery,
)
