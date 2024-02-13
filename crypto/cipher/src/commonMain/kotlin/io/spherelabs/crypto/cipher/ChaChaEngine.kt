package io.spherelabs.crypto.cipher


/**
 * Implementation of Daniel J. Bernstein's ChaCha stream cipher.
 */

private const val DefaultRounds = 20

internal class ChaChaEngine(rounds: Int = DefaultRounds) : Salsa20Engine(rounds) {
    override val algorithmName = "ChaCha"

    override fun advanceCounter(diff: Long) {
        val hi = (diff ushr 32).toInt()
        val lo = diff.toInt()
        if (hi > 0) {
            engineState[13] += hi
        }
        val oldState = engineState[12]
        engineState[12] += lo
        if (oldState != 0 && engineState[12] < oldState) {
            engineState[13]++
        }
    }

    override fun advanceCounter() {
        if (++engineState[12] == 0) {
            ++engineState[13]
        }
    }

    override fun retreatCounter(diff: Long) {
        val hi = (diff ushr 32).toInt()
        val lo = diff.toInt()
        if (hi != 0) {
            if (engineState[13] and 0xffffffffL.toInt() >= hi and 0xffffffffL.toInt()) {
                engineState[13] -= hi
            } else {
                throw IllegalStateException("Attempt to reduce counter past zero.")
            }
        }
        if (engineState[12] and 0xffffffffL.toInt() >= lo and 0xffffffffL.toInt()) {
            engineState[12] -= lo
        } else {
            if (engineState[13] != 0) {
                --engineState[13]
                engineState[12] -= lo
            } else {
                throw IllegalStateException("Attempt to reduce counter past zero.")
            }
        }
    }

    override fun retreatCounter() {
        check(engineState[12] != 0 || engineState[13] != 0) {
            "Attempt to reduce counter past zero."
        }
        if (--engineState[12] == -1) {
            --engineState[13]
        }
    }

    override fun getCounter(): Long {
        return engineState[13].toLong() shl 32 or ((engineState[12] and 0xffffffffL.toInt()).toLong())
    }

    override fun resetCounter() {
        engineState[13] = 0
        engineState[12] = engineState[13]
    }

    override fun setKey(key: ByteArray?, iv: ByteArray) {
        if (key != null) {
            require(key.size == 16 || key.size == 32) {
                "$algorithmName requires 128 bit or 256 bit key"
            }
            packTauOrSigma(key.size, engineState)

            // Key
            littleEndianToInt(key, 0, engineState, 4, 4)
            littleEndianToInt(key, key.size - 16, engineState, 8, 4)
        }

        // IV
        littleEndianToInt(iv, 0, engineState, 14, 2)
    }

    override fun generateKeyStream(output: ByteArray) {
        chachaCore(rounds, engineState, x)
        intToLittleEndian(x, output, 0)
    }
}
