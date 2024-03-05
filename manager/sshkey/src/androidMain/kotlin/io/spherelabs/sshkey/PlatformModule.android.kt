package io.spherelabs.sshkey


import org.koin.dsl.module

actual fun platformModule() = module {
    single { MultiplatformKeyGenerator() }
}
