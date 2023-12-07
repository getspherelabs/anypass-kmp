import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
    kotlin("native.cocoapods")
    id("com.codingfeline.buildkonfig")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    ios()
    iosX64()
    iosArm64()
    iosSimulatorArm64()


    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"

        podfile = project.file("../iosApp/Podfile")

        framework {
            baseName = "shared"
            linkerOpts.add("-lsqlite3")
            export("dev.icerock.moko:resources:0.22.3")
        }

        pod("FirebaseAuth", "~> 10.7.0")
        pod("Google-Mobile-Ads-SDK", "~> 10.3.0", moduleName = "GoogleMobileAds")
        pod("Sentry", "~> 8.4.0")
        pod("FirebaseCore")

        extraSpecAttributes["resource"] = "'build/cocoapods/framework/shared.framework/*.bundle'"

    }

    sourceSets {
        all {
            languageSettings.optIn("androidx.compose.material.ExperimentalMaterialApi")
            languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
            languageSettings.optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            languageSettings.optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            languageSettings.optIn("androidx.compose.foundation.ExperimentalFoundationApi")
        }
        val commonMain by getting {
            dependencies {
                api(projects.core.common)
                api(projects.core.designsystem)
                api(projects.core.analytics)
                api(projects.data.prefs)
                api(projects.data.local)
                api(projects.manager.password)
                api(projects.manager.biometry)
                api(projects.navigation)
                api(projects.features.addnewpassword.addNewPasswordDomain)
                api(projects.features.addnewpassword.addNewPasswodPresentation)
                api(projects.features.account.accountDomain)
                api(projects.features.account.accountPresentation)
                api(projects.features.auth.authDomain)
                api(projects.features.auth.authPresentation)
                api(projects.features.onboarding.onboardingDomain)
                api(projects.features.onboarding.onboardingPresentation)
                api(projects.features.generatepassword.generatePasswordDomain)
                api(projects.features.generatepassword.generatePasswordPresentation)
                api(projects.features.home.homeDomain)
                api(projects.features.home.homePresentation)
                api(projects.features.passphrase.masterPasswordDomain)
                api(projects.features.passphrase.masterPasswordPresentation)
                api(projects.features.changepassword.changePasswordDomain)
                api(projects.features.changepassword.changePasswordPresentation)
                api(projects.features.newtoken.newtokenApi)
                api(projects.features.newtoken.newtokenImpl)
                api(libs.moko.resource)
                api(libs.coroutine)
                api(libs.koin.core)

                implementation(projects.features.newtoken.newtokenDi)
                implementation(projects.core.admob)
                implementation(projects.core.validation)
                implementation(projects.resource.fonts)
                implementation(projects.resource.icons)
                implementation(projects.data.authManager)
                implementation(projects.core.system.foundation)
                implementation(projects.manager.otp)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(libs.sentry)

                implementation(libs.koin.compose)

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
                api(libs.koin.android)
                implementation("androidx.activity:activity-compose:1.7.2")
                implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
                implementation("androidx.compose.material:material:1.5.1")
                implementation("androidx.compose.ui:ui-tooling:1.5.1")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)

            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                api(libs.coroutine)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

    }
}

android {
    namespace = "io.spherelabs.anypass"
    compileSdk = 33

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

buildkonfig {
    packageName = "io.spherelabs.anypass"

    val (SENTRY_DSN, SENTRY_DSN_VALUE) = configs("SENTRY_DSN")
    val ANDROID_AD_ID_VALUE = config("ANDROID_AD_ID")
    val IOS_AD_ID_VALUE = config("IOS_AD_ID")
    val (AD_ID, DEFAULT_AD_ID_VALUE) = configs("AD_ID")

    defaultConfigs {
        buildConfigField(STRING, SENTRY_DSN, SENTRY_DSN_VALUE)
        buildConfigField(STRING, AD_ID, DEFAULT_AD_ID_VALUE)
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


multiplatformResources {
    multiplatformResourcesPackage = "io.spherelabs.anypass"
}
//
//tasks.withType<org.jetbrains.kotlin.gradle.tasks.DummyFrameworkTask>().configureEach {
//    @Suppress("ObjectLiteralToLambda")
//    doLast(
//        object : Action<Task> {
//            override fun execute(task: Task) {
//                task as org.jetbrains.kotlin.gradle.tasks.DummyFrameworkTask
//
//                val frameworkDir =
//                    File(task.destinationDir, task.frameworkName.get() + ".framework")
//
//                listOf(
//                    "anypass:shared.bundle",
//                ).forEach { bundleName ->
//                    val bundleDir = File(frameworkDir, bundleName)
//                    bundleDir.mkdir()
//                    File(bundleDir, "dummyFile").writeText("dummy")
//                }
//            }
//        },
//    )
//}

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
