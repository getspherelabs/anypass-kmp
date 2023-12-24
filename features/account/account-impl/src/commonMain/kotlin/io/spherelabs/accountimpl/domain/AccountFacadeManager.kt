package io.spherelabs.accountimpl.domain

import io.spherelabs.accountapi.domain.usecase.GetSizeOfStrongPasswordUseCase
import io.spherelabs.accountapi.domain.usecase.GetSizeOfWeakPasswordUseCase
import io.spherelabs.accountapi.domain.usecase.GetTotalPasswordUseCase
import io.spherelabs.accountimpl.domain.model.AllPasswords
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class AccountFacadeManager(
    private val getTotalPasswordUseCase: GetTotalPasswordUseCase,
    private val getSizeOfWeakPasswordUseCase: GetSizeOfWeakPasswordUseCase,
    private val getSizeOfStrongPasswordUseCase: GetSizeOfStrongPasswordUseCase,
) {

  fun getAllPasswords(): Flow<AllPasswords> {
    return flow {
      combine(
          getTotalPasswordUseCase.execute(),
          getSizeOfStrongPasswordUseCase.execute(),
          getSizeOfWeakPasswordUseCase.execute(),
      ) { totalPassword, weakPassword, strongPassword ->
        emit(
            AllPasswords(
                totalPassword = totalPassword,
                sizeOfWeakPassword = weakPassword,
                sizeOfStrongPassword = strongPassword,
            ),
        )
      }
    }
  }
}
