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
                jvmTarget = "17"
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
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
                implementation(compose.material)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                api("dev.icerock.moko:resources-compose:0.22.3")
                api(project(":navigation"))
                api(Libs.Coroutine.core)
                api(Libs.Koin.core)

                api(project(":core:common"))
                api(project(":features:addnewpassword:addNewPasswordDomain"))
                api(project(":features:addnewpassword:addNewPasswodPresentation"))

                api(project(":data:prefs"))
                api(project(":manager:password"))
                implementation(project(":data:authManager"))
                implementation(project(":core:admob"))
                api(project(":features:auth:authDomain"))
                api(project(":features:auth:authPresentation"))
                api(project(":features:onboarding:onboardingDomain"))
                api(project(":features:onboarding:onboardingPresentation"))
                implementation("io.sentry:sentry-kotlin-multiplatform:0.2.1")


                api(project(":features:generatepassword:generatePasswordDomain"))
                api(project(":features:generatepassword:generatePasswordPresentation"))

                implementation(project(":features:masterpassword:masterPasswordDomain"))
                implementation(project(":features:masterpassword:masterPasswordPresentation"))
                implementation(project(":resource"))
                api(project(":data:local"))
                api(project(":manager:biometry"))
                api(project(":core:designsystem"))
                implementation(project(":core:validation"))
                api(project(":features:home:homeDomain"))
                api(project(":features:home:homePresentation"))
                implementation(Libs.Koin.compose)

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
                implementation(Libs.Android.coil)
                api(Libs.Koin.android)
                implementation("androidx.activity:activity-compose:1.7.2")
                implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
                implementation("androidx.compose.material:material:1.5.0")
                implementation("androidx.compose.ui:ui-tooling:1.5.0")
                // implementation ("com.google.android.gms:play-services-ads:22.4.0")
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
                api(Libs.Coroutine.core)
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.DummyFrameworkTask>().configureEach {
    @Suppress("ObjectLiteralToLambda")
    doLast(
        object : Action<Task> {
            override fun execute(task: Task) {
                task as org.jetbrains.kotlin.gradle.tasks.DummyFrameworkTask

                val frameworkDir =
                    File(task.destinationDir, task.frameworkName.get() + ".framework")

                listOf(
                    "anypass:shared.bundle",
                ).forEach { bundleName ->
                    val bundleDir = File(frameworkDir, bundleName)
                    bundleDir.mkdir()
                    File(bundleDir, "dummyFile").writeText("dummy")
                }
            }
        },
    )
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
