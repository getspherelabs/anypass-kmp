package io.spherelabs.authdomain

import io.spherelabs.common.exception.AuthException
import io.spherelabs.common.exception.IdNotAvailableException
import io.spherelabs.data.local.db.UserDao
import io.spherelabs.firebase.FirebaseAuthManager
import io.spherelabs.local.db.User

interface CreateEmailAndPassword {
  suspend fun execute(name: String, email: String, password: String): Result<String>
}

class DefaultCreateEmailAndPassword(
  private val authManager: FirebaseAuthManager,
  private val userDao: UserDao
) : CreateEmailAndPassword {
  override suspend fun execute(name: String, email: String, password: String): Result<String> {
    val authResult = authManager.createEmailAndPassword(email, password)
    println("Authentication result is $authResult")
    return if (authResult.isSuccess) {
      val newId = authResult.getOrThrow().user?.uid
      if (newId != null) {
        runCatching {
            userDao.insertUser(User(id = newId, name = name, email = email, password = password))
          }
          .fold(onSuccess = { Result.success(newId) }, onFailure = { Result.failure(it) })
      } else {
        Result.failure(IdNotAvailableException("User ID not available"))
      }
    } else {
      Result.failure(authResult.exceptionOrNull() ?: AuthException("Authentication failed"))
    }
  }
}
