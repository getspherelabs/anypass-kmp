package io.spherelabs.addnewpasswordapi.domain.usecase

import io.spherelabs.addnewpasswordapi.model.Websites
import io.spherelabs.common.Option
import kotlinx.coroutines.flow.Flow

interface GetWebsites {
    fun execute(): Flow<Result<Websites>>
}
