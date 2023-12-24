package io.spherelabs.helpstore

import io.spherelabs.common.uuid4

val askedQuestions = listOf(
    AskedQuestion(
        uuid = uuid4(),
        question = "What is AnyPass, and why should I use it?",
        answer = "AnyPass is a password manager that helps you keep all your passwords safe and easily accessible. It also manages Time-based One-Time Passwords (TOTP) and helps create strong passwords.",
    ),
    AskedQuestion(
        uuid = uuid4(),
        question = "Is AnyPass secure?",
        answer = "Yes, AnyPass prioritizes your security. It uses encryption to safeguard your data, and only you have access to your passwords and TOTP through your master password.",
    ),
    AskedQuestion(
        uuid = uuid4(),
        question = "What if I forget my master password?",
        answer = "If you forget your master password, AnyPass provides secure recovery options to help you regain access to your stored passwords."
    )
)

data class AskedQuestion(
    val uuid: String,
    val question: String,
    val answer: String,
)
