@file:Suppress("DSL_SCOPE_VIOLATION")

plugins { alias(libs.plugins.anypass.prefs) }

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.features.changepassword.changePasswordDomain)
            }
        }
    }
}

android {
  namespace = "io.spherelabs.data.settings"
  compileSdk = 33
  defaultConfig { minSdk = 24 }
}
