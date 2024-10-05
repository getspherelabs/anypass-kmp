package io.spherelabs.crypto.tinypass.database.header

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuidFrom

/**
 * [CipherId] is a unique identifier used to specify the encryption algorithm used to encrypt the database file.
 */

sealed interface C
enum class CipherId(val uuid: Uuid, val ivLength: Int) {
    Aes(
        uuid = uuidFrom("31c1f2e6-bf71-4350-be58-05216afc5aff"),
        ivLength = 16,
    ),
    ChaCha20(
        uuid = uuidFrom("d6038a2b-8b6f-4cb5-a524-339a31dbb59a"),
        ivLength = 12,
    )
}

