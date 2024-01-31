package io.spherelabs.crypto.uuid

import java.util.UUID

internal class AndroidUuid(
    private val uuid: UUID,
) : Uuid {

    override val mostSignificantBits: Long = uuid.mostSignificantBits
    override val leastSignificantBits: Long = uuid.leastSignificantBits

    override fun toString(): String {
        return uuid.toString()
    }

    override fun isZero(): Boolean {
        return leastSignificantBits == 0L && mostSignificantBits == 0L
    }

    override fun of(mostSigBits: Long, leastSigBits: Long): Uuid {
        return UUID(mostSigBits, leastSigBits).toAndroidUuid()
    }
}

internal fun UUID.toAndroidUuid(): Uuid {
    return AndroidUuid(this)
}

actual fun uuid4(): Uuid = AndroidUuid(UUID.randomUUID())
