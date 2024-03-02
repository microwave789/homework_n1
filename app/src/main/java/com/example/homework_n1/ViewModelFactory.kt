package com.example.homework_n1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Class, that creates ViewModels
class ViewModelFactory(
    private val todoItemsRepository: TodoItemsRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        TodoItemsViewModel::class.java -> TodoItemsViewModel(todoItemsRepository)
        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}
