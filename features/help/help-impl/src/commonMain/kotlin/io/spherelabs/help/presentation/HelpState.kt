package io.spherelabs.help.presentation

import io.spherelabs.common.Empty
import io.spherelabs.model.FAQs

data class HelpState(
    val email: String = String.Empty,
    val list: FAQs = emptyList(),
) {
    companion object {
        val Empty = HelpState()
    }
}
