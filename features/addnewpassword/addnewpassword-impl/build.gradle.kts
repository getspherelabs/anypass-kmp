@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.presentation)
    alias(libs.plugins.anypass.compose)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.core.designsystem)
                api(projects.resource.images)
                api(projects.core.validation)
                api(projects.features.navigation.navigationApi)

                implementation(projects.core.common)
                implementation(projects.features.addnewpassword.addnewpasswordApi)
                implementation(libs.voyager)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.foundation)
                implementation(compose.runtime)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(libs.kotlinx.immutable)
                implementation(project(":core:common"))
                implementation(projects.resource.fonts)
                implementation(projects.resource.icons)
                implementation(projects.core.system.foundation)

                implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha01")
                implementation("io.coil-kt.coil3:coil-network:3.0.0-alpha01")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.core:core-ktx:1.10.1")
                api(compose.preview)
                api(compose.uiTooling)
            }
        }
        val androidUnitTest by getting { dependencies { implementation(libs.konsist) } }
    }
}

android {
    namespace = "io.spherelabs.addnewpasswordimpl"
    compileSdk = 33
    defaultConfig { minSdk = 24 }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}
