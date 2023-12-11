@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.api)
}
kotlin {
    sourceSets {
        val androidUnitTest by getting {
            dependencies {
                implementation(libs.konsist)
            }
        }
    }
}
android {
    namespace = "io.spherelabs.homeapi"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
