package io.spherelabs.accountapi.domain.usecase


interface OpenUrlUseCase {
  suspend fun execute(url: String)
}

