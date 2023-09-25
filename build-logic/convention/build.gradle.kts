/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    `kotlin-dsl`
    id("com.diffplug.spotless").version("6.21.0")
}

group = "io.spherelabs.anypass.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

spotless {
    kotlin {
        target("src/**/*.kt")
        ktlint("0.49.1")
    }

    kotlinGradle {
        target("*.kts")
        ktlint("0.49.1")
    }
}

dependencies {
    compileOnly("com.diffplug.spotless:spotless-plugin-gradle:6.21.0")
    compileOnly("org.jetbrains.compose:compose-gradle-plugin:1.5.0")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
}

gradlePlugin {
    plugins {
        register("compose") {
            id = "anypass.compose"
            implementationClass = "io.getspherelabs.convention.ComposeMultiplatformPlugin"
        }
        register("domainMultiplatform") {
            id = "anypass.multiplatform.domain"
            implementationClass = "io.getspherelabs.convention.KotlinMultiplatformPlugin"
        }
        register("designSystemMultiplatform") {
            id = "anypass.multiplatform.designsystem"
            implementationClass = "io.getspherelabs.convention.DesignSystemMultiplatformPlugin"
        }
        register("presentationMultiplatform") {
            id = "anypass.multiplatform.presentation"
            implementationClass = "io.getspherelabs.convention.PresentationMultiplatformPlugin"
        }
    }
}
