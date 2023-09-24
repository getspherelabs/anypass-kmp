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
            baseName = "masterPasswordPresentation"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":features:masterpassword:masterPasswordDomain"))

                api(Libs.Meteor.core)
                api(Libs.Koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Libs.Android.viewModel)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.masterpasswordpresentation"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}