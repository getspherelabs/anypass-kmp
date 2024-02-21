package io.spherelabs.crypto.tinypass.database.core

import io.spherelabs.crypto.tinypass.database.core.Credentials

data class KdbxConfig(
    val path: String,
    val credentials: Credentials,
)
