plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "io.spherelabs.lockerkmp.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "io.spherelabs.lockerkmp.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    dependencies {
        implementation(project(":shared"))
        implementation(project(":navigation"))
        implementation("androidx.compose.ui:ui:1.4.0")
        implementation("androidx.compose.ui:ui-tooling:1.4.0")
        implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
        implementation("androidx.compose.foundation:foundation:1.4.0")
        implementation("androidx.compose.material:material:1.4.0")
        implementation("androidx.activity:activity-compose:1.7.0")
    }
}