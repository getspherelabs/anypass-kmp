package io.spherelabs.domain.usecase

import io.spherelabs.model.FAQs
import kotlinx.coroutines.flow.Flow

interface GetFAQsUseCase {
    fun execute(): Flow<FAQs>
}
