package io.spherelabs.home.homepresentation

sealed interface HomeEffect {
    data class Failure(val message: String) : HomeEffect
    data class CopyClipboard(val message: String) : HomeEffect
}
