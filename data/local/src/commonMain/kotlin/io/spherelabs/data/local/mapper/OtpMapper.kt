package io.spherelabs.data.local.mapper

import io.spherelabs.authenticatordomain.model.AlgorithmTypeDomain
import io.spherelabs.authenticatordomain.model.OtpDigitDomain
import io.spherelabs.authenticatordomain.model.OtpDomain
import io.spherelabs.authenticatordomain.model.OtpDurationDomain
import io.spherelabs.data.local.db.otp.asDomain
import io.spherelabs.data.local.db.otp.asEntity
import io.spherelabs.local.db.OtpEntity
import io.spherelabs.newtokenapi.model.NewTokenDomain

fun OtpEntity.asDomain(): OtpDomain {
    return OtpDomain(
        id = this.id,
        secret = this.secret,
        issuer = this.issuer,
        serviceName = this.serviceName,
        info = this.info,
        digit = this.digit?.asDomain() ?: OtpDigitDomain.SIX,
        duration = this.duration?.asDomain() ?: OtpDurationDomain.SIXTY,
        type = this.type?.asDomain() ?: AlgorithmTypeDomain.SHA512,
        createdTimestamp = this.createdTimestamp,
    )
}

fun OtpDomain.asEntity(): OtpEntity {
    return OtpEntity(
        id = this.id,
        secret = this.secret,
        issuer = this.issuer,
        serviceName = this.serviceName,
        info = this.info,
        digit = this.digit.asEntity(),
        duration = this.duration.asEntity(),
        type = this.type.asEntity(),
        createdTimestamp = this.createdTimestamp,
    )
}

fun NewTokenDomain.asEntity(): OtpEntity {
    return OtpEntity(
        id = this.id,
        secret = this.secret,
        issuer = this.issuer,
        serviceName = this.serviceName,
        info = this.info,
        digit = this.digit.asEntity(),
        duration = this.duration.asEntity(),
        type = this.type.asEntity(),
        createdTimestamp = this.createdTimestamp,
    )
}

