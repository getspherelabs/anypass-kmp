package io.spherelabs.authapi.domain.usecase

interface HasCurrentUserExist {
  fun execute(): Boolean
}
