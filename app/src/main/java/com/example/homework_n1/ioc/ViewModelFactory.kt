package com.example.homework_n1.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework_n1.data.repository.TodoItemsRepository
import com.example.homework_n1.ui.stateholders.TodoItemsViewModel

// Class, that creates ViewModels (only one currently supported)
class ViewModelFactory(
    private val todoItemsRepository: TodoItemsRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        TodoItemsViewModel::class.java -> TodoItemsViewModel(todoItemsRepository)
        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}
