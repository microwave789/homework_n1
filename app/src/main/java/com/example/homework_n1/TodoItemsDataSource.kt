package com.example.homework_n1

// Интерфейс, который служит источником данных для приложения. Получает список элементов
interface TodoItemsDataSource {
    suspend fun loadTodoItems(): List<TodoItem>
}
