@file:Suppress("DSL_SCOPE_VIOLATION")

plugins { alias(libs.plugins.anypass.prefs) }

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies { implementation(projects.features.changepassword.changepasswordApi) }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.security:security-crypto-ktx:1.1.0-alpha03")
            }

        }
    }
}

android {
    namespace = "io.spherelabs.data.settings"
    compileSdk = 33
    defaultConfig { minSdk = 24 }
}
