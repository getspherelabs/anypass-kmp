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
        minSdk = 26
        targetSdk = 34
        versionCode = 3
        versionName = "0.1.3"
        multiDexEnabled = true

    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
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
        implementation(projects.shared)
        implementation(projects.data.prefs)
        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.ui.tooling)
        implementation(libs.androidx.compose.ui.tooling.preview)
        implementation(libs.androidx.compose.activity)
        implementation(libs.androidx.compose.material)
        implementation(libs.androidx.compose.foundation)
        implementation(libs.firebase.auth)
        implementation(libs.sentry)
        implementation(libs.koin.android)
        implementation(libs.koin.core)
        implementation(libs.app.update)
        implementation(libs.app.update.ktx)
    }
}

configurations {
    compileOnly {
        exclude(group = "io.sentry", module = "sentry")
        exclude(group = "io.sentry", module = "sentry-android")
    }
}


