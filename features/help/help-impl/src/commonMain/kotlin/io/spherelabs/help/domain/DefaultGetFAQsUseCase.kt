package io.spherelabs.help.domain

import io.spherelabs.domain.usecase.GetFAQsUseCase
import io.spherelabs.help.domain.mapper.asDomain
import io.spherelabs.helpstore.HelpStoreManager
import io.spherelabs.model.FAQs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultGetFAQsUseCase(
    private val storeManager: HelpStoreManager,
) : GetFAQsUseCase {
    override fun execute(): Flow<FAQs> {
        return storeManager.getAskedQuestions().map { faqs ->
            faqs.asDomain()
        }
    }
}
