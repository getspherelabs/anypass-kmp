@file:Suppress("DSL_SCOPE_VIOLATION")

import com.diffplug.gradle.spotless.SpotlessExtension

buildscript {
    repositories {
        gradlePluginPortal()
    }

    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.22.3")
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    }
}

plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.jvm).apply(false)
    alias(libs.plugins.compose.multiplatform).apply(false)
    alias(libs.plugins.secrets).apply(false)
    alias(libs.plugins.gms).apply(false)
    alias(libs.plugins.buildkonfig).apply(false)
    alias(libs.plugins.sentry).apply(false)
    alias(libs.plugins.spotless)
}

fun Project.spotless(action: SpotlessExtension.() -> Unit) = extensions.configure<SpotlessExtension>(action)
