plugins {
    `kotlin-dsl`
    id("com.diffplug.spotless").version("6.23.0")
}

group = "io.spherelabs.anypass.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

//spotless {
//    kotlin {
//        target("src/**/*.kt")
//        ktfmt("0.44").googleStyle()
//        licenseHeaderFile(rootProject.file("spotless/anypass-copyright.txt"))
//            .onlyIfContentMatches("missingString")
//    }
//    kotlinGradle {
//        target("*.kts")
//        ktfmt("0.44").googleStyle()
//        licenseHeaderFile(rootProject.file("spotless/anypass-copyright.txt"), "(^(?![\\/ ]\\*).*$)")
//            .onlyIfContentMatches("missingString")
//    }
//    format("xml") {
//        target("src/**/*.xml")
//        targetExclude("**/build/", ".idea/")
//        trimTrailingWhitespace()
//        endWithNewline()
//    }
//}

dependencies {
    compileOnly("com.diffplug.spotless:spotless-plugin-gradle:6.21.0")
    compileOnly("org.jetbrains.compose:compose-gradle-plugin:1.5.1")
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
        register("validationMultiplatform") {
            id = "anypass.multiplatform.validation"
            implementationClass = "io.getspherelabs.convention.ValidationMultiplatformPlugin"
        }
        register("resourceMultiplatform") {
            id = "anypass.multiplatform.resource"
            implementationClass = "io.getspherelabs.convention.ResourceMultiplatformPlugin"
        }
        register("apiMultiplatform") {
            id = "anypass.multiplatform.api"
            implementationClass = "io.getspherelabs.convention.ApiMultiplatformPlugin"
        }
    }
}
