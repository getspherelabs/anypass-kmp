package io.spherelabs.resource

import cafe.adriel.lyricist.LyricistStrings
import io.spherelabs.resource.strings.AnyPassStrings
import io.spherelabs.resource.strings.Locales

@LyricistStrings(languageTag = Locales.EN, default = true)
val EnAnyPassStrings: AnyPassStrings = AnyPassStrings(
    account = "Account",
    strong = "Strong",
    weak = "Weak",
    fingerPrint = "Fingerprint",
    changePassword = "Change a password",
    sendFeedback = "Send a feedback",
    addNewPassword = "Add a new password",
    newPasswordRecord = "Add new password to your records",
    category = "Category",
    selectIcon = "Select a icon",
    generatePassword = "Generate a password",
    savePassword = "Save password",
    loginNow = "Hey,\nLogin Now!",
    login = "Login",
    doNotHaveAccount = "Don't have account?",
    createNew = "Create new",
    createNewAccount = "Create a new \naccount",
    signUp = "Sign Up",
    maximumLimitCharacter = { number ->
        "Maximum limit character: $number"
    },
    uppercase = "Uppercase",
    digits = "Digits",
    special = "Special",
    randomPassword = "Random password",
    back = "Back",
    use = "Use",
    keep = "Keep",
    yourLife = "Your Life",
    safe = "Safe",
    onboardingHeadline = "Password Manager \n From Anywhere",
    onboardingDescription = {
        "Keep your passwords in a secure private vault-and simply access them with one click from all your devices."
    },
    getStarted = "Get Started",
    confirmPassphrase = "Confirm Passphrase",
    addPassphrase = "Add Passphrase",
    submit = "Submit",
    passwordFailure = "Please, enter an password again!",
    emailFailure = "Please, enter an email again!",
    passwordLengthIsLess = "The password length is less than eight!"
)
