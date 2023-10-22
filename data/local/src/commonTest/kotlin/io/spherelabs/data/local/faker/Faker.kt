package io.spherelabs.data.local.faker

import io.spherelabs.data.local.db.otp.AlgorithmTypeEntity
import io.spherelabs.data.local.db.otp.OtpDigitEntity
import io.spherelabs.data.local.db.otp.OtpDurationEntity
import io.spherelabs.local.db.OtpEntity
import io.spherelabs.local.db.PasswordEntity
import kotlinx.datetime.Clock

object Faker {
    val otp = Otp.get()
    val password = Password.get()
}

object Otp {
    fun get(): OtpEntity {
        return OtpEntity(
            id = "1",
            createdTimestamp = Clock.System.now().toEpochMilliseconds(),
            digit = OtpDigitEntity.SIX,
            info = "Behance",
            duration = OtpDurationEntity.Fifteen,
            issuer = "Behance",
            secret = "XSKSAdkShrhruHDHJDS",
            serviceName = "Behance",
            type = AlgorithmTypeEntity.SHA1,
        )
    }
}

object Password {
    fun get(): List<PasswordEntity> {
        return listOf(
            PasswordEntity(
                id = "1",
                username = "test",
                category_id = "1",
                email = "test",
                image = "test",
                notes = "test",
                title = "test",
                password = "test",
                websiteAddress = "test",
            ),
            PasswordEntity(
                id = "1",
                username = "test",
                category_id = "2",
                email = "test",
                image = "test",
                notes = "test",
                title = "test",
                password = "test",
                websiteAddress = "test",
            ),
        )
    }
}
