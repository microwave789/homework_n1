package com.example.homework_n1

class TodoItemsRepository {

    private val todoItems = mutableListOf(
        // Захардкодить 10-20 дел
        TodoItem("1", false, "2024-03-01"),
        TodoItem("2", true, "2024-02-28"),
        // ...
    )

    fun getAll(): List<TodoItem> {
        return todoItems
    }

    fun add(item: TodoItem) {
        todoItems.add(item)
    }

    // ...
}
