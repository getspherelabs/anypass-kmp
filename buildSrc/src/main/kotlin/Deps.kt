object Version {
    const val ktorVersion = "2.3.2"
    const val meteorVersion = "0.4.3"
    const val coroutineVersion = "1.7.2"
    const val settingsVersion = "1.0.0"
    const val coilVersion = "2.4.0"
    const val koinCoreVersion = "3.3.0"
    const val koinAndroidVersion = "3.3.1"
    const val jsonVersion = "1.5.0"
    const val serializationCoreVersion = "1.4.1"
    const val navigationVersion = "2.5.1"
    const val turbineVersion = "1.0.0"
    const val datastoreVersion = "1.1.0-alpha04"
    const val composeLifecycleVersion = "2.6.0"
    const val accompanistNavigationVersion = "0.30.1"
    const val composeAnimationVersion = "1.4.3"
    const val sqldelightVersion = "2.0.0"
    const val ktlint = "10.3.0"
    const val firebaseAuthVersion = "19.4.0"
    const val firebaseCoreVersion = "17.2.1"
    const val firebaseCoroutineVersion = "1.4.1"
    const val koverVersion = "0.6.0"
}

object Libs {
    object Android {
        const val datastore = "androidx.datastore:datastore:${Version.datastoreVersion}"
        const val datastorePref =
            "androidx.datastore:datastore-preferences:${Version.datastoreVersion}"
        const val composeLifecycle =
            "androidx.lifecycle:lifecycle-runtime-compose:${Version.composeLifecycleVersion}"
        const val navigation = "androidx.navigation:navigation-compose:${Version.navigationVersion}"
        const val coil = "io.coil-kt:coil-compose:${Version.coilVersion}"
        const val navigationAnimation =
            "com.google.accompanist:accompanist-navigation-animation:${Version.accompanistNavigationVersion}"
        const val composeAnimation =
            "androidx.compose.animation:animation:${Version.composeAnimationVersion}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1"
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Version.ktorVersion}"
        const val content = "io.ktor:ktor-client-content-negotiation:${Version.ktorVersion}"
        const val serializationJson =
            "io.ktor:ktor-serialization-kotlinx-json:${Version.ktorVersion}"
        const val logging = "io.ktor:ktor-client-logging:${Version.ktorVersion}"
        const val darwin = "io.ktor:ktor-client-darwin:${Version.ktorVersion}"
        const val okhttp = "io.ktor:ktor-client-okhttp:${Version.ktorVersion}"
        const val test = "io.ktor:ktor-server-test-host:${Version.ktorVersion}"
        const val mock = "io.ktor:ktor-client-mock:${Version.ktorVersion}"
    }


    object Meteor {
        const val core = "io.github.behzodhalil:meteor-mvi:${Version.meteorVersion}"
        const val usecase = "io.github.behzodhalil:meteor-usecase:${Version.meteorVersion}"
        const val viewModel = "io.github.behzodhalil:meteor-viewmodel:${Version.meteorVersion}"
    }

    object Coroutine {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutineVersion}"
        const val firebase =
            "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Version.firebaseCoroutineVersion}"
    }

    object Settings {
        const val core = "com.russhwolf:multiplatform-settings:${Version.settingsVersion}"
        const val coroutine =
            "com.russhwolf:multiplatform-settings-coroutines:${Version.settingsVersion}"
        const val datastore =
            "com.russhwolf:multiplatform-settings-datastore:${Version.settingsVersion}"
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Version.koinAndroidVersion}"
        const val core = "io.insert-koin:koin-core:${Version.koinCoreVersion}"
        const val compose = "io.insert-koin:koin-compose:1.0.4"
    }


    object Testing {
        const val turbine = "app.cash.turbine:turbine:${Version.turbineVersion}"
        const val koin = "io.insert-koin:koin-test:${Version.koinCoreVersion}"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutineVersion}"
    }

    object Firebase {
        const val auth = "com.google.firebase:firebase-auth-ktx:${Version.firebaseAuthVersion}"
        const val core = "com.google.firebase:firebase-core:${Version.firebaseCoreVersion}"
    }

    object SqlDelight {
        const val runtime = "app.cash.sqldelight:runtime:${Version.sqldelightVersion}"
        const val android = "app.cash.sqldelight:android-driver:${Version.sqldelightVersion}"
        const val native = "app.cash.sqldelight:native-driver:${Version.sqldelightVersion}"
        const val extension = "app.cash.sqldelight:coroutines-extensions:${Version.sqldelightVersion}"
        const val primitiveAdapter = "app.cash.sqldelight:primitive-adapters:${Version.sqldelightVersion}"
        const val test = "app.cash.sqldelight:sqlite-driver:${Version.sqldelightVersion}"
    }
}