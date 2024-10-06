package io.spherelabs.crypto.kdbx.database.header

import io.spherelabs.anycrypto.securerandom.SecureRandom
import okio.ByteString
import okio.ByteString.Companion.toByteString

/**
 * https://palant.info/2023/03/29/documenting-keepass-kdbx4-file-format/
 *
 * 03 d9 a2 9a   67 fb 4b b5   01 00   04 00
 * ┗━━━━━━━━━┛   ┗━━━━━━━━━┛   ┗━━━┛   ┗━━━┛
 * Signature1    Signature2    Version: 4.1
 *
 * 02   10 00 00 00   31 c1 f2 e6 bf 71 43 50 be 58 05 21 6a fc 5a ff
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * CipherID: AES256-EBC
 *
 * 03   04 00 00 00   01 00 00 00
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * Compression: Gzip
 *
 * 04   20 00 00 00   12 34 56 78 12 34 56 78 12 34 56 78 12 34 56 78
 * 12 34 56 78 12 34 56 78 12 34 56 78 12 34 56 78
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * MainSeed (random)
 *
 * 07   10 00 00 00   12 34 56 78 12 34 56 78 12 34 56 78 12 34 56 78
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * EncryptionIV (random)
 *
 * 0b   8b 00 00 00          00 01
 * ┗━━━━━━━━━━━━━━┛          ┗━━━┛
 * KdfParameters starting    FieldStorage version: 0x100
 *
 * 42   05 00 00 00   24 55 55 49 44   10 00 00 00
 * ef 63 6d df 8c 29 44 4b 91 f7 a9 a4 03 e3 0a 0c
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * bytes[] entry, Key $UUID, Value Argon2d
 *
 * 05   01 00 00 00   49   08 00 00 00   02 00 00 00 00 00 00 00
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * UInt64 entry, Key I, Value 2
 *
 * 05   01 00 00 00   4d   08 00 00 00   00 00 00 40 00 00 00 00
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * UInt64 entry, Key M, Value 0x40000000 (1 GB)
 *
 * 04   01 00 00 00   50   04 00 00 00   08 00 00 00
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * UInt32 entry, Key P, Value 8
 *
 * 42   01 00 00 00   53   20 00 00 00
 * 12 34 56 78 12 34 56 78 12 34 56 78 12 34 56 78
 * 12 34 56 78 12 34 56 78 12 34 56 78 12 34 56 78
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * bytes[] entry, Key S, Value random
 *
 * 04   01 00 00 00   56   04 00 00 00   13 00 00 00
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * UInt32 entry, Key V, Value 0x13
 *
 * 00
 * ┗┛
 * End of VariantMap
 *
 * 00    04 00 00 00   0d 0a 0d 0a
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * EndOfHeader
 */

/**
 * Represents the outer header for KDBX4 file format.
 * This header contains crucial information like encryption parameters, KDF settings, and custom metadata.
 *
 * @property option Configuration options for the outer header, typically specifying encryption-related behaviors.
 * @property seed A cryptographic seed used for key derivation.
 * @property encryptionIV Initialization vector used for encryption.
 * @property kdfParameters Key Derivation Function (KDF) parameters, specifically Argon2 for KDBX4.
 * @property customData Map of custom fields that can store additional, user-defined metadata.
 */

data class KdbxOuterHeader(
    val option: OuterHeaderOption,
    val seed: ByteString,
    val encryptionIV: ByteString,
    val kdfParameters: KdfParameters,
    val customData: Map<String, Kdbx4Field>,
) {

    companion object {

        fun of(random: SecureRandom): KdbxOuterHeader {
            return KdbxOuterHeader(
                option = OuterHeaderOption.Default,
                seed = random.nextBytes(32).toByteString(),
                encryptionIV = random.nextBytes(16).toByteString(),
                kdfParameters = KdfParameters.Argon2(
                    uuid = KdfParameters.KdfArgon2d,
                    salt = random.nextBytes(32).toByteString(),
                    parallelism = 2U,
                    memory = 32UL * 1024UL * 1024UL,
                    iterations = 8U,
                    version = 0x13.toUInt(),
                    key = null,
                    associatedData = null,
                ),
                customData = mapOf(),
            )
        }

        val end = ByteString.of(0x0D, 0x0A, 0x0D, 0x0A)

        const val End = 0
        const val SEED = 3
        const val ENCRYPTION_IV = 4
        const val KDF_PARAMETERS = 5
        const val CUSTOM_DATA = 6

      }


}

/**
 * [CompressionFlags] representing compression options for the KDBX data.
 * - None: No compression applied.
 * - GZip: Use GZip compression.
 */

enum class CompressionFlags {
    None, GZip
}


/**
 * [FieldID] representing the field identifiers for KDBX outer header fields.
 * Each enum value corresponds to a specific field within the header.
 *
 * - `End`: Marks the end of the header (0).
 * - `CipherID`: Identifies the encryption cipher (1).
 * - `CompressionFlags`: Identifies the compression algorithm (2).
 * - `Seed`: Cryptographic seed used in key derivation (3).
 * - `EncryptionIV`: Initialization Vector for encryption (4).
 * - `KdfParameters`: Parameters for the Key Derivation Function (5).
 * - `PublicCustomData`: Stores additional custom metadata (6).
 */
enum class FieldID {
    End,
    CipherID,
    CompressionFlags,
    Seed,
    EncryptionIV,
    KdfParameters,
    PublicCustomData
}
