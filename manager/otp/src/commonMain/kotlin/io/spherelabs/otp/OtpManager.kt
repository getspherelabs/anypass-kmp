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
        secret: String,
        timestamp: Long,
        configuration: OtpConfiguration,
    ): String

}

class DefaultOtpManager : OtpManager {


    private fun counter(timestamp: Long, configuration: OtpConfiguration): Long {
        val millis = configuration.period.millis
        if (millis <= 0L) {
            return 0
        }
        return floor((timestamp / configuration.period.value).toDouble()).toLong()
    }

    override suspend fun generate(
        secret: String,
        timestamp: Long,
        configuration: OtpConfiguration,
    ): String {

        val message = createMessage(timestamp, configuration = configuration)
        val hash = generateHash(message, secret = secret, configuration = configuration)
        val offset = hash.offset()

        val binary = PlatformBuffer.allocate(4).apply {
            for (i in 0..3) {
                this[i] = hash[i + offset]
            }
        }

        binary[0] = binary[0].and(0x7F)

        val codeInt = convertToCode(binary, configuration)

        return formatCode(codeInt, configuration)
    }


    private fun createMessage(
        timestamp: Long,
        configuration: OtpConfiguration,
    ): PlatformBuffer {
        return PlatformBuffer.allocate(8).apply { this[0] = counter(timestamp, configuration) }
    }

    private fun timeslotStart(timestamp: Long, config: OtpConfiguration): Long {
        val counter = counter(timestamp, config)
        val timeStepMillis = config.period.millis.toDouble()
        return (counter * timeStepMillis).toLong()
    }

    private fun createHmacInstance(secret: String, configuration: OtpConfiguration): Hmac {
        val newSecret: ByteArray = secret.decodeBase32ToByteArray()
        return when (configuration.algorithmType) {
            AlgorithmType.SHA1 -> HmacSHA1(newSecret)
            AlgorithmType.SHA256 -> HmacSHA256(newSecret)
            AlgorithmType.SHA512 -> HmacSHA512(newSecret)
        }
    }

    private fun generateHash(
        message: PlatformBuffer,
        configuration: OtpConfiguration,
        secret: String,
    ): ByteArray {
        val hmac = createHmacInstance(secret, configuration)
        return hmac.doFinal(message.readByteArray(8))
    }


    private fun convertToCode(binary: PlatformBuffer, configuration: OtpConfiguration): Int {
        val digits = configuration.digits.number
        return binary.readInt().rem(10.0.pow(digits).toInt())
    }

    private fun formatCode(value: Int, configuration: OtpConfiguration): String {
        val digits = configuration.digits.number
        return value.toString().padStart(digits, padChar = '0')
    }
}

internal fun ByteArray.offset(): Int {
    return this.last().and(0x0F).toInt()
}

internal fun String.decodeBase32ToByteArray(): ByteArray =
    encodeToByteArray().decodeToByteArray(Base32.Default)
