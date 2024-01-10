package io.spherelabs.anypass.app

import io.spherelabs.meteorviewmodel.viewmodel.ViewModel
import io.spherelabs.onboardingapi.domain.usecase.HasCurrentUserExist
import io.spherelabs.onboardingapi.domain.usecase.IsFirstTimeUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class SharedViewModel(
    private val isFirstTimeUseCase: IsFirstTimeUseCase,
    private val hasCurrentUserExistUseCase: HasCurrentUserExist,
) : ViewModel() {

    val uiSharedState: StateFlow<SharedState> = handleApp().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SharedState.Loading,
    )

    private fun handleApp(): Flow<SharedState> {
        return flow {
            val isFirstTime = isFirstTimeUseCase.execute()
            val currentUserExist = hasCurrentUserExistUseCase.execute()
            val result = SharedState.Success(isFirstTime, currentUserExist)
            println("Shared state is $result")
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}

sealed interface SharedState {
    data object Loading : SharedState
    data class Success(val isFirstTime: Boolean, val isCurrentUserExist: Boolean) : SharedState
}

