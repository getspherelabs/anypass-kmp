package io.spherelabs.home.homedomain

import io.spherelabs.home.homedomain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

interface GetEmail {
  fun execute(): Flow<String>
}

class DefaultGetEmail(private val repository: HomeRepository) : GetEmail {
  override fun execute(): Flow<String> {
    return repository.getEmail()
  }
}
