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
                api(projects.features.addnewpassword.addnewpasswordNavigation)
                api(projects.manager.password)
                api(projects.features.generatepassword.generatepasswordApi)

                implementation(libs.voyager)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(project(":core:common"))
                implementation(projects.resource.fonts)
                implementation(projects.resource.icons)
                implementation(projects.core.system.foundation)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "io.spherelabs.generatepasswordimpl"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
