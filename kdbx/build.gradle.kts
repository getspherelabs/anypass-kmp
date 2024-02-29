plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
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
            baseName = "kdbx"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.coroutine)
                implementation("com.squareup.okio:okio:3.7.0")
                implementation(projects.crypto.secureRandom)
                implementation(projects.crypto.digest)
                implementation(projects.crypto.cipher)
                implementation("com.benasher44:uuid:0.8.2")
                implementation("com.fleeksoft.ksoup:ksoup:0.1.2")
                implementation(libs.datetime)

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.coroutine.test)
                implementation("com.squareup.okio:okio-fakefilesystem:3.7.0")
            }
        }
        val androidMain by getting
        val androidUnitTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.0")
                implementation(kotlin("test"))
                implementation(libs.assertk)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "io.spherelabs.crypto.tinypass"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
