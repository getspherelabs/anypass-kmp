package io.getspherelabs.convention.multiplatform

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

internal fun Project.configureDomainMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
                    api("io.insert-koin:koin-core:3.3.0")
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
                    implementation("app.cash.turbine:turbine:1.0.0")
                }
            }
            val androidMain by getting
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
}


internal fun Project.configureDesignSystemMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation("org.jetbrains.kotlinx:atomicfu:0.17.3")
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
            val androidMain by getting {
                dependsOn(commonMain)
                dependencies {
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
                    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0")
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
}

internal fun Project.configurePresentationMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
                    api("io.insert-koin:koin-core:3.3.0")
                    api("io.github.behzodhalil:meteor-mvi:0.4.3")
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
            val androidMain by getting {
                dependencies {
                    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
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
}


internal fun Project.configureCommonMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            val commonMain by getting
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
            val androidMain by getting
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
}

internal fun Project.configurePrefsMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            all {
                languageSettings.optIn("com.russhwolf.settings.ExperimentalSettingsApi")
            }
            val commonMain by getting {
                dependencies {
                    api("io.insert-koin:koin-core:3.3.0")
                    api("com.russhwolf:multiplatform-settings:1.0.0")
                    api("com.russhwolf:multiplatform-settings-coroutines:1.0.0")
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
            val androidMain by getting {
                dependencies {
                    implementation("io.insert-koin:koin-android:3.3.1")
                    implementation("androidx.datastore:datastore:1.1.0-alpha04")
                    implementation("androidx.datastore:datastore-preferences:1.1.0-alpha04")
                    api("com.russhwolf:multiplatform-settings-datastore:1.0.0")
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
}

internal fun Project.configureValidationMultiplatform(
    extension: KotlinMultiplatformExtension,
) {

    extension.apply {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }

        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.all {
                linkerOpts("-lsqlite3")
            }
        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
                    api("io.insert-koin:koin-core:3.3.0")
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
                }
            }
            val androidMain by getting
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
}

internal fun KotlinMultiplatformExtension.sourceSets(
    configure: Action<NamedDomainObjectContainer<KotlinSourceSet>>,
) {
    (this as ExtensionAware).extensions.configure("sourceSets", configure)
}

