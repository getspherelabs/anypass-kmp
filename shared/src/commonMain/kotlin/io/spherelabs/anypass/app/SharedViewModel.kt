package io.spherelabs.anypass.app

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.spherelabs.onboardingapi.domain.usecase.HasCurrentUserExist
import io.spherelabs.onboardingapi.domain.usecase.IsFirstTimeUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class SharedViewModel(
    private val isFirstTimeUseCase: IsFirstTimeUseCase,
    private val hasCurrentUserExistUseCase: HasCurrentUserExist,
) : ScreenModel {

    val uiSharedState: StateFlow<SharedState> = handleApp().stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SharedState.Loading,
    )

    private fun handleApp(): Flow<SharedState> {
        return flow {
            val isFirstTime = isFirstTimeUseCase.execute()
            val currentUserExist = hasCurrentUserExistUseCase.execute()
            val result = SharedState.Success(isFirstTime, currentUserExist)

            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}

sealed interface SharedState {
    data object Loading : SharedState
    data class Success(val isFirstTime: Boolean, val isCurrentUserExist: Boolean) : SharedState
}

