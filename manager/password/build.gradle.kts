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
            baseName = "password"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
               api(Libs.Koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
                implementation("app.cash.turbine:turbine:1.0.0")
            }
        }
    }
}

android {
    namespace = "io.spherelabs.manager.password"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
