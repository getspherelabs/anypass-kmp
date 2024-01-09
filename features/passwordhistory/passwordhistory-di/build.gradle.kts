plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "passwordhealth-di"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.passwordhistory.passwordhistoryApi)
                api(projects.features.navigation.navigationApi)
                api(projects.features.passwordhistory.passwordhistoryImpl)

                implementation(libs.voyager)
                implementation(libs.koin.core)
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
    namespace = "io.spherelabs.passwordhistorydi"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
