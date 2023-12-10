pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
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
include(":core:analytics")
include(":features:account")
include(":features:account:accountDomain")
include(":features:account:accountPresentation")
include(":features:passphrase")
include(":features:passphrase:masterPasswordDomain")
include(":features:passphrase:masterPasswordPresentation")
include(":core:validation")
include(":core:admob")
include(":resource")
include(":resource:fonts")
include(":resource:icons")
include(":resource:strings")
include(":manager:otp")
include(":core:mlkit")
include(":features:changepassword")
include(":features:changepassword:changePasswordDomain")
include(":features:changepassword:changePasswordPresentation")
include(":core:system")
include(":core:system:foundation")
include(":features:authenticator")
include(":features:authenticator:authenticatorDomain")
include(":features:newtoken:newtoken-api")
include(":features:newtoken:newtoken-impl")
include(":features:newtoken:newtoken-di")
include(":features:auth:auth-api")
include(":features:auth:auth-di")
include(":features:auth:auth-impl")
include(":features:auth:auth-navigation")
include(":features:onboarding:onboarding-navigation")
include(":features:onboarding:onboarding-api")
include(":features:onboarding:onboarding-di")
include(":features:onboarding:onboarding-impl")
include(":resource:images")
