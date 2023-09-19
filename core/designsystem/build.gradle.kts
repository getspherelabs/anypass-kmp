plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
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
            baseName = "designsystem"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.ui)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies    {
                implementation("androidx.savedstate:savedstate-ktx:1.2.1")
                implementation("androidx.lifecycle:lifecycle-runtime:2.6.2")
                implementation("androidx.compose.animation:animation-core:1.5.1")
                implementation("androidx.compose.ui:ui-util:1.5.1")
                implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.0")
                api("androidx.compose.material:material-icons-core:1.5.1")
                api("androidx.compose.material:material-ripple:1.5.1")
                api("androidx.compose.runtime:runtime:1.5.1")
                api("androidx.compose.ui:ui-graphics:1.5.1")
                api("androidx.compose.ui:ui:1.5.1")
                api("androidx.compose.ui:ui-text:1.5.1")
                implementation("androidx.activity:activity-compose:1.7.2")
                implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
                implementation("androidx.compose.ui:ui-tooling:1.5.1")
                implementation("androidx.lifecycle:lifecycle-common-java8:2.6.2")
            }
        }
        val androidUnitTest by getting
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
    namespace = "io.spherelabs.designsystem"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}