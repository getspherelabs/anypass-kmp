package io.spherelabs.masterpasswordpresentation

data class MasterPasswordState(
    val password: String = "",
    val confirmPassword: String? = null,
    val isInitialPasswordExisted: Boolean = false,
    val isExistPassword: Boolean = false
) {
    companion object {
        val Empty = MasterPasswordState()

        fun row1(): List<String> = listOf("1","2","3")
        fun row2(): List<String> = listOf("4","5","6")
        fun row3(): List<String> = listOf("7","8","9")
        fun row4(): List<String> = listOf("0","c")
    }

}