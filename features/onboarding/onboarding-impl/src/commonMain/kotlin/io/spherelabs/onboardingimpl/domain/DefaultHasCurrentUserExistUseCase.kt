package io.spherelabs.onboardingimpl.domain

import io.spherelabs.firebase.FirebaseAuthManager
import io.spherelabs.onboardingapi.domain.usecase.HasCurrentUserExist

class DefaultHasCurrentUserExistUseCase(private val firebaseAuthManager: FirebaseAuthManager) :
    HasCurrentUserExist {

  override fun execute(): Boolean {
    return firebaseAuthManager.currentUser?.let { it.currentName?.let { true } } ?: false
  }
}
