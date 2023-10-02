plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("io.sentry.android.gradle") version "3.12.0"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "io.spherelabs.anypass.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "io.spherelabs.anypass.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    dependencies {
        implementation(project(":shared"))
        implementation(project(":navigation"))
        implementation("androidx.compose.ui:ui:1.5.1")
        implementation("androidx.compose.ui:ui-tooling:1.5.1")
        implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
        implementation("androidx.compose.foundation:foundation:1.5.1")
        implementation("androidx.compose.material:material:1.5.1")
        implementation("androidx.activity:activity-compose:1.7.2")
        implementation(Libs.Firebase.auth)
        implementation("io.sentry:sentry-kotlin-multiplatform:0.2.1")
    }
}

configurations {
    compileOnly {
        exclude(group = "io.sentry", module = "sentry")
        exclude(group = "io.sentry", module = "sentry-android")
    }
}


