@file:Suppress("DSL_SCOPE_VIOLATION")

plugins { alias(libs.plugins.anypass.api) }

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.common)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.addnewpasswordapi"
    compileSdk = 33
    defaultConfig { minSdk = 24 }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
