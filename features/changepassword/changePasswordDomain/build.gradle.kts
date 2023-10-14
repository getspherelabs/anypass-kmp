@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.domain)
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.assertk)
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation("com.lemonappdev:konsist:0.13.0")
            }
        }
    }
}


android {
    namespace = "io.spherelabs.changepassworddomain"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
