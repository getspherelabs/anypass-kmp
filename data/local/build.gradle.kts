plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("app.cash.sqldelight") version "2.0.0"
    kotlin("native.cocoapods")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    android {
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

        podfile = project.file("../../iosApp/Podfile")

        framework {
            baseName = "local"

            export("dev.icerock.moko:resources:0.22.3")
        }

        targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
            binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
                linkerOpts.add("-lsqlite3")
            }
        }


    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.addnewpassword.addNewPasswordDomain)
                api(projects.features.home.homeApi)
                api(projects.features.account.accountDomain)
                api(projects.features.authenticator.authenticatorDomain)
                api(libs.moko.resource)

                implementation(projects.manager.otp)
                implementation(projects.features.newtoken.newtokenApi)
                implementation(libs.koin.core)
                implementation(libs.datetime)
                implementation(libs.sqldelight.runtime)
                implementation(libs.sqldelight.ext)
                implementation(libs.sqldelight.adapter)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.turbine)
                implementation(libs.coroutine.test)
                implementation(libs.assertk)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.sqldelight.android)
                implementation(libs.koin.android)
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(libs.sqldelight.test)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                api(libs.sqldelight.native)
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

            dependencies {
                api(libs.sqldelight.native)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.data.local"
    compileSdk = 33

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }


    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = 24
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "io.spherelabs.anypass.local"
}


sqldelight {
    databases {
        create("AnyPassDatabase") {
            packageName.set("io.spherelabs.local.db")
        }
        linkSqlite.set(true)
    }
}
