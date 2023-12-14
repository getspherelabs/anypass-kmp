package io.spherelabs.data.local.mapper

import io.spherelabs.authenticatorapi.model.CounterDomain
import io.spherelabs.local.db.CounterEntity

fun CounterEntity.asDomain(): CounterDomain {
    return CounterDomain(
        otpId, counter,
    )
}
