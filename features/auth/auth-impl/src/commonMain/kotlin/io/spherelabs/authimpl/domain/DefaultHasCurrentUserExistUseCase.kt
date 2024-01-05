package io.spherelabs.authimpl.domain

import io.spherelabs.authapi.domain.usecase.HasCurrentUserExist
import io.spherelabs.firebase.FirebaseAuthManager

class DefaultHasCurrentUserExistUseCase(private val firebaseAuthManager: FirebaseAuthManager) :
    HasCurrentUserExist {

  override fun execute(): Boolean {
    return firebaseAuthManager.currentUser?.let {
        it.uid?.let { true }
    } ?: false
  }
}
