package com.example.homework_n1

// Application component - contains information to be used by the whole app
class ApplicationComponent {
    private val dataSource = HardCodedDataResource()
    private val todoItemsRepository = TodoItemsRepository(dataSource)
    val viewModelFactory = ViewModelFactory(todoItemsRepository)
}
