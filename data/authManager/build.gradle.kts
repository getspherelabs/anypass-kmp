plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

kotlin {
    android()

    ios()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"

        // podfile = project.file("../../iosApp/Podfile")

        framework {
            baseName = "authManager"
        }
        noPodspec()
        pod("FirebaseAuth")
    }

//    cocoapods {
//        summary = "Some description for the Shared Module"
//        homepage = "Link to the Shared Module homepage"
//        version = "1.0"
//        ios.deploymentTarget = "14.1"
//
//        framework {
//            baseName = "authManager"
//        }
//        noPodspec()
//        pod("FirebaseAuth")
//
//        targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
//            binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
//                linkerOpts.add("-lsqlite3")
//            }
//        }
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Libs.Koin.core)
                api(Libs.Coroutine.core)

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Libs.Koin.android)
                implementation(Libs.Koin.compose)
                implementation(Libs.Firebase.core)
                implementation(Libs.Firebase.auth)
                implementation(Libs.Coroutine.firebase)
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
    }
}

android {
    namespace = "io.spherelabs.firebase"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}