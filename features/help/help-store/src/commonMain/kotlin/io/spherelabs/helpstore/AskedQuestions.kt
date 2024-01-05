package io.spherelabs.helpstore

import io.spherelabs.common.uuid4


data class AskedQuestion(
    val uuid: String = uuid4(),
    val question: String,
    val answer: String,
)
