package io.spherelabs.model

typealias FAQs = List<FAQ>

data class FAQ(
    val uuid: String,
    val question: String,
    val answer: String,
)
