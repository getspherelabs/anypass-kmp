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
include(":data:authManager")

include(":manager")
include(":manager:password")
include(":manager:otp")
include(":manager:biometry")

include(":core")
include(":core:designsystem")
include(":core:common")
include(":core:analytics")
include(":core:validation")
include(":core:admob")
include(":core:mlkit")
include(":core:system")
include(":core:system:foundation")

include(":resource")
include(":resource:fonts")
include(":resource:icons")
include(":resource:strings")



include(":features")
include(":features:newtoken:newtoken-api")
include(":features:newtoken:newtoken-impl")
include(":features:newtoken:newtoken-di")
include(":features:newtoken:newtoken-navigation")

include(":features:auth")
include(":features:auth:auth-api")
include(":features:auth:auth-di")
include(":features:auth:auth-impl")
include(":features:auth:auth-navigation")

include(":features:onboarding")
include(":features:onboarding:onboarding-navigation")
include(":features:onboarding:onboarding-api")
include(":features:onboarding:onboarding-di")
include(":features:onboarding:onboarding-impl")

include(":resource:images")

include(":features:home")
include(":features:home:home-api")
include(":features:home:home-impl")
include(":features:home:home-di")
include(":features:home:home-navigation")

include(":features:keypassword")
include(":features:keypassword:keypassword-api")
include(":features:keypassword:keypassword-impl")
include(":features:keypassword:keypassword-navigation")
include(":features:keypassword:keypassword-di")

include(":features:addnewpassword")
include(":features:addnewpassword:addnewpassword-api")
include(":features:addnewpassword:addnewpassword-impl")
include(":features:addnewpassword:addnewpassword-navigation")
include(":features:addnewpassword:addnewpassword-di")

include(":features:account")
include(":features:account:account-api")
include(":features:account:account-impl")
include(":features:account:account-di")
include(":features:account:account-navigation")

include(":features:generatepassword")
include(":features:generatepassword:generatepassword-api")
include(":features:generatepassword:generatepassword-impl")
include(":features:generatepassword:generatepassword-di")
include(":features:generatepassword:generatepassword-navigation")

include(":features:changepassword")
include(":features:changepassword:changepassword-api")
include(":features:changepassword:changepassword-impl")
include(":features:changepassword:changepassword-di")
include(":features:changepassword:changepassword-navigation")

include(":features:authenticator")
include(":features:authenticator:authenticator-api")
include(":features:authenticator:authenticator-impl")
include(":features:authenticator:authenticator-di")
include(":features:authenticator:authenticator-navigation")
