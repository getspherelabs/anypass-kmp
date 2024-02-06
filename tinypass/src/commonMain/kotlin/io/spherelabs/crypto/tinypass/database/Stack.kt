package io.spherelabs.crypto.tinypass.database

class Stack<T : Any> {
    private val storage = arrayListOf<T>()

    companion object {
        fun <T : Any> create(items: Iterable<T>): Stack<T> {
            val stack = Stack<T>()
            for (item in items) {
                stack.push(item)
            }
            return stack
        }
    }


    override fun toString(): String {
        return storage.toString()
    }

    fun push(element: T) {
        storage.add(element)
    }

    fun pop(): T? {
        if (isEmpty) {
            return null
        }
        return storage.removeAt(count - 1)
    }

    fun peek(): T? {
        return storage.lastOrNull()
    }

    fun reversed(): Stack<T> {
        return create(storage.reversed())
    }

    fun toList(): List<T> {
        return storage
    }

    val count: Int
        get() = storage.size

    val isEmpty: Boolean
        get() = storage.isEmpty()

    val isNotEmpty: Boolean
        get() = storage.isNotEmpty()
}
