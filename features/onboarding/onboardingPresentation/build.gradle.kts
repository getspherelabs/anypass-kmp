plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
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
            linkerOpts = mutableListOf("-lsqlite3")
            baseName = "onboardingPresentation"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":features:onboarding:onboardingDomain"))
                implementation(compose.runtime)

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
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

        val jvmMain by creating {
            dependsOn(commonMain)
        }

        val desktopMain by creating {
            dependsOn(commonMain)
            jvmMain.dependsOn(this)

            dependencies {
                api(Libs.Coroutine.core)
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.features.onboardingpresentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
    }
}