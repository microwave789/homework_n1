package com.example.homework_n1

import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Locale

// Hard-coded examples, simulates loading logic
class HardCodedDataResource : TodoItemsDataSource {
    private val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.US)
    private val currentTimeMillis = System.currentTimeMillis()
    private val todoItems = mutableListOf(
        TodoItem(
            id = "EXAMPLE-1",
            done = false,
            text = "Купить что-то",
            importance = Importance.MID,
            createdOn = currentTimeMillis
        ),
        TodoItem(
            id = "EXAMPLE-2",
            done = false,
            text = "Купить что нибудь. Очень дорогое очень очень !!!",
            importance = Importance.MID,
            createdOn = currentTimeMillis
        ),
        TodoItem(
            id = "EXAMPLE-3",
            done = true,
            text = "Покушать",
            importance = Importance.MID,
            deadline = dateFormat.parse("9 oct 2021")?.time,
            createdOn = currentTimeMillis
        )
    )

    override suspend fun loadTodoItems(): List<TodoItem> {
        delay(300L);
        return todoItems
    }
}