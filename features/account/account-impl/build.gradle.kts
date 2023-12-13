@file:Suppress("DSL_SCOPE_VIOLATION")

import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.anypass.presentation)
    alias(libs.plugins.anypass.compose)
    id("com.codingfeline.buildkonfig")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.core.designsystem)
                api(projects.resource.images)
                api(projects.features.addnewpassword.addnewpasswordNavigation)
                api(projects.features.changepassword.changepasswordNavigation)
                api(projects.data.prefs)

                implementation(projects.core.admob)
                implementation(projects.features.account.accountApi)
                implementation(libs.voyager)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(project(":core:common"))
                implementation(projects.resource.fonts)
                implementation(projects.resource.icons)
                implementation(projects.core.system.foundation)
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(libs.konsist)
            }
        }
    }
}

buildkonfig {
    packageName = "io.spherelabs.accountimp"

    val ANDROID_AD_ID_VALUE = config("ANDROID_AD_ID")
    val IOS_AD_ID_VALUE = config("IOS_AD_ID")
    val (AD_ID, DEFAULT_AD_ID_VALUE) = configs("AD_ID")

    defaultConfigs {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, AD_ID, DEFAULT_AD_ID_VALUE)
    }

    targetConfigs {
        create("android") {
            buildConfigField(STRING, AD_ID, ANDROID_AD_ID_VALUE)
        }

        create("ios") {
            buildConfigField(STRING, AD_ID, IOS_AD_ID_VALUE)
        }
    }
}

android {
    namespace = "io.spherelabs.accountimpl"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

fun configs(name: String): Pair<String, String> {
    val secret = System.getenv(name)
        ?: gradleLocalProperties(rootDir).getProperty(name)
        ?: error("No $name provided")
    return name to secret
}

fun config(name: String): String {
    val value = System.getenv(name)
        ?: gradleLocalProperties(rootDir).getProperty(name)
        ?: error("No $name provided")

    return value
}
