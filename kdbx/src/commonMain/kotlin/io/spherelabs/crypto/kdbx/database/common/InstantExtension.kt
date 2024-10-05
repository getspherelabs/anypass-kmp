package io.spherelabs.crypto.kdbx.database.common

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

val Instant.Companion.Default: Instant
    get() = Clock.System.now()
