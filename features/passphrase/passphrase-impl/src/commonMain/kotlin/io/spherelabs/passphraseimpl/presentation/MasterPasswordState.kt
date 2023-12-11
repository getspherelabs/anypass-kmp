package io.spherelabs.passphraseimpl.presentation

data class MasterPasswordState(
    val password: String = "",
    val isInitialPasswordExisted: Boolean = false,
    val isExistPassword: Boolean = false,
    val isFingerprintEnabled: Boolean = false,
) {
    companion object {
        val Empty = MasterPasswordState()

        val keypads: Array<List<String>> = arrayOf(
            listOf("1","2","3"),
            listOf("4,","5","6"),
            listOf("7","8","9"),
            listOf("<","0","c")
        )
    }
}
