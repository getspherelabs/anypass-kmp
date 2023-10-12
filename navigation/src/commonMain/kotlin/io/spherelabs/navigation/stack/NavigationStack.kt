package io.spherelabs.navigation.stack

class NavigationStack<E : Any>(
    initialItems: Collection<E> = emptyList(),
) : Stack<E> {
    private val queue = ArrayDeque(initialItems)

    override val items: List<E> get() = queue.toList()

    override val top: E? get() = queue.lastOrNull()

    override fun push(element: E) {
        queue.addLast(element)
    }

    override fun push(elements: List<E>) {
        queue.addAll(elements)
    }

    override fun replace(element: E) {
        if (queue.isEmpty()) {
            push(element)
        } else {
            queue[queue.lastIndex] = element
        }
    }

    override fun pop(): E {
        return queue.removeLast()
    }

    override fun popAll() {
        queue.removeAll(items)
    }

    override val size: Int
        get() = queue.size

    override val isEmpty: Boolean
        get() = queue.isEmpty()
}
