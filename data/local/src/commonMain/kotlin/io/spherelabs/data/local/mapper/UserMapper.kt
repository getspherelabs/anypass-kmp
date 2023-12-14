package io.spherelabs.data.local.mapper

import io.spherelabs.accountapi.model.AccountUser
import io.spherelabs.local.db.UserEntity

fun UserEntity.asDomain(): AccountUser {
    return AccountUser(
        id = id,
        name = name ?: "Unknown",
        password = password ?: "****",
        email = email ?: "Unknown",
    )
}
