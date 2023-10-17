package io.spherelabs.authdomain.usecase

import io.spherelabs.firebase.FirebaseAuthManager

interface HasCurrentUserExist {
  fun execute(): Boolean
}

class DefaultHasCurrentUserExistUseCase(private val firebaseAuthManager: FirebaseAuthManager) :
    HasCurrentUserExist {

  override fun execute(): Boolean {
    return firebaseAuthManager.currentUser?.let {
        it.currentName?.let { true }
    } ?: false
  }
}
