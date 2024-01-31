package io.spherelabs.crypto.cipher


/**
 * Implementation of Daniel J. Bernstein's ChaCha stream cipher.
 */

internal class ChaCha7539 : Salsa20() {
    override val algorithmName = "ChaCha7539"

    override fun advanceCounter(diff: Long) {
        val hi = (diff ushr 32).toInt()
        val lo = diff.toInt()
        check(hi <= 0) {
            "Attempt to increase counter past 2^32."
        }

        val oldState = engineState[12]
        engineState[12] += lo
        check(!(oldState != 0 && engineState[12] < oldState)) {
            "Attempt to increase counter past 2^32."
        }
    }

    override fun advanceCounter() {
        check(++engineState[12] != 0) { "Attempt to increase counter past 2^32." }
    }

    override fun retreatCounter(diff: Long) {
        val hi = (diff ushr 32).toInt()
        val lo = diff.toInt()
        check(hi == 0) { "Attempt to reduce counter past zero." }

        if (engineState[12] and 0xffffffffL.toInt() >= lo and 0xffffffffL.toInt()) {
            engineState[12] -= lo
        } else {
            throw IllegalStateException("Attempt to reduce counter past zero.")
        }
    }

    override fun retreatCounter() {
        check(engineState[12] != 0) { "Attempt to reduce counter past zero." }
        --engineState[12]
    }

    override fun getCounter(): Long {
        return (engineState[12] and 0xffffffffL.toInt()).toLong()
    }

    override fun resetCounter() {
        engineState[12] = 0
    }

    override fun setKey(key: ByteArray?, iv: ByteArray) {
        if (key != null) {
            require(key.size == 32) {
                "$algorithmName requires 256 bit key"
            }
            packTauOrSigma(key.size, engineState)

            // Key
            littleEndianToInt(key, 0, engineState, 4, 8)
        }

        // IV
        littleEndianToInt(iv, 0, engineState, 13, 3)
    }

    override fun generateKeyStream(output: ByteArray) {
        chachaCore(rounds, engineState, x)
        intToLittleEndian(x, output, 0)
    }
}
