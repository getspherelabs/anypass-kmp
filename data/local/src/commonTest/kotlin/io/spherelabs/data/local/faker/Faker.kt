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
            duration = OtpDurationEntity.FIFTEEN,
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
                category_id = "1",
                email = "test@gmail.com",
                image = "test_2_image",
                title = "Behance",
                notes = "test_notes",
                password = "12345",
                username = "Test1",
                websiteAddress = "Behance.com",
            ),
            PasswordEntity(
                id = "2",
                category_id = "2",
                email = "test@gmail.com",
                image = "test_2_image",
                title = "Linkedin",
                notes = "test_notes",
                password = "Strong@2023",
                username = "Test2",
                websiteAddress = "Linkedin.com",
            ),
            PasswordEntity(
                id = "3",
                category_id = "1",
                email = "test@gmail.com",
                image = "test_2_image",
                title = "Quora",
                notes = "test_notes",
                password = "12345",
                username = "Test3",
                websiteAddress = "Quora.com",
            ),
            PasswordEntity(
                id = "4",
                category_id = "1",
                email = "test@gmail.com",
                image = "test_2_image",
                title = "Behance",
                notes = "test_notes",
                password = "Strong@2023",
                username = "Test1",
                websiteAddress = "Behance.com",
            ),
        )
    }
}
