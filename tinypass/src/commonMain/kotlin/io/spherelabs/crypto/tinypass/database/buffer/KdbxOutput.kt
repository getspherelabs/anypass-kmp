package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader

sealed interface KdbxOutput {
    data class OuterHeader(val outerHeader: KdbxOuterHeader) :
        KdbxOutput
    data class InnerHeader(val innerHeader: KdbxInnerHeader) :
        KdbxOutput
}


sealed interface KdbxInput {
    object OuterHeader : KdbxInput
    object InnerHeader : KdbxInput
}
