package io.spherelabs.masterpasswordpresentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.store.createMeteor
import io.spherelabs.meteorviewmodel.commonflow.NonNullCommonFlow
import io.spherelabs.meteorviewmodel.commonflow.NonNullCommonStateFlow
import io.spherelabs.meteorviewmodel.commonflow.asCommonFlow
import io.spherelabs.meteorviewmodel.commonflow.asCommonStateFlow
import io.spherelabs.meteorviewmodel.viewmodel.ViewModel
import kotlinx.coroutines.launch

class MasterPasswordViewModel(
  private val masterPasswordReducer: MasterPasswordReducer,
  private val masterPasswordMiddleware: MasterPasswordMiddleware
) : ViewModel() {

  private val store: Store<MasterPasswordState, MasterPasswordWish, MasterPasswordEffect> =
    viewModelScope.createMeteor(
      configs =
        MeteorConfigs.build {
          initialState = MasterPasswordState.Empty
          storeName = MASTER_PASSWORD_STORE
          reducer = masterPasswordReducer
          middlewares = listOf(masterPasswordMiddleware)
        }
    )

  init {
    viewModelScope.launch { store.wish(MasterPasswordWish.CheckMasterPassword) }
  }

  fun wish(wish: MasterPasswordWish) {
    viewModelScope.launch { store.wish(wish) }
  }

  val state: NonNullCommonStateFlow<MasterPasswordState> by lazy {
    this.store.state.asCommonStateFlow()
  }

  val effect: NonNullCommonFlow<MasterPasswordEffect> by lazy { this.store.effect.asCommonFlow() }

  override fun onCleared() {
    super.onCleared()
    store.cancel()
  }

  companion object {
    private const val MASTER_PASSWORD_STORE = "MasterPasswordViewModel"
  }
}
