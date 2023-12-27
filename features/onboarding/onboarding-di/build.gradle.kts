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
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "onboarding-di"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.onboarding.onboardingImpl)
                api(projects.features.navigation.navigationApi)

                implementation(projects.features.onboarding.onboardingApi)
                implementation(libs.koin.core)
                implementation(libs.voyager)

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
    namespace = "io.spherelabs.onboardingdi"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
