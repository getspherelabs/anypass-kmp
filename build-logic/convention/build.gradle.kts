plugins {
    `kotlin-dsl`
    id("com.diffplug.spotless").version("6.21.0")
}

group = "io.spherelabs.anypass.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

spotless {
    kotlin {
        target("src/**/*.kt")
        ktfmt("0.44").googleStyle()
    }

    kotlinGradle {
        target("*.kts")
        ktfmt("0.44").googleStyle()
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
        register("commonMultiplatform") {
            id = "anypass.multiplatform.common"
            implementationClass = "io.getspherelabs.convention.CommonMultiplatformPlugin"
        }
        register("prefsMultiplatform") {
            id = "anypass.multiplatform.prefs"
            implementationClass = "io.getspherelabs.convention.PrefsMultiplatformPlugin"
        }
    }
}
