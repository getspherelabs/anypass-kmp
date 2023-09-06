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
include(":features:createPassword")
include(":biometry")
