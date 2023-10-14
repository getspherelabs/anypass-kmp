package io.spherelabs.navigation.stack

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver

fun <E : Any> navigationSaver(
): Saver<Stack<E>, Any> {
    return listSaver(
        save = { stack -> stack.items },
        restore = { items -> NavigationStack(items) },
    )
}
