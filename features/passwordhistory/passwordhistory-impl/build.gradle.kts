@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.anypass.presentation)
    alias(libs.plugins.anypass.compose)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.core.designsystem)
                api(projects.resource.images)
                api(projects.features.passwordhistory.passwordhistoryApi)

                implementation(projects.core.common)
                implementation(libs.datetime)
                implementation(libs.voyager)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(projects.resource.fonts)
                implementation(projects.resource.icons)
                implementation(projects.core.system.foundation)
                implementation("me.onebone:toolbar-compose:2.3.5")
                implementation(projects.core.system.ui)
                implementation(libs.datetime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.coroutine.test)
                implementation(libs.assertk)
                implementation(libs.turbine)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.passwordhistoryimpl"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
