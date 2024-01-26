package io.spherelabs.sshkey

import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val keyGeneratorModule = module {
    includes(platformModule())
}
