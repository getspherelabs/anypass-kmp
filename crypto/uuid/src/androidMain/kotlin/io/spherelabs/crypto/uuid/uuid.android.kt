package io.spherelabs.crypto.uuid

import java.util.UUID

internal class AndroidUuid(
    private val uuid: UUID,
) : Uuid {

    override var mostSignificantBits: Long = uuid.mostSignificantBits
    override var leastSignificantBits: Long = uuid.leastSignificantBits

    override fun toString(): String {
        return uuid.toString()
    }

    override fun isZero(): Boolean {
        return leastSignificantBits == 0L && mostSignificantBits == 0L
    }

    override fun of(mostSigBits: Long, leastSigBits: Long): Uuid {
        return UUID(mostSigBits, leastSigBits).toAndroidUuid()
    }

    override fun fromString(name: String): Uuid {
        return UUID.fromString(name).toAndroidUuid()
    }
}

internal fun UUID.toAndroidUuid(): Uuid {
    return AndroidUuid(this)
}

actual fun uuid4(): Uuid = AndroidUuid(UUID.randomUUID())
