package io.spherelabs.addnewpasswordimpl.domain

import io.spherelabs.addnewpasswordapi.domain.repository.AddNewPasswordRepository
import io.spherelabs.addnewpasswordapi.domain.usecase.GetWebsites
import io.spherelabs.addnewpasswordapi.model.Websites
import kotlinx.coroutines.flow.Flow

class DefaultGetWebsites(
    private val repository: AddNewPasswordRepository,
) : GetWebsites {


    override fun execute():Flow<Result<Websites>> {
        return repository.getWebsites()
    }
}
