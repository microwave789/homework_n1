package com.example.homework_n1

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class TodoItemsRepository(
    // Data source that updates the items
    private val dataSource: TodoItemsDataSource
) {
    // Live data of lists, that store the items (public getter)
    private val _todoItems = MutableLiveData<List<TodoItem>>(emptyList())
    val todoItems: LiveData<List<TodoItem>> = _todoItems

    // апдейт
    @MainThread
    suspend fun updateTodoItems() {
        val loadedTodoItems = withContext(Dispatchers.IO) { dataSource.loadTodoItems() }
        _todoItems.value = loadedTodoItems
    }

    // изменить
    suspend fun changeTodoItem(changedTodoItem: TodoItem) {
        val newTodoItems = withContext(Dispatchers.Default) {
            todoItems.value.orEmpty().map { todoItem ->
                if (todoItem.id == changedTodoItem.id) changedTodoItem
                else todoItem.copy(editedOn = System.currentTimeMillis())
            }
        }
        _todoItems.value = newTodoItems
    }

    // удалить
    suspend fun deleteTodoItem(todoItemId: String) {
        withContext(Dispatchers.Default) {
            _todoItems.postValue(todoItems.value.orEmpty().filter { it.id != todoItemId })
        }
    }

    // добавить
    suspend fun addTodoItem(newTodoItem: TodoItem) {
        withContext(Dispatchers.Default) {
            val appended = todoItems.value.orEmpty() as MutableList<TodoItem>
            appended.add(newTodoItem)
            _todoItems.postValue(appended)
        }
    }
}
