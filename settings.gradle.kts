pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "anypass-kmp"
include(":androidApp")
include(":shared")
include(":data")
include(":data:local")
include(":data:prefs")
include(":features")
include(":navigation")
include(":features:onboarding")
include(":features:onboarding:onboardingDomain")
include(":features:onboarding:onboardingPresentation")
include(":manager")
include(":manager:password")
include(":features:addnewpassword")
include(":features:addnewpassword:addNewPasswordDomain")
include(":core")
include(":core:designsystem")
include(":core:common")
include(":features:addnewpassword:addNewPasswodPresentation")
include(":manager:biometry")
include(":features:generatepassword")
include(":features:generatepassword:generatePasswordDomain")
include(":features:generatepassword:generatePasswordPresentation")
include(":features:home")
include(":features:home:homeDomain")
include(":features:home:homePresentation")
include(":data:authManager")
include(":features:auth")
include(":features:auth:authDomain")
include(":features:auth:authPresentation")
include(":core:analytics")
include(":features:account")
include(":features:account:accountDomain")
include(":features:account:accountPresentation")
include(":features:masterpassword")
include(":features:masterpassword:masterPasswordDomain")
include(":features:masterpassword:masterPasswordPresentation")
include(":core:validation")
