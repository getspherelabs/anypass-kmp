plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("io.sentry.android.gradle")
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
        implementation(projects.shared)
        implementation(projects.navigation)

        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.ui.tooling)
        implementation(libs.androidx.compose.ui.tooling.preview)
        implementation(libs.androidx.compose.activity)
        implementation(libs.androidx.compose.material)
        implementation(libs.androidx.compose.foundation)
        implementation(libs.firebase.auth)
        implementation(libs.sentry)
    }
}

configurations {
    compileOnly {
        exclude(group = "io.sentry", module = "sentry")
        exclude(group = "io.sentry", module = "sentry-android")
    }
}


