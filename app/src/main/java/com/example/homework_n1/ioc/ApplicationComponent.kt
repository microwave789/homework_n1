package com.example.homework_n1.ioc

import com.example.homework_n1.data.datasource.HardCodedDataResource
import com.example.homework_n1.data.repository.TodoItemsRepository

// Application component - contains information to be used by the whole app
class ApplicationComponent {
    private val dataSource = HardCodedDataResource()
    private val todoItemsRepository = TodoItemsRepository(dataSource)
    val viewModelFactory = ViewModelFactory(todoItemsRepository)
}
