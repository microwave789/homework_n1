package com.example.homework_n1.data.datasource

import com.example.homework_n1.data.model.TodoItem

// Interface, that serves as a data source for the app. Gets a list of itemsf
interface TodoItemsDataSource {
    suspend fun loadTodoItems(): List<TodoItem>
}
