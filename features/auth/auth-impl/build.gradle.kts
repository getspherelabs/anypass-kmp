@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.presentation)
    alias(libs.plugins.anypass.compose)
}

kotlin {
    sourceSets {
        all {
            languageSettings.optIn("androidx.compose.material.ExperimentalMaterialApi")
            languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
            languageSettings.optIn("androidx.compose.foundation.ExperimentalFoundationApi")
            languageSettings.optIn("androidx.compose.foundation.layout.ExperimentalLayoutApi")
        }
        val commonMain by getting {
            dependencies {
                api(projects.core.designsystem)
                api(projects.data.local)
                api(projects.data.authManager)
                api(projects.data.prefs)
                api(projects.features.navigation.navigationApi)

                implementation(projects.core.common)
                implementation(projects.features.auth.authApi)
                implementation(projects.features.keypassword.keypasswordApi)
                api(projects.features.navigation.navigationApi)
                api(projects.core.validation)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(compose.foundation)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(libs.voyager)
                implementation(libs.koin.compose)
                implementation(projects.resource.fonts)
                implementation(projects.resource.icons)
                implementation(projects.core.system.foundation)
            }
        }
        val androidUnitTest by getting { dependencies { implementation(libs.konsist) } }
    }
}

android {
    namespace = "io.spherelabs.authimpl"
    compileSdk = 33
    defaultConfig { minSdk = 24 }
}
