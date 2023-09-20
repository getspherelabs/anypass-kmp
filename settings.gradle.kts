pluginManagement {
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

rootProject.name = "locker-kmp"
include(":androidApp")
include(":shared")
include(":data")
include(":data:local")
include(":data:settings")
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
include(":data:firebase")
include(":features:auth")
include(":features:auth:authDomain")
include(":features:auth:authPresentation")
