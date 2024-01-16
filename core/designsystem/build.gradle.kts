@file:Suppress("DSL_SCOPE_VIOLATION")

plugins { alias(libs.plugins.anypass.designsystem) }

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.resource.strings)
                api(libs.lyricist)

                implementation(projects.core.system.foundation)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.ui)
                implementation(libs.koin.compose)
                implementation(libs.meteor)
            }
        }
        val androidMain by getting {
            dependencies { implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2") }
        }
    }
}

android {
    namespace = "io.spherelabs.designsystem"
    compileSdk = 33
    defaultConfig { minSdk = 24 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
