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
            baseName = "masterPasswordDomain"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":data:prefs"))

                api(Libs.Coroutine.core)
                api(Libs.Koin.core)
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
    namespace = "io.spherelabs.masterpassworddomain"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}