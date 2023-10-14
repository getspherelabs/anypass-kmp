package io.spherelabs.otp

import com.ditchoom.buffer.PlatformBuffer
import com.ditchoom.buffer.allocate
import io.matthewnelson.encoding.base32.Base32
import io.matthewnelson.encoding.core.Decoder.Companion.decodeToByteArray
import kotlin.experimental.and
import kotlin.math.floor
import kotlin.math.pow
import org.kotlincrypto.macs.hmac.Hmac
import org.kotlincrypto.macs.hmac.sha1.HmacSHA1
import org.kotlincrypto.macs.hmac.sha2.HmacSHA256
import org.kotlincrypto.macs.hmac.sha2.HmacSHA512

interface OtpManager {
    suspend fun generate(
        count: Long,
    ): String
}

class DefaultOtpManager(
    secret: String,
    private val configuration: OtpConfiguration,
) : OtpManager {

    private val secret: ByteArray = secret.decodeBase32ToByteArray()

    private fun counter(timestamp: Long): Long {
        val millis = configuration.period.millis
        if (millis <= 0L) {
            return 0
        }
        return floor(timestamp.toDouble().div(millis)).toLong()
    }

    override suspend fun generate(count: Long): String {
        val message = createMessage(count)
        val hash = generateHash(message)
        val offset = hash.offset()

        val binary = PlatformBuffer.allocate(4).apply {
            for (i in 0..3) {
                this[i] = hash[i + offset]
            }
        }

        binary[0] = binary[0].and(0x7F)

        val codeInt = convertToCode(binary)

        return formatCode(codeInt)
    }


    fun timeslotLeft(timestamp: Long): Double {
        val diff = timestamp - timeslotStart(timestamp)
        return 1.0 - diff.toDouble() / configuration.period.millis.toDouble()
    }

    private fun createMessage(count: Long): PlatformBuffer {
        return PlatformBuffer.allocate(8).apply { this[0] = counter(count) }
    }

    private fun timeslotStart(timestamp: Long): Long {
        val counter = counter(timestamp)
        val timeStepMillis = configuration.period.millis.toDouble()
        return (counter * timeStepMillis).toLong()
    }

    private fun createHmacInstance(): Hmac {
        return when (configuration.algorithmType) {
            AlgorithmType.SHA1 -> HmacSHA1(secret)
            AlgorithmType.SHA256 -> HmacSHA256(secret)
            AlgorithmType.SHA512 -> HmacSHA512(secret)
        }
    }

    private fun generateHash(message: PlatformBuffer): ByteArray {
        val hmac = createHmacInstance()
        return hmac.doFinal(message.readByteArray(8))
    }


    private fun convertToCode(binary: PlatformBuffer): Int {
        val digits = configuration.digits.number
        return binary.readInt().rem(10.0.pow(digits).toInt())
    }

    private fun formatCode(value: Int): String {
        val digits = configuration.digits.number
        return value.toString().padStart(digits, padChar = '0')
    }
}

internal fun ByteArray.offset(): Int {
    return this.last().and(0x0F).toInt()
}

internal fun String.decodeBase32ToByteArray(): ByteArray =
    encodeToByteArray().decodeToByteArray(Base32.Default)
