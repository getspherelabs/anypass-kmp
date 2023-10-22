package io.spherelabs.data.local.mapper

import io.spherelabs.accountdomain.repository.AccountUserUi
import io.spherelabs.local.db.UserEntity

fun UserEntity.asDomain(): AccountUserUi {
    return AccountUserUi(
        id = id,
        name = name ?: "Unknown",
        password = password ?: "****",
        email = email ?: "Unknown",
    )
}
