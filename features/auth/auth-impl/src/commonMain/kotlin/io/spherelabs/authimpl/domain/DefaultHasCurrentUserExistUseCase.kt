package io.spherelabs.authimpl.domain

import io.spherelabs.authapi.domain.usecase.HasCurrentUserExist
import io.spherelabs.firebase.FirebaseAuthManager

class DefaultHasCurrentUserExistUseCase(private val firebaseAuthManager: FirebaseAuthManager) :
    HasCurrentUserExist {

  override fun execute(): Boolean {
    return firebaseAuthManager.currentUser?.let {
        println("Firebase user is ${it.email}")
        it.email?.let { true }
    } ?: false
  }
}
