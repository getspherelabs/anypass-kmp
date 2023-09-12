pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "locker-kmp"
include(":androidApp")
include(":shared")
include(":data")
include(":data:local")
include(":data:settings")
include(":features")
include(":biometry")
include(":navigation")
include(":features:onboarding")
include(":features:onboarding:onboardingDomain")
include(":features:onboarding:onboardingPresentation")
include(":manager")
include(":manager:password")
