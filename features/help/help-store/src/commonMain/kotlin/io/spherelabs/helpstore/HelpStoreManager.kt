package io.spherelabs.helpstore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias FAQs = List<AskedQuestion>

interface HelpStoreManager {
    fun getAskedQuestions(): Flow<FAQs>
}

class DefaultHelpStoreManager : HelpStoreManager {

    override fun getAskedQuestions(): Flow<FAQs> {
        return flow {
            emit(
                askedQuestions,
            )
        }
    }
}
