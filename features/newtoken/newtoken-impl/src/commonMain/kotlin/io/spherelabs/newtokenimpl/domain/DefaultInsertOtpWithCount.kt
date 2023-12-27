package io.spherelabs.newtokenimpl.domain

import io.spherelabs.newtokenapi.domain.repository.NewTokenRepository
import io.spherelabs.newtokenapi.domain.usecase.InsertOtpWithCountUseCase
import io.spherelabs.newtokenapi.model.NewTokenDomain

class DefaultInsertOtpWithCount(
    private val repository: NewTokenRepository,
) : InsertOtpWithCountUseCase {
  override suspend fun execute(data: NewTokenDomain, count: Long) {
    repository.insertOtpWithCount(data, count)
  }
}
