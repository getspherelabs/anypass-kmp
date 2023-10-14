package io.spherelabs.navigation.stack

interface Stack<E : Any> {
    val items: List<E>
    val top: E?
    fun push(element: E)
    fun push(elements: List<E>)
    fun replace(element: E)
    fun pop(): E
    fun popAll()
    val size: Int
    val isEmpty: Boolean
}
