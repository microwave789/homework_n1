package com.example.homework_n1.data.datasource

import com.example.homework_n1.data.model.TodoItem

// Интерфейс, который служит источником данных для приложения. Получает список элементов
interface TodoItemsDataSource {
    suspend fun loadTodoItems(): List<TodoItem>
}
