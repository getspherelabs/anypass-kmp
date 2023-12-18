@file:Suppress("DSL_SCOPE_VIOLATION")

//import com.diffplug.gradle.spotless.SpotlessExtension

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        gradlePluginPortal()
    }

    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.22.3")
        classpath("com.android.tools.build:gradle:7.4.2")
    }
}

plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.compose.multiplatform).apply(false)
    alias(libs.plugins.secrets).apply(false)
    alias(libs.plugins.gms).apply(false)
    alias(libs.plugins.buildkonfig).apply(false)
    alias(libs.plugins.sentry).apply(false)
    // alias(libs.plugins.spotless)
    alias(libs.plugins.dependencyanalysis)
    id("org.jetbrains.kotlin.jvm") version "1.8.20" apply false
}

//fun Project.spotless(action: SpotlessExtension.() -> Unit) = extensions.configure<SpotlessExtension>(action)
