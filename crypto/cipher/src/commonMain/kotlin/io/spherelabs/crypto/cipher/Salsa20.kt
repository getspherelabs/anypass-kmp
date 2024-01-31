package io.spherelabs.crypto.cipher

/**
 * Implementation of Daniel J. Bernstein's Salsa20 stream cipher, Snuffle 2005
 */

private const val DefaultRounds = 20
private const val StateSize = 16 // 16, 32 bit ints = 64 bytes

private val TauSigma = littleEndianToInt(
    bs = ("expand 16-byte k" + "expand 32-byte k").encodeToByteArray(),
    off = 0,
    count = 8,
)

internal open class Salsa20(protected val rounds: Int = DefaultRounds) {
    open val algorithmName = "Salsa20"

    /*
     * Internal counter
     */
    private var cW0 = 0
    private var cW1 = 0
    private var cW2 = 0

    private var index = 0
    private var initialised = false

    private val keyStream = ByteArray(StateSize * 4) // expanded state, 64 bytes

    protected var engineState = IntArray(StateSize) // state

    protected var x = IntArray(StateSize) // internal buffer

    init {
        require(rounds > 0 && rounds and 1 == 0) {
            "'rounds' must be a positive, even number"
        }
    }

    fun init(key: ByteArray?, iv: ByteArray) {
        if (key == null) {
            check(initialised) {
                "$algorithmName KeyParameter can not be null for first initialisation"
            }
            setKey(null, iv)
        } else {
            setKey(key, iv)
        }
        reset()
        initialised = true
    }

    fun getBytes(numberOfBytes: Int): ByteArray {
        val output = ByteArray(numberOfBytes)

        for (i in 0 until numberOfBytes) {
            output[i] = keyStream[index]
            index = (index + 1) % 64
            if (index == 0) {
                advanceCounter()
                generateKeyStream(keyStream)
            }
        }
        return output
    }

    fun processBytes(input: ByteArray): ByteArray {
        val output = ByteArray(input.size)
        processBytes(input, 0, input.size, output, 0)
        return output
    }

    fun processBytes(
        input: ByteArray,
        inputOffset: Int,
        length: Int,
        output: ByteArray,
        roundsOffset: Int,
    ): Int {
        check(initialised) { "$algorithmName not initialised" }

        if (inputOffset + length > input.size) {
            throw Exception("Input buffer too short")
        }
        if (roundsOffset + length > output.size) {
            throw Exception("Output buffer too short")
        }
        if (limitExceeded(length)) {
            throw Exception("2^70 byte limit per IV would be exceeded; Change IV")
        }
        for (i in 0 until length) {
            output[i + roundsOffset] = (keyStream[index] xor input[i + inputOffset])
            index = (index + 1) % 64
            if (index == 0) {
                advanceCounter()
                generateKeyStream(keyStream)
            }
        }
        return length
    }

    fun skip(numberOfBytes: Long): Long {
        if (numberOfBytes >= 0) {
            var remaining = numberOfBytes
            if (remaining >= 64) {
                val count = remaining / 64
                advanceCounter(count)
                remaining -= count * 64
            }
            val oldIndex = index
            index = (index + remaining.toInt()) % 64
            if (index < oldIndex) {
                advanceCounter()
            }
        } else {
            var remaining = -numberOfBytes
            if (remaining >= 64) {
                val count = remaining / 64
                retreatCounter(count)
                remaining -= count * 64
            }
            for (i in 0 until remaining) {
                if (index == 0) {
                    retreatCounter()
                }
                index = (index - 1) % 64
            }
        }
        generateKeyStream(keyStream)
        return numberOfBytes
    }

    fun seekTo(position: Long): Long {
        reset()
        return skip(position)
    }

    fun getPosition(): Long = getCounter() * 64 + index

    fun reset() {
        index = 0
        resetLimitCounter()
        resetCounter()
        generateKeyStream(keyStream)
    }

    protected fun packTauOrSigma(keyLength: Int, state: IntArray) {
        val tsOff = (keyLength - 16) / 4
        state[0] = TauSigma[tsOff]
        state[1] = TauSigma[tsOff + 1]
        state[2] = TauSigma[tsOff + 2]
        state[3] = TauSigma[tsOff + 3]
    }

    protected open fun advanceCounter(diff: Long) {
        val hi = (diff ushr 32).toInt()
        val lo = diff.toInt()
        if (hi > 0) {
            engineState[9] += hi
        }
        val oldState = engineState[8]
        engineState[8] += lo
        if (oldState != 0 && engineState[8] < oldState) {
            engineState[9]++
        }
    }

    protected open fun advanceCounter() {
        if (++engineState[8] == 0) {
            ++engineState[9]
        }
    }

