package io.spherelabs.designsystem.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.compose.LocalKoinScope
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

@Composable
inline fun <reified T> useInject(
    qualifier: Qualifier? = null,
    scope: Scope = LocalKoinScope.current,
    noinline parameters: ParametersDefinition? = null,
): T {
    return remember(qualifier, scope, parameters) {
        scope.get(qualifier, parameters)
    }
}
