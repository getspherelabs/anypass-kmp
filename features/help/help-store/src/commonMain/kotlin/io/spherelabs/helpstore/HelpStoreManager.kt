package io.spherelabs.helpstore

import io.spherelabs.common.uuid4
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias FAQs = List<AskedQuestion>

interface HelpStoreManager {
    fun getAskedQuestions(): Flow<FAQs>
}

class DefaultHelpStoreManager : HelpStoreManager {
    private val askedQuestions = listOf(
        AskedQuestion(
            question = "What is AnyPass, and why should I use it?",
            answer = "AnyPass is a password manager that helps you keep all your passwords safe and easily accessible. It also manages Time-based One-Time Passwords (TOTP) and helps create strong passwords.",
        ),
        AskedQuestion(
            question = "Is AnyPass secure?",
            answer = "Yes, AnyPass prioritizes your security. It uses encryption to safeguard your data, and only you have access to your passwords and TOTP through your master password.",
        ),
        AskedQuestion(
            question = "What if I forget my master password?",
            answer = "If you forget your master password, AnyPass provides secure recovery options to help you regain access to your stored passwords."
        )
    )

    override fun getAskedQuestions(): Flow<FAQs> {
        return flow {
            emit(
                askedQuestions,
            )
        }
    }
}