    protected open fun retreatCounter(diff: Long) {
        val hi = (diff ushr 32).toInt()
        val lo = diff.toInt()
        if (hi != 0) {
            if (engineState[9] and 0xffffffffL.toInt() >= hi and 0xffffffffL.toInt()) {
                engineState[9] -= hi
            } else {
                throw IllegalStateException("attempt to reduce counter past zero.")
            }
        }
        if (engineState[8] and 0xffffffffL.toInt() >= lo and 0xffffffffL.toInt()) {
            engineState[8] -= lo
        } else {
            if (engineState[9] != 0) {
                --engineState[9]
                engineState[8] -= lo
            } else {
                throw IllegalStateException("Attempt to reduce counter past zero.")
            }
        }
    }

    protected open fun retreatCounter() {
        check(engineState[8] != 0 || engineState[9] != 0) {
            "Attempt to reduce counter past zero."
        }
        if (--engineState[8] == -1) {
            --engineState[9]
        }
    }

    protected open fun getCounter(): Long {
        return engineState[9].toLong() shl 32 or ((engineState[8] and 0xffffffffL.toInt()).toLong())
    }

    protected open fun resetCounter() {
        engineState[9] = 0
        engineState[8] = engineState[9]
    }

    protected open fun setKey(key: ByteArray?, iv: ByteArray) {
        if (key != null) {
            require(key.size == 16 || key.size == 32) {
                "$algorithmName requires 128 bit or 256 bit key"
            }
            val tauSigmaOffset = (key.size - 16) / 4
            engineState[0] = TauSigma[tauSigmaOffset]
            engineState[5] = TauSigma[tauSigmaOffset + 1]
            engineState[10] = TauSigma[tauSigmaOffset + 2]
            engineState[15] = TauSigma[tauSigmaOffset + 3]

            // Key
            littleEndianToInt(key, 0, engineState, 1, 4)
            littleEndianToInt(key, key.size - 16, engineState, 11, 4)
        }

        // IV
        littleEndianToInt(iv, 0, engineState, 6, 2)
    }

    protected open fun generateKeyStream(output: ByteArray) {
        salsaCore(rounds, engineState, x)
        intToLittleEndian(x, output, 0)
    }

    private fun resetLimitCounter() {
        cW0 = 0
        cW1 = 0
        cW2 = 0
    }

    private fun limitExceeded(): Boolean {
        if (++cW0 == 0) {
            if (++cW1 == 0) {
                return ++cW2 and 0x20 != 0 // 2^(32 + 32 + 6)
            }
        }
        return false
    }

    /*
     * This relies on the fact len will always be positive.
     */
    private fun limitExceeded(length: Int): Boolean {
        cW0 += length
        if (cW0 in 0 until length) {
            if (++cW1 == 0) {
                return ++cW2 and 0x20 != 0 // 2^(32 + 32 + 6)
            }
        }
        return false
    }

    private fun salsaCore(rounds: Int, input: IntArray, x: IntArray) {
        require(input.size == 16)
        require(x.size == 16)
        require(rounds % 2 == 0) { "Number of rounds must be even" }

        var i = rounds
        var x00 = input[0]
        var x01 = input[1]
        var x02 = input[2]
        var x03 = input[3]
        var x04 = input[4]
        var x05 = input[5]
        var x06 = input[6]
        var x07 = input[7]
        var x08 = input[8]
        var x09 = input[9]
        var x10 = input[10]
        var x11 = input[11]
        var x12 = input[12]
        var x13 = input[13]
        var x14 = input[14]
        var x15 = input[15]

        while (i > 0) {
            x04 = x04 xor x00 + x12 leftRotate (7)
            x08 = x08 xor x04 + x00 leftRotate 9
            x12 = x12 xor x08 + x04 leftRotate 13
            x00 = x00 xor x12 + x08 leftRotate 18
            x09 = x09 xor x05 + x01 leftRotate 7
            x13 = x13 xor x09 + x05 leftRotate 9
            x01 = x01 xor x13 + x09 leftRotate 13
            x05 = x05 xor x01 + x13 leftRotate 18
            x14 = x14 xor x10 + x06 leftRotate 7
            x02 = x02 xor x14 + x10 leftRotate 9
            x06 = x06 xor x02 + x14 leftRotate 13
            x10 = x10 xor x06 + x02 leftRotate 18
            x03 = x03 xor x15 + x11 leftRotate 7
            x07 = x07 xor x03 + x15 leftRotate 9
            x11 = x11 xor x07 + x03 leftRotate 13
            x15 = x15 xor x11 + x07 leftRotate 18
            x01 = x01 xor x00 + x03 leftRotate 7
            x02 = x02 xor x01 + x00 leftRotate 9
            x03 = x03 xor x02 + x01 leftRotate 13
            x00 = x00 xor x03 + x02 leftRotate 18
            x06 = x06 xor x05 + x04 leftRotate 7
            x07 = x07 xor x06 + x05 leftRotate 9
            x04 = x04 xor x07 + x06 leftRotate 13
            x05 = x05 xor x04 + x07 leftRotate 18
            x11 = x11 xor x10 + x09 leftRotate 7
            x08 = x08 xor x11 + x10 leftRotate 9
            x09 = x09 xor x08 + x11 leftRotate 13
            x10 = x10 xor x09 + x08 leftRotate 18
            x12 = x12 xor x15 + x14 leftRotate 7
            x13 = x13 xor x12 + x15 leftRotate 9
            x14 = x14 xor x13 + x12 leftRotate 13
            x15 = x15 xor x14 + x13 leftRotate 18
            i -= 2
        }
        x[0] = x00 + input[0]
        x[1] = x01 + input[1]
        x[2] = x02 + input[2]
        x[3] = x03 + input[3]
        x[4] = x04 + input[4]
        x[5] = x05 + input[5]
        x[6] = x06 + input[6]
        x[7] = x07 + input[7]
        x[8] = x08 + input[8]
        x[9] = x09 + input[9]
        x[10] = x10 + input[10]
        x[11] = x11 + input[11]
        x[12] = x12 + input[12]
        x[13] = x13 + input[13]
        x[14] = x14 + input[14]
        x[15] = x15 + input[15]
    }
}

