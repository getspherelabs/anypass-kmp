package io.spherelabs.helpstore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface HelpStoreManager {
    fun getAskedQuestions(): Flow<List<AskedQuestion>>
}

class DefaultHelpStoreManager : HelpStoreManager {

    override fun getAskedQuestions(): Flow<List<AskedQuestion>> {
        return flow {
            emit(
                listOf(
                    AskedQuestion(
                        uuid = "1",
                        question = "",
                        answer = "",
                    ),
                ),
            )
        }
    }
}
