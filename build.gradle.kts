@file:Suppress("DSL_SCOPE_VIOLATION")

import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        gradlePluginPortal()
    }

    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.22.3")
        classpath("com.android.tools.build:gradle:7.4.1")
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
    alias(libs.plugins.spotless)
    alias(libs.plugins.dependencyanalysis)
    alias(libs.plugins.kotlinx.serialization)
    id("org.jetbrains.kotlin.jvm") version "1.8.20" apply false
}

allprojects {
    tasks.withType<KotlinCompilationTask<*>>().configureEach {
        compilerOptions {
            allWarningsAsErrors.set(false)

            if (project.providers.gradleProperty("anypass.enableComposeCompilerReports").isPresent) {
                freeCompilerArgs.addAll(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                        layout.buildDirectory.asFile.get().absolutePath + "/compose_metrics",
                )
                freeCompilerArgs.addAll(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                        layout.buildDirectory.asFile.get().absolutePath + "/compose_metrics",
                )
            }
        }
    }
}
fun Project.spotless(action: SpotlessExtension.() -> Unit) = extensions.configure<SpotlessExtension>(action)