internal fun chachaCore(rounds: Int, input: IntArray, x: IntArray) {
    require(input.size == 16)
    require(x.size == 16)
    require(rounds % 2 == 0) { "Number of rounds must be even" }

    var i = rounds
    var x00 = input[0]
    var x01 = input[1]
    var x02 = input[2]
    var x03 = input[3]
    var x04 = input[4]
    var x05 = input[5]
    var x06 = input[6]
    var x07 = input[7]
    var x08 = input[8]
    var x09 = input[9]
    var x10 = input[10]
    var x11 = input[11]
    var x12 = input[12]
    var x13 = input[13]
    var x14 = input[14]
    var x15 = input[15]

    while (i > 0) {
        x00 += x04
        x12 = Integer.rotateLeft(x12 xor x00, 16)
        x08 += x12
        x04 = Integer.rotateLeft(x04 xor x08, 12)
        x00 += x04
        x12 = Integer.rotateLeft(x12 xor x00, 8)
        x08 += x12
        x04 = Integer.rotateLeft(x04 xor x08, 7)
        x01 += x05
        x13 = Integer.rotateLeft(x13 xor x01, 16)
        x09 += x13
        x05 = Integer.rotateLeft(x05 xor x09, 12)
        x01 += x05
        x13 = Integer.rotateLeft(x13 xor x01, 8)
        x09 += x13
        x05 = Integer.rotateLeft(x05 xor x09, 7)
        x02 += x06
        x14 = Integer.rotateLeft(x14 xor x02, 16)
        x10 += x14
        x06 = Integer.rotateLeft(x06 xor x10, 12)
        x02 += x06
        x14 = Integer.rotateLeft(x14 xor x02, 8)
        x10 += x14
        x06 = Integer.rotateLeft(x06 xor x10, 7)
        x03 += x07
        x15 = Integer.rotateLeft(x15 xor x03, 16)
        x11 += x15
        x07 = Integer.rotateLeft(x07 xor x11, 12)
        x03 += x07
        x15 = Integer.rotateLeft(x15 xor x03, 8)
        x11 += x15
        x07 = Integer.rotateLeft(x07 xor x11, 7)
        x00 += x05
        x15 = Integer.rotateLeft(x15 xor x00, 16)
        x10 += x15
        x05 = Integer.rotateLeft(x05 xor x10, 12)
        x00 += x05
        x15 = Integer.rotateLeft(x15 xor x00, 8)
        x10 += x15
        x05 = Integer.rotateLeft(x05 xor x10, 7)
        x01 += x06
        x12 = Integer.rotateLeft(x12 xor x01, 16)
        x11 += x12
        x06 = Integer.rotateLeft(x06 xor x11, 12)
        x01 += x06
        x12 = Integer.rotateLeft(x12 xor x01, 8)
        x11 += x12
        x06 = Integer.rotateLeft(x06 xor x11, 7)
        x02 += x07
        x13 = Integer.rotateLeft(x13 xor x02, 16)
        x08 += x13
        x07 = Integer.rotateLeft(x07 xor x08, 12)
        x02 += x07
        x13 = Integer.rotateLeft(x13 xor x02, 8)
        x08 += x13
        x07 = Integer.rotateLeft(x07 xor x08, 7)
        x03 += x04
        x14 = Integer.rotateLeft(x14 xor x03, 16)
        x09 += x14
        x04 = Integer.rotateLeft(x04 xor x09, 12)
        x03 += x04
        x14 = Integer.rotateLeft(x14 xor x03, 8)
        x09 += x14
        x04 = Integer.rotateLeft(x04 xor x09, 7)
        i -= 2
    }
    x[0] = x00 + input[0]
    x[1] = x01 + input[1]
    x[2] = x02 + input[2]
    x[3] = x03 + input[3]
    x[4] = x04 + input[4]
    x[5] = x05 + input[5]
    x[6] = x06 + input[6]
    x[7] = x07 + input[7]
    x[8] = x08 + input[8]
    x[9] = x09 + input[9]
    x[10] = x10 + input[10]
    x[11] = x11 + input[11]
    x[12] = x12 + input[12]
    x[13] = x13 + input[13]
    x[14] = x14 + input[14]
    x[15] = x15 + input[15]
}
