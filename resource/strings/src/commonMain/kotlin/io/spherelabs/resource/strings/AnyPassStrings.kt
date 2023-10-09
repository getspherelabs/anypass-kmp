package io.spherelabs.resource.strings

import io.spherelabs.resource.EnAnyPassStrings


val Strings: Map<String, AnyPassStrings> = mapOf(
    "en" to EnAnyPassStrings,
)

object Locales {
    const val EN = "en"
}
data class AnyPassStrings(
    val account: String,
    val strong: String,
    val weak: String,
    val fingerPrint: String,
    val changePassword: String,
    val sendFeedback: String,
    val addNewPassword: String,
    val newPasswordRecord: String,
    val category: String,
    val selectIcon: String,
    val generatePassword: String,
    val savePassword: String,
    val loginNow: String,
    val login: String,
    val doNotHaveAccount: String,
    val createNew: String,
    val createNewAccount: String,
    val signUp: String,
    val maximumLimitCharacter: (String) -> String,
    val uppercase: String,
    val digits: String,
    val special: String,
    val randomPassword: String,
    val back: String,
    val use: String,
    val keep: String,
    val yourLife: String,
    val safe: String,
    val onboardingHeadline: String,
    val onboardingDescription: () -> String,
    val getStarted: String,
    val confirmPassphrase: String,
    val addPassphrase: String,
    val submit: String,
    val passwordLengthIsLess: String,
    val passwordFailure: String,
    val emailFailure: String,
)

