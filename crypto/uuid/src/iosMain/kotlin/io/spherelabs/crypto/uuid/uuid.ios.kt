package io.spherelabs.crypto.uuid

internal class IosUuid : Uuid {
    override val mostSignificantBits: Long
        get() = TODO("Not yet implemented")
    override val leastSignificantBits: Long
        get() = TODO("Not yet implemented")

    override fun toString(): String {
        TODO("Not yet implemented")
    }

    override fun isZero(): Boolean {
        TODO("Not yet implemented")
    }

    override fun of(mostSigBits: Long, leastSigBits: Long): Uuid {
        TODO("Not yet implemented")
    }
}


actual fun uuid4(): Uuid = IosUuid()
