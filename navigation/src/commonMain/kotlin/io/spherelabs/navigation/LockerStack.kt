package io.spherelabs.navigation

/**
 * A custom stack data structure for managing a collection of elements.
 *
 * The [LockerStack] provides a simple stack implementation using a `Queue` (specifically, an `ArrayDeque`)
 * to manage a collection of elements. You can push elements onto the stack, pop elements from the stack, and
 * retrieve information about the stack's contents.
 *
 * @param E The type of elements to be stored in the stack.
 * @property initialItems An optional collection of initial elements to populate the stack with.
 */
class LockerStack<E : Any>(initialItems: Collection<E> = emptyList()) {
    private val queue = ArrayDeque<E>(initialItems)

    /**
     * Returns a list of items currently in the stack.
     */
    val items: List<E> get() = queue.toList()

    /**
     * Retrieves the top element of the stack.
     *
     * @return The top element of the stack, or `null` if the stack is empty.
     */
    val top: E? get() = queue.lastOrNull()

    /**
     * Gets the current size of the stack.
     */
    val size: Int get() = queue.size

    /**
     * Pushes the specified [element] onto the top of the stack.
     *
     * @param element The element to be pushed onto the stack.
     */
    fun push(element: E) {
        queue.addLast(element)
    }

    /**
     * Pops the top element from the stack and returns the popped element.
     *
     * @return The element that was removed from the top of the stack.
     * @throws NoSuchElementException if the stack is empty.
     */
    fun pop(): E {
        return queue.removeLast()
    }
}